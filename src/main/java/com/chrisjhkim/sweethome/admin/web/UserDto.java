package com.chrisjhkim.sweethome.admin.web;

import com.chrisjhkim.sweethome.member.entity.Role;
import com.chrisjhkim.sweethome.member.entity.User;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Long id;
	private String email;
	private String name;
	private Role role;


	public static UserDto from(User user) {
		UserDto result = new UserDto();
		result.id = user.getId();
		result.email = user.getEmail();
		result.name = user.getName();
		result.role = user.getRole();
		return result;
	}
}
