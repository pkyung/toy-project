package com.example.boardandcomment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BoardAndCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardAndCommentApplication.class, args);
    }

}
