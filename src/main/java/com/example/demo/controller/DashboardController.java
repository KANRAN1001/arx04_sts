package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.dashboard.Behavior;
import com.example.demo.dto.dashboard.Detection;
import com.example.demo.dto.dashboard.IpLog;
import com.example.demo.dto.dashboard.LoggingTable;
import com.example.demo.dto.dashboard.Pattern;
import com.example.demo.dto.dashboard.Week;
import com.example.demo.service.DashboardService;

/*
 * @ 대시보드 컨드롤러
 * 클라이언트의 요청을 받아 model과 view를 컨트롤 하는 클래스 
 */
@Controller
public class DashboardController {
	// DashboardService 객체 생성
	private final DashboardService dashboardService;

	// 생성자
	public DashboardController(DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}

	/*
	 * 대시보드 화면 요청을 받는 매핑 메서드
	 */
	@GetMapping("/dashboard")
	public String policy(Model model) {

		// 당일 날을 구해서 변수에 담은 후
		LocalDate currentDate = LocalDate.now();
		// yyyymmdd 형식으로 변환하기 위해 변환 변수 사용
		DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		// 매개변수로 주기 위해 오늘날짜를 문자열로 변환
		String today = currentDate.format(customFormatter);
		model.addAttribute("today", today);

		// 당일 행동 및 패턴의 로깅 갯수를 model로 html로 넘겨줌
		List<Detection> list = dashboardService.getTodayDetectionsCount(today); // 매개변수가 xml의 변수로 들어감
		model.addAttribute("list", list); // html에 model객체로 전달

		// 당일 행동기반 로깅 갯수를 정책별로 model로 html로 넘겨줌
		List<Behavior> behavior = dashboardService.getTodayBehaviorCount(today);
		model.addAttribute("behavior", behavior);

		// 당일 패턴기반 로깅 갯수를 정책별로 model로 html로 넘겨줌
		List<Pattern> pattern = dashboardService.getTodayPatternCount(today);
		model.addAttribute("pattern", pattern);

		// 일주일간의 행동기반 로깅 갯수를 model로 html로 넘겨줌
		List<Week> b_date = dashboardService.getWeekBehaviorCount();
		model.addAttribute("b_date", b_date);
		// 일주일간의 행동기반 로깅 갯수를 합쳐서 넘겨줌
		int week_behavior_count = 0;
		for (int i = 0; i < b_date.size(); i++) {
			week_behavior_count += b_date.get(0).getCount();
		}
		model.addAttribute("week_behavior_count", week_behavior_count);

		// 일주일간의 패턴기반 로깅 갯수를 model로 html로 넘겨줌
		List<Week> p_date = dashboardService.getWeekPatternCount();
		model.addAttribute("p_date", p_date);
		// 일주일간의 패턴기반 로깅 갯수를 합쳐서 넘겨줌
		int week_pattern_count = 0;
		for (int i = 0; i < p_date.size(); i++) {
			week_pattern_count += p_date.get(0).getCount();
		}
		model.addAttribute("week_pattern_count", week_pattern_count);

		// 당일 행동기반 로깅 갯수를 구해서 넘겨줌
		int today_behavior_count = 0;
		for (int i = 0; i < behavior.size(); i++) {
			today_behavior_count += behavior.get(i).getCount();
		}
		model.addAttribute("today_behavior_count", today_behavior_count);

		// 당일 패턴기반 로깅 갯수를 구해서 넘겨줌
		int today_pattern_count = 0;
		for (int i = 0; i < pattern.size(); i++) {
			today_pattern_count += pattern.get(i).getCount();
		}
		model.addAttribute("today_pattern_count", today_pattern_count);

		// 최근 로깅 5건을 받아서 넘겨줌
		List<LoggingTable> loggingTable = dashboardService.getWeekLogging();
		model.addAttribute("loggingTable", loggingTable);
		System.out.println(loggingTable);

		// IP별 로깅 횟수를 넘겨줌
		List<IpLog> ipLogging = dashboardService.getIpLogging();
		model.addAttribute("ipLogging", ipLogging);

		return "dashboard";
	}

}