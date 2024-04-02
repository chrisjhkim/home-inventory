package com.chrisjhkim.sweethome.sample.thymeleaf;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sample/thymeleaf")
public class ThymeleafSampleController {



	@GetMapping("/all")
	public String sampleAll(Model model,
	                        HttpSession session){
		// <h1>String 출력 text / utext</h1>
		model.addAttribute("simpleString", "Hello <b>Thymeleaf</b>");

		// <h1>SpringEL 표현식</h1>
		// <h1>지역변수 사용 - (th:with)</h1>
		User user1 = new User("user1", 50);
		User userA = new User("userA", 10);
		User userB = new User("userB", 20);

		model.addAttribute("user1", user1);
		model.addAttribute("userList", List.of(userA, userB));
		model.addAttribute("userMap", Map.of("userA", userA, "userB", userB));

		// <h1>편의 객체</h1>
		session.setAttribute("mySessionData", "some data");

		// LocalDateTime
		model.addAttribute("localDateTime", LocalDateTime.now());

		// URL 링크
		model.addAttribute("param1", "data1");
		model.addAttribute("param2", "data2");

		// 리터럴
		model.addAttribute("literalData", "Spring!");

		// 연산 operation
		model.addAttribute("operationNullData", null);
		model.addAttribute("operationData", "Spring!");

		// 반복
		model.addAttribute("repeatUserList", List.of(
				new User("userA", 10),
				new User("userB", 20),
				new User("userC", 30)
		));

		// if, unless
		model.addAttribute("ifUnlessUserList", List.of(
				new User("userA", 10),
				new User("userB", 20),
				new User("userC", 30)
		));

		// 주석 예시
		model.addAttribute("commentsData", "Spring!");

		// block
		model.addAttribute("blockUserList", List.of(
				new User("userA", 10),
				new User("userB", 20),
				new User("userC", 30)
		));

		// javascript
		model.addAttribute("javascriptUser", new User("userA", 10));
		model.addAttribute("javascriptUserList", List.of(
				new User("userA", 10),
				new User("userB", 20),
				new User("userC", 30)
		));

		return "/sample/thymeleaf/main";

//		return "/sample/thymeleaf/template/fragment/fragmentMain";
	}


	@GetMapping("/template/fragment")
	public String template(){
		return "sample/thymeleaf/template/fragment/fragmentMain";
	}

	@GetMapping("/template/layout")
	public String layout(){
		return "sample/thymeleaf/template/layout/layoutMain";
	}
	@GetMapping("/template/layoutExtended")
	public String layoutExtended(){
		return "sample/thymeleaf/template/layoutExtended/layoutExtendedMain";
	}

	@Component("sampleBeanName")
	static class BeanForSample{
		public String methodFromBean(){
			return "method from bean !! ";
		}
	}
	@Data
	@AllArgsConstructor
	static class User{
		private String userName;
		private int age;

	}
}
