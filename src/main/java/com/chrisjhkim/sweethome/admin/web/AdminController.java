package com.chrisjhkim.sweethome.admin.web;

import com.chrisjhkim.sweethome.member.entity.Role;
import com.chrisjhkim.sweethome.member.entity.User;
import com.chrisjhkim.sweethome.member.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

	private final UserService userService;

	@ResponseBody
	@GetMapping("/test1")
	public String test(){
		return "OK";
	}

	@GetMapping("/admin/users")
	public String listUsers(Model model) {
		List<UserDto> users = userService.getAllUsers().stream()
				.map(UserDto::from)
				.toList();

		System.out.println("users = " + users);

		model.addAttribute("users", users);
		return "admin";
	}


	@ResponseBody
	@PostMapping("/admin/updateRole")
	public String updateRole(@RequestBody RoleUpdateRequestBody request) {
		log.info("request = {}", request);
		// 데이터베이스 로직을 통해 사용자의 역할 업데이트
		// 예시로는 서비스 계층의 메소드 호출
		// 예: userService.updateUserRole(request.getEmail(), request.getRole());
		userService.updateRole(request);
		return "Role updated for " + request.getEmail();
	}

	// RoleUpdateRequestBody 정의
	@Data
	public static class RoleUpdateRequestBody {
		private Role role;
		private String email;

	}
}
