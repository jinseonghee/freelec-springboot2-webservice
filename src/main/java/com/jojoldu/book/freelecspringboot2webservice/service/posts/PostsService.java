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
    public Long update(Long id, PostsUpdateRequestDto requestDto) { // 데이터 베이스 쿼리를 날리는 부분이 없는 이유는 JPA 영속성 컨텍스트 때문. - 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영(더티 체킹)
                    // JPA 영속성 컨텍스트는 엔티티를 영구 저장하는 환경(JPA의 핵심은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈림.)
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
