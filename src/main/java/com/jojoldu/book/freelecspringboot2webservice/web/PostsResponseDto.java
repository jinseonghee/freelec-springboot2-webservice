package com.jojoldu.book.freelecspringboot2webservice.web;

import com.jojoldu.book.freelecspringboot2webservice.domain.posts.Posts;
import lombok.Data;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto (Posts entity) { //Entity의 필드 중 일부만 사용하므로 생성자로 entity를 받아 필드에 값을 넣어 줌.
                                             //굳이 모든 필드를 가진 생성자가 필요하진 않으므로 Dto는 Entity를 받아 처리
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getTitle();
        this.author = entity.getAuthor();
    }
}
