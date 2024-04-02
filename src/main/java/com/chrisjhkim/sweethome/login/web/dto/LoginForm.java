package com.chrisjhkim.sweethome.login.web.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class LoginForm {

	@NotEmpty
	private String loginId;

	@NotEmpty
	private String password;
}
