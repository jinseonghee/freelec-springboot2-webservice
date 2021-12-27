package com.jojoldu.book.freelecspringboot2webservice.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jojoldu.book.freelecspringboot2webservice.config.auth.LoginUser;
import com.jojoldu.book.freelecspringboot2webservice.config.auth.dto.SessionUser;
import com.jojoldu.book.freelecspringboot2webservice.service.posts.PostsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {

	private final PostsService postsService;

	@GetMapping("/")
	public String index(Model model, @LoginUser SessionUser user) { // model은 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음.
	                                   								//여기선 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달
									   								//기존에 (User) httpSession.getAttribute("user")로 가져오던 세션 정보 값이 @LoginUser로 개선
		model.addAttribute("posts", postsService.findAllDesc());

		if (user != null) {
			model.addAttribute("userName", user.getName()); // 세션에 저장된 값이 있을 때만 model에 userName으로 등록.
			                                                            //세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인 버튼만 보이도록 한다.
		}
		return "index";
	}

	@GetMapping("/posts/save")
	public String postsSave() {
		return "posts-save";
	}

	@GetMapping("/posts/update/{id}")
	public String postsUpdate(@PathVariable Long id, Model model) {
		PostsResponseDto dto = postsService.findById(id);
		model.addAttribute("post", dto);
		return "posts-update";
	}
}
