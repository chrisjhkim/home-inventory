package com.chrisjhkim.sweethome.config.auth.dto;

import com.chrisjhkim.sweethome.member.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
	private String name;
	private String email;
	private String picture;

	public SessionUser(User user) {
		if ( user != null ) {
			this.name = user.getName();
			this.email = user.getEmail();
			this.picture = user.getPicture();

		}
	}

}
