package com.jojoldu.book.freelecspringboot2webservice.config.auth.dto;

import com.jojoldu.book.freelecspringboot2webservice.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable { //SessionUser에는 인증된 사용자 정보만 필요. 해서 name, email, picture 필드만 선언

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
