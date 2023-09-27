package com.example.boardandcomment.domain.friend;

import com.example.boardandcomment.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "give_id")
    private Member giveMember;

    @ManyToOne
    @JoinColumn(name = "take_id")
    private Member takeMember;

}
