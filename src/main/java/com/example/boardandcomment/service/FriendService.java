package com.example.boardandcomment.service;

import com.example.boardandcomment.domain.friend.Friend;
import com.example.boardandcomment.domain.friend.FriendRepository;
import com.example.boardandcomment.domain.friendRequest.FriendRequestRepository;
import com.example.boardandcomment.domain.friendRequest.FriendRequest;
import com.example.boardandcomment.domain.member.Member;
import com.example.boardandcomment.domain.member.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class FriendService {
    private final FriendRequestRepository friendRequestRepository;
    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    public List<Member> getFriend(Cookie memberId) {
        Member member = memberRepository.findByUuid(memberId.getValue()).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));
        List<Friend> friendList = friendRepository.findByMember(member);
        List<Member> friends = new ArrayList<>();
        for (int i = 0; i < friendList.size(); i++) {
            friends.add(friendList.get(i).getFriend());
        }
        return friends;
    }

    public List<Member> getSearch(Cookie memberId, String keyword) {
        Member member = memberRepository.findByUuid(memberId.getValue()).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));
        List<Member> searchList = new ArrayList<>();
        List<Member> memberList = memberRepository.findByUuidContaining(keyword);
        List<Friend> friendList = friendRepository.findByMember(member);

        for (Member m : memberList) {
            boolean isFriend = false;
            for (Friend f : friendList) {
                if (f.getFriend().equals(m)) {
                    isFriend = true;
                }
            }
            if (!m.equals(member) && !isFriend) {
                searchList.add(m);
            }
        }
        return searchList;
    }

    public FriendRequest saveFriendRequest(Cookie memberId, String friendId) {
        Member member = memberRepository.findByUuid(memberId.getValue()).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));
        Member friendMember = memberRepository.findByUuid(friendId).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));

        return friendRequestRepository.save(FriendRequest.builder()
                .giveMember(member)
                .takeMember(friendMember)
                .build());
    }

    public List<Member> getFriendRequest(Cookie memberId) {
        Member member = memberRepository.findByUuid(memberId.getValue()).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));
        List<Member> giveMembers = new ArrayList<>();
        List<FriendRequest> friendRequestList = friendRequestRepository.findAllByTakeMember(member);

        for (int i = 0; i < friendRequestList.size(); i++) {
            giveMembers.add(friendRequestList.get(i).getGiveMember());
        }
        return giveMembers;
    }

    public void saveFriend(Cookie memberId, String friendId) {
        Member member = memberRepository.findByUuid(memberId.getValue()).orElseThrow(() -> new IllegalArgumentException("해당 멤버는 없습니다."));
        Member friend = memberRepository.findByUuid(friendId).orElseThrow(() -> new IllegalArgumentException("해당 멤버는 없습니다."));

        friendRepository.save(Friend.builder()
                .member(member)
                .friend(friend)
                .build());
        friendRepository.save(Friend.builder()
                .member(friend)
                .friend(member)
                .build());

        FriendRequest friendRequestEntity = friendRequestRepository.findByTakeMemberAndGiveMember(member, friend);
        friendRequestRepository.delete(friendRequestEntity);
    }

    public void deleteFriend(Cookie memberId, String friendId) {
        Member member = memberRepository.findByUuid(memberId.getValue()).orElseThrow(() -> new IllegalArgumentException("해당 멤버는 없습니다."));
        Member friend = memberRepository.findByUuid(friendId).orElseThrow(() -> new IllegalArgumentException("해당 멤버는 없습니다."));

        FriendRequest friendRequestEntity = friendRequestRepository.findByTakeMemberAndGiveMember(member, friend);
        friendRequestRepository.delete(friendRequestEntity);
    }
}
