package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	/* Service 객체 */
	private final UserService userService;

	/* 생성자 */
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/*
	 * @brief : 로그인 페이지
	 * 
	 * @Mapping : "signin"
	 * 
	 * @param : HttpSession 세션관리
	 */
	@GetMapping("/user/signin")
	public String login(HttpSession session) {

		String user = (String) session.getAttribute("user");

		if (user == null) {
			//session.setMaxInactiveInterval(30);
			return "/user/signin";

		} else {
			//System.out.println("이미 로그인 한 사용자 임 : " + user);

			//session.setMaxInactiveInterval(30);
			return "redirect:dashboard";
		}
	} // login funtion END

	/*
	 * @brief : 로그인 시 DB와 비교하여 세션 생성
	 * 
	 * @Mapping "login"
	 */
	@PostMapping("/user/signin")
	public String login(@RequestParam("login_id") String login_id, @RequestParam("password") String password,
			HttpSession session, Model model) {

		System.out.println("session id :" + session.getId());
		System.out.println("create time : " + session.getCreationTime());
		// 자동으로 끊김
		session.setMaxInactiveInterval(30);
		System.out.println("max Incative Interval : " + session.getMaxInactiveInterval());

		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println("passwd : " + bcryptPasswordEncoder.encode(password));
		String DB_ID = userService.getById(login_id);

		String DB_PW = userService.getByPw(login_id);

		// TODO : loginId, passwd 입력체크

		System.out.println("입력받은 id: " + login_id);
		System.out.println("입력받은 pw: " + password);
		System.out.println("DB받은 id: " + DB_ID);
		System.out.println("DB받은 pw: " + DB_PW);

		String user = userService.getById(login_id);

		String message;
		if (login_id == null) {
			message = "없는 사용자 입니다.";
			model.addAttribute("message", message);
			return "/user/signin";
		} else if (!bcryptPasswordEncoder.matches(password, DB_PW)) {
			message = "비밀번호가 틀렸습니다.";
			model.addAttribute("message", message);
			return "/user/signin";
		}

		// 로그인 성공
		// 세션에 사용자 정보 추가
		session.setAttribute("user", user);

		return "dashboard";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    String loginId = (String) session.getAttribute("user");

	    if (loginId != null) {
	        // 사용자 정보를 데이터베이스에서 업데이트
	        userService.updateLastLoginDate(loginId);
	    }

	    // 세션 무효화
	    session.invalidate();

	    // 로그인 페이지로 리다이렉트
	    return "redirect:/user/signin";
	}
	

	@GetMapping("userManager")
	public String userManager(Model model) {
		List<User> user = new ArrayList<>();
		user = userService.getAllUser();
		model.addAttribute("user", user);
		return "user/userManager";
	}

	// 유저 추가 페이지로 넘어감
	@GetMapping("/insertUser")
	public String insertuser() {
		return "user/insertUser";
	}

	// 유저 추가-> 유저 관리자 페이지
	
	@PostMapping("/insertuser")
	public String adduser(@ModelAttribute User user) {
		System.out.println("돼라");
		userService.insertUser(user);
		return "redirect:userManager";
	}
//		 
//
//	
//	
	@GetMapping("modify/{user_id}/{login_id}/{password}/{user_privilege}/{user_name}/{last_login_date}")
	public String modifyDepartments(@PathVariable("user_id") String user_id ,@PathVariable("login_id") String login_id, @PathVariable("password") String password,
			@PathVariable("user_privilege") String user_privilege, @PathVariable("user_name") String user_name, @PathVariable("last_login_date") String last_login_date
			) {
		System.out.println("받은거" + user_id);
		System.out.println("받은거" + login_id);
		System.out.println("받은거" + password);
		System.out.println("받은거" + user_privilege);
		System.out.println("받은거" + user_name);
		System.out.println("받은거" + last_login_date);

		//TODO : 수정 페이지 호출	
		return "user/modifyUser";
	}
	
	
	@GetMapping("/modifyUser")
	public String modify(@ModelAttribute User user) {
		userService.updateUser(user);
		return "redirect:/userManager";
	}
	
//	
	@PostMapping("/jqueryDelete")
	public ResponseEntity<?> deleteUser(@RequestBody Map<String, String> request) {
	    String userName = request.get("user_name");
	    userService.deleteUser(userName);  // 사용자 삭제 로직
	    return ResponseEntity.ok().build();
	}

	

}	