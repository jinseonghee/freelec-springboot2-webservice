package com.jojoldu.book.freelecspringboot2webservice.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //기본 생성자 자동 추가
@Entity //테이블과 링크될 클래스임을 나타냄
public class Posts {

    @Id //해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) //이 옵션을 추가해야만 auto_increment가 된다.
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함. 빌더 패턴을 활용하면 어떤 필드에 어떤 인자를 넣어줬는지 명확히 알 수 있음
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
