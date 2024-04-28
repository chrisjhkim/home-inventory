package com.chrisjhkim.sweethome.member.service;

import com.chrisjhkim.sweethome.admin.web.AdminController;
import com.chrisjhkim.sweethome.member.entity.Role;
import com.chrisjhkim.sweethome.member.entity.User;
import com.chrisjhkim.sweethome.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void updateRole(AdminController.RoleUpdateRequestBody request) {
		Optional<User> byEmail = userRepository.findByEmail(request.getEmail());

		byEmail.ifPresent(
				user -> user.setRole(request.getRole()) // TODO
		);

//		byEmail.get().setRole(request.getRole())
	}
}
