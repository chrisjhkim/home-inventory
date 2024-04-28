package com.chrisjhkim.sweethome.member.service;

import com.chrisjhkim.sweethome.admin.web.AdminController;
import com.chrisjhkim.sweethome.member.entity.User;

import java.util.List;

public interface UserService {
	List<User> getAllUsers();

	void updateRole(AdminController.RoleUpdateRequestBody request);
}
