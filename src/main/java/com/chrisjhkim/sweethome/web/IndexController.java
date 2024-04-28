package com.chrisjhkim.sweethome.web;

import com.chrisjhkim.sweethome.config.auth.LoginUser;
import com.chrisjhkim.sweethome.config.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(Model model, @LoginUser SessionUser user) {
//		model.addAttribute("posts", postsService.findAllDesc());
		if (user != null) {
			model.addAttribute("userName", user.getName());
		}
		return "index";
	}
}
