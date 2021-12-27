package com.jojoldu.book.freelecspringboot2webservice.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { //JpaRepository<엔티티 클래스, PK 타입>

    Optional<User> findByEmail(String email); //소셜 로그인으로 반환되는 값 중 email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단하기 위한 메서드
        //Optional은 null이 아닌 값을 포함하거나 포함하지 않을 수 있는 컨테이너 객체
}
