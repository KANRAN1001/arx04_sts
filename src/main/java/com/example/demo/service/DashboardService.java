package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.DashboardDao;
import com.example.demo.dto.dashboard.Behavior;
import com.example.demo.dto.dashboard.Detection;
import com.example.demo.dto.dashboard.IpLog;
import com.example.demo.dto.dashboard.LoggingTable;
import com.example.demo.dto.dashboard.Pattern;
import com.example.demo.dto.dashboard.Week;

/*
 * @ 대시보드 Service 클래스
 * 대시보드 인터페이스를 주입받아 로직을 구현하는 클래스
 */
@Service
public class DashboardService {

	// DashboardDao 객체 생성
	private final DashboardDao dashboardDao;
	// 생성자
	public DashboardService(DashboardDao dashboardDao) {
		this.dashboardDao = dashboardDao;
	}
	
	// 최근 7일간의 테이블 네임을 가져오는 쿼리 테이블이 있을경우
	public List<String> getWeekTable() {
		return dashboardDao.getWeekTable();
	}
	
	/*
	 * 당일 행동 및 패턴기반 탐지 횟수를 List로 반환해주는 서비스 메서드
	 * @param  : String today 쿼리문에 동적으로 테이블을 조회할 수 있게 해주는 역할
	 * @return : List<Detection> 리스트 형식으로 탐지 타입별로 횟수를 반환
	 */
	public List<Detection> getTodayDetectionsCount(String today) {
		return dashboardDao.getTodayDetectionsCount(today);
	}
	
	/*
	 * 당일 행동기반 탐지 횟수를 정책별로 List로 반환해주는 서비스 메서드
	 * @param  : String today 쿼리문에 동적으로 테이블을 조회할 수 있게 해주는 역할
	 * @return : List<Behavior> 리스트 형식으로 행동기반 탐지 정책번호와 횟수를 반환
	 */ 
	public List<Behavior> getTodayBehaviorCount(String today) {
		List<Behavior> behavior = dashboardDao.getTodayBehaviorCount(today);
		if ( behavior.isEmpty()) {
			behavior.add(new Behavior("0", 0));
		}	
		return behavior;
	}
	
	public List<Pattern> getTodayPatternCount(String today) {
		 List<Pattern> pattern =  dashboardDao.getTodayPatternCount(today);
		 if ( pattern.isEmpty()) {
			 pattern.add(new Pattern("0",0));
		 }
		 return pattern;
	}
	
	/*
	 * 당일 기준으로 일주일전까지 행동기반 탐지 횟수 List 반환해주는 서비스 메서드
	 * @param  : List<String> list 날짜를 문자열로 리스트에 담아 매개변수에 전달
	 * @return : List<Week> 날짜별로 행동기반 탐지 횟수를 반환
	 */ 
	public List<Week> getWeekBehaviorCount() {
		
		  int i = 7;
	      LocalDate currentDate = LocalDate.now();
	      DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	      List<String> date = new ArrayList<String>();
	      while(i-- > 0) {
	    	  date.add(currentDate.minusDays(i).format(customFormatter));
	      }
		return dashboardDao.getWeekBehaviorCount(date);
	}
	
	/*
	 * 당일 기준으로 일주일전까지 패턴기반 탐지 횟수 List 반환해주는 서비스 메서드
	 * @param  : List<String> list 날짜를 문자열로 리스트에 담아 매개변수에 전달 
	 * @return : List<Week> 날짜별로 패턴기반 탐지 횟수를 반환
	 */ 
	public List<Week> getWeekPatternCount() {	
		  int i = 7;
	      LocalDate currentDate = LocalDate.now();
	      DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	      List<String> date = new ArrayList<String>();
	      while(i-- > 0) {
	    	  date.add(currentDate.minusDays(i).format(customFormatter));
	      }
		return dashboardDao.getWeekPatternCount(date);
	}
	
	/*
	 * 당일 기준으로 일주일전까지 로깅횟수를 최근 5건 List 반환해주는 서비스 메서드
	 * @return : List<Week> 날짜별로 패턴기반 탐지 횟수를 반환
	 */ 
	public List<LoggingTable> getWeekLogging() {
		return dashboardDao.getWeekLogging();
	}
	
	/*
	 * 당일 기준으로 IP별 탐지 횟수 List 반환해주는 서비스 메서드
	 * @param  : List<String> list 날짜를 문자열로 리스트에 담아 매개변수에 전달 
	 * @return : List<Week> 날짜별로 패턴기반 탐지 횟수를 반환
	 */ 
	public List<IpLog> getIpLogging() {
		return dashboardDao.getIpLogging();
	}
	
	/*
	 * public List<Policy> getActivePolicy() { return
	 * dashboardDao.getActivePolicy(); }
	 */
	

	
}
