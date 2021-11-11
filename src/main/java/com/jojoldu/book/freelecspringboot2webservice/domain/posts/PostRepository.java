package com.jojoldu.book.freelecspringboot2webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Long> { // <Entity 클래스, PK 타입> 명시 (기본적인 CRUD 메서드 자동 생성)

}
