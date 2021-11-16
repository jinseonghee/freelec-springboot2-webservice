package com.jojoldu.book.freelecspringboot2webservice.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After //JUnit 단위테스트가 끝날 때마다 수행되는 메서드 지정
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {

        //given - 테스트에서 구체화하고자 하는 행동을 시작하기 전에 테스트 상태를 설명하는 부분
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() //postRepository.save는 테이블에 insert/update 쿼리 실행. id 값이 있다면 update, 없다면 insert 쿼리 실행
                .title(title)
                .content(content)
                .author("wlstjdgml0610@gmail.com")
                .build());

        //when - 구체화하고자 하는 그 행동
        List<Posts> postsList = postsRepository.findAll(); // 테이블 posts에 있는 모든 데이터를 조회해오는 메서드

        //then - 어떤 특정한 행동 때문에 발생할거라고 예상되는 변화에 대한 설명
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
