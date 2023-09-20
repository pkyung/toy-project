package com.example.boardandcomment.domain.board;

import com.example.boardandcomment.domain.member.Member;
import com.example.boardandcomment.domain.time.TimeStamped;
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
public class Board extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
