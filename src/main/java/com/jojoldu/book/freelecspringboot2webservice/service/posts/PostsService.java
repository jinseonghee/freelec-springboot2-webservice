package com.jojoldu.book.freelecspringboot2webservice.service.posts;

import com.jojoldu.book.freelecspringboot2webservice.domain.posts.Posts;
import com.jojoldu.book.freelecspringboot2webservice.domain.posts.PostsRepository;
import com.jojoldu.book.freelecspringboot2webservice.web.PostsResponseDto;
import com.jojoldu.book.freelecspringboot2webservice.web.PostsUpdateRequestDto;
import com.jojoldu.book.freelecspringboot2webservice.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복이 대신 생성
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional // 데이터 베이스의 상태를 변경하는 작업이나 한번에 수행되어야 하는 연산들을 의미, 예외 발생시 자동으로 rollback 처리
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));


    }

    public PostsResponseDto findById(Long id) {
    }
}
