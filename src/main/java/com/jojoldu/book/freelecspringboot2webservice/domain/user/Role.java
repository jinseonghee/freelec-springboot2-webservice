package com.jojoldu.book.freelecspringboot2webservice.domain.user;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    public final String key;
    public final String title;
}
