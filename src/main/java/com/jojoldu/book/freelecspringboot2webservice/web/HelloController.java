package com.jojoldu.book.freelecspringboot2webservice.web;


import com.jojoldu.book.freelecspringboot2webservice.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 준다. 그렇기 때문에 @ResponseBody를 메소드 마다 선언 해주지 않아도 된다.
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        //@RequestParam은 외부에서 API로 넘김 파라미터를 가져오는 어노테이션.
        //여기에선 외부에서 name(@RequestParam("name"))이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장하게 된다.

        return new HelloResponseDto(name, amount);
    }
}
