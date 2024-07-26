package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Policy;
import com.example.demo.service.PolicyService;

/*
 * Policy Controller 클래스
 * 정책 조회, 추가, 수정 요청
 */
@Controller
public class PolicyController {

	/* PolicyService 객체 생성 */
	private final PolicyService policyService;

	/* 생성자 */
	public PolicyController(PolicyService policyService) {
		this.policyService = policyService;
	}

	/*
	 * 정책 관리 화면 요청을 받는 메서드
	 */
	@GetMapping("/policyManager")
	public String policyManager(Model model) {

		List<Policy> policy = policyService.getAllPolicy();
		model.addAttribute("policy", policy);
		return "policy/policyManager";
	}

	/*
	 * 정책 추가 화면 요청을 받는 메서드
	 */
	@GetMapping("/insertPolicy")
	public String insertPolicy() {
		return "policy/insertPolicy";
	}


	/*
	 * 정책 추가 후 정책관리 화면을 요청을 받는 메서드
	 */
	@GetMapping("/insertpolicy")
	public String joind(@ModelAttribute Policy policy) {
		policyService.addPolicy(policy);
		System.out.println(policy.getPolicy_info());
		// udp 신호 테스트
		policyService.policyUdp();
		return "redirect:policyManager";
	}

	/*
	 * 정책 수정 화면 요청을 받는 메서드
	 * @param : @Pathvariable(rule_no) URL주소의 매핑된 rule_no를 매치 시켜주는 매개변수
	 */
	@PostMapping("/modify/{rule_no}")
	public String modifyDepartments(@PathVariable("rule_no") String rule_no, Model model) {

		
		Policy policy = policyService.getPolicy(rule_no);
		model.addAttribute("policy", policy);

		// 수정 페이지 호출
		return "policy/modifyPolicy";
	}

	/*
	 * 정책 수정 후 정책관리 화면 요청을 받는 메서드 
	 */
	@GetMapping("/modify")
	public String modify(@ModelAttribute Policy policy) {
		policyService.updatePolicy(policy);
		// 정책 관리 페이지 호출 
		return "redirect:policyManager";
	}

	/*
	 * 정책을 비동기로 삭제시켜주는 메서드
	 */
	@PostMapping("/delete_policy")
	public Boolean delete_dept(@RequestBody Policy policy) {
		System.out.println("받은거 : " + policy.getRule_no());
		try {
			policyService.deletePolicy(policy.getRule_no());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/*
	 * 정책을 비활성화 및 활성화 해주는 메서드
	 */
	@PostMapping("/update_activation")
	@ResponseBody
	public Boolean update_Act(@RequestBody Policy policy) {
		try {
			policyService.updateActivation(policy.getRule_no());
			policyService.policyUdp();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * 정책검색 컨트롤러
	 */
	@GetMapping("/search_policy")
	public String searchPolicy(@RequestParam(value = "activation", required = false) String activation,
			@RequestParam(value = "rule_no", required = false) String rule_no,
			@RequestParam(value = "from_ip", required = false) String from_ip,
			@RequestParam(value = "to_ip", required = false) String to_ip,
			@RequestParam(value = "port", required = false) String port,
			@RequestParam(value = "txt1", required = false) String txt1,
			@RequestParam(value = "txt2", required = false) String txt2,
			@RequestParam(value = "txt3", required = false) String txt3,
			@RequestParam(value = "base_cnt", required = false) String base_cnt,
			@RequestParam(value = "base_sec", required = false) String base_sec,
			@RequestParam(value = "policy_name", required = false) String policy_name,
			@RequestParam(value = "policy_info", required = false) String policy_info,
			@RequestParam(value = "rule_type", required = false) String rule_type,
			@RequestParam(value = "action_type", required = false) String action_type,
			@RequestParam(value = "from_create_date", required = false) String from_create_date,
			@RequestParam(value = "to_create_date", required = false) String to_create_date,
			@RequestParam(value = "from_modify_date", required = false) String from_modify_date,
			@RequestParam(value = "to_modify_date", required = false) String to_modify_date, Model model) {

		// 입력 받은 매개변수를 확인하기 위해 로깅
		/*
		 * System.out.println("Activation: " + activation);
		 * System.out.println("Rule Number: " + rule_no); System.out.println("From IP: "
		 * + from_ip); System.out.println("To IP: " + to_ip);
		 * System.out.println("Port: " + port); System.out.println("Text 1: " + txt1);
		 * System.out.println("Text 2: " + txt2); System.out.println("Text 3: " + txt3);
		 * System.out.println("Base Count: " + base_cnt);
		 * System.out.println("Base Seconds: " + base_sec);
		 * System.out.println("Policy Name: " + policy_name);
		 * System.out.println("Policy Info: " + policy_info);
		 * System.out.println("Rule Type: " + rule_type);
		 * System.out.println("Action Type: " + action_type);
		 * System.out.println("from_create_date " + from_create_date);
		 * System.out.println("to_create_date " + to_create_date);
		 * System.out.println("from_modify_date " + from_modify_date);
		 * System.out.println("to_modify_date " + to_modify_date);
		 */

		List<Policy> search = policyService.searchPolicy(activation, rule_no, from_ip, to_ip, port, txt1, txt2, txt3,
				base_cnt, base_sec, policy_name, policy_info, rule_type, action_type, from_create_date, to_create_date,
				from_modify_date, to_modify_date);

		// 검색 결과를 모델에 추가
		model.addAttribute("policy", search);
		// 결과를 보여줄 뷰의 이름을 리턴
		return "/policy/policyManager"; 
	}

}
