package com.chrisjhkim.sweethome.login.web.controller;

import com.chrisjhkim.sweethome.login.service.LoginService;
import com.chrisjhkim.sweethome.login.web.dto.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;



	@GetMapping("/login")
	public String loginForm(@ModelAttribute LoginForm loginForm){
		return "login/loginForm";
	}


}
