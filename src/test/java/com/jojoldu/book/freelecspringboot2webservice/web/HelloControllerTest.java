package com.jojoldu.book.freelecspringboot2webservice.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴. 여기선 SpringRunner라는 스프링 실행자를 사용.(스프링 부트 테스트와 JUnit 사이의 연결자 역할)
@WebMvcTest(controllers = HelloController.class) //여러 스프링 어노테이션 중, web(spring MVC)에 집중할 수 있는 어노테이션
//@Controller, @ControllerAdvice 등 사용 가능(@Service, @component, @Repository등은 사용 불가)
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 bean을 주입받음
    private MockMvc mvc; //웹 API를 테스트 할 떄 사용, 스프링 MVC 테스트의 시작점.(이 클래스를 통해 HTTP, GET, POST등에 대한 API 테스트를 할 수 있다.)

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 HTTP GET 요청.
                .andExpect(status().isOk()) //mvc.perform결과를 검증. HTTP Header의 Status를 검증.(200, 404, 500 error 검증)
                .andExpect(content().string(hello)); //응답 본문의 내용을 검증.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name) // param은 API 테스트할 때 사용될 요청 파라미터를 설정
                .param("amount", String.valueOf(amount))) // 단 값은 String만 허용. 숫자/날짜등은 문자열로 변경해서 사용
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //JSON 응답값을 필드별로 검증할 수 있는 메서드
                .andExpect(jsonPath("$.amount", is(amount))); //$를 기준으로 필드명 명시
    }
}
