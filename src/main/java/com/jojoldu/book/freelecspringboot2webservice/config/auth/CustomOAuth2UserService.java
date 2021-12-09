package com.jojoldu.book.freelecspringboot2webservice.config.auth;

import com.jojoldu.book.freelecspringboot2webservice.config.auth.dto.OAuthAttributes;
import com.jojoldu.book.freelecspringboot2webservice.config.auth.dto.SessionUser;
import com.jojoldu.book.freelecspringboot2webservice.domain.user.User;
import com.jojoldu.book.freelecspringboot2webservice.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> { // 해당 클래스를 이용해서 userRequest에 있는 정보를 빼낼 수 있습니다.

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService(); //DefaultOAuth2UserService는 OAuth2UserService의 구현체
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 현재 로그인 진행 중인 서비스를 구분하는 코드 (후에 네이버 로그인인지 구글 로그인인지 구분하기 위해 사용)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        //OAuth2 로그인 진행 시 키가 되는 필드값을 이야기 함.(primary Key와 같은 의미)
        //구글의 경우 기본적인 코드를 지원하지만, 네이버 카카오등은 기본 지원하지 않음. 구글의 기본 코드는 "sub"임. (후에 네이버 구글 로그인 동시 지원할 떄 사용)

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        //OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스. 이후 네이버 등 다른 소셜 로그인도 이 클래스 사용

        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(user)); //세션에 사용자 정보를 저장하기 위한 Dto 클래스 (왜 User 클래스를 쓰지 않고 새로 만드어서 쓰는지 이유가 잇음)

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey()))
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.
                        getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

                return userRepository.save(user);
    }
}
