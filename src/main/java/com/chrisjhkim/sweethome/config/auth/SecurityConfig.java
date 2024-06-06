package com.chrisjhkim.sweethome.config.auth;

import com.chrisjhkim.sweethome.member.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화
//				.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable()) // CSRF 보호 비활성화
//				.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.ignoringRequestMatchers("/h2-console/**")) // CSRF 보호 비활성화

				.headers(headers -> headers
						.frameOptions(Customizer.withDefaults()))
//						.frameOptions().disable())
				.authorizeHttpRequests(authorize -> authorize
						// 웹 리소스 권한
						.requestMatchers("/css/**", "/images/**", "/js/**", "/profile")
							.permitAll()
						// H2 DB console URL
						.requestMatchers( "/h2-console/**")
							.permitAll()

						.requestMatchers("/api/v1/**")
								.hasRole(Role.USER.name()) // '/api/v1/**' 경로는 USER 역할이 필요

						.requestMatchers("/admin/**").hasRole(Role.MASTER.name())   // 관리자 권한들 MASTER 권한 필요
						.requestMatchers("/test1").hasAnyRole(Role.MASTER.name())
//						.requestMatchers("/admin/**").hasRole(Role.MASTER.name()) // '/api/v1/**' 경로는 USER 역할이 필요
//						.requestMatchers("/admin/users").hasRole(Role.MASTER.getKey())
						.anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
				)
				.formLogin(formLogin -> formLogin
						.loginPage("/login") // 사용자 정의 로그인 페이지 URL
						.loginProcessingUrl("/perform_login") // 로그인 폼 submit 시 데이터가 처리되는 URL
						.defaultSuccessUrl("/home", true) // 로그인 성공 후 리다이렉트할 기본 URL
						//.failureUrl("/login?error=true") // 로그인 실패 시 리다이렉트할 URL
						.permitAll() // 모든 사용자가 로그인 페이지에 접근할 수 있도록 허용
				)
				.logout(logout -> logout
						.logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 페이지
				)
				.oauth2Login(oauth2 -> oauth2
						.userInfoEndpoint(userInfo -> userInfo
								.userService(customOAuth2UserService) // Custom OAuth2 사용자 서비스
						)
				)

			;

		return http.build();

	}

}
