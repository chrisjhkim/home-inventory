package com.chrisjhkim.sweethome.config.auth;

import com.chrisjhkim.sweethome.config.auth.dto.OAuthAttributes;
import com.chrisjhkim.sweethome.config.auth.dto.SessionUser;
import com.chrisjhkim.sweethome.member.entity.Role;
import com.chrisjhkim.sweethome.member.entity.User;
import com.chrisjhkim.sweethome.member.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	private final UserRepository userRepository;
	private final HttpSession httpSession;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
				.getUserInfoEndpoint().getUserNameAttributeName();

		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

		User user = saveOrUpdate(attributes);
		httpSession.setAttribute("user", new SessionUser(user));

		return new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(user.getRole().getKey())),
				attributes.getAttributes(),
				attributes.getNameAttributeKey());
	}


	private User saveOrUpdate(OAuthAttributes attributes) {
		try {
			User user = userRepository.findByEmail(attributes.getEmail())
					.map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
					.orElse(attributes.toGuestEntity());

			if ( user.getEmail().equals("chris1108g@gmail.com")){
				user.setRole(Role.MASTER);
			}

			return userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
