package com.example.demo.dao;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Policy;
import com.example.demo.dto.dashboard.Behavior;
import com.example.demo.dto.dashboard.Detection;
import com.example.demo.dto.dashboard.IpLog;
import com.example.demo.dto.dashboard.LoggingTable;
import com.example.demo.dto.dashboard.Pattern;
import com.example.demo.dto.dashboard.Week;

/*
 * @ 대시보드 DAO 인터페이스
 * xml파일과 직접적으로 연동되어 쿼리문을 작성하는 DAO 인터페이스
 */
@Mapper
public interface DashboardDao {

	/*
	 * 당일 행동 및 패턴기반  탐지 횟수를 List로 반환해주는 인터페이스
	 * @param  : String today 쿼리문에 동적으로 테이블을 조회할 수 있게 해주는 역할
	 * @return : List<Detection> 리스트 형식으로 탐지 타입별로 횟수를 반환
	 */ 
	public List<Detection> getTodayDetectionsCount(String today);
	
	/*
	 * 당일 행동기반 탐지 횟수를 정책별로 List로 반환해주는 인터페이스
	 * @param  : String today 쿼리문에 동적으로 테이블을 조회할 수 있게 해주는 역할
	 * @return : List<Behavior> 리스트 형식으로 행동기반 탐지 정책번호와 횟수를 반환
	 */ 
	public List<Behavior> getTodayBehaviorCount(String today);
	
	/*
	 * 당일 패턴기반 탐지 횟수를 정책별로 List로 반환해주는 인터페이스
	 * @param : String today 쿼리문에 동적으로 테이블을 조회할 수 있게 해주는 역할
	 * @return : List<Pattern> 리스트 형식으로 패턴기반 탐지 정책번호와 횟수를 반환
	 */
	public List<Pattern> getTodayPatternCount(String today);
	
	/*
	 * 당일 기준으로 일주일전까지 행동기반 탐지 횟수 List 반환 인터페이스
	 * @param  : List<String> list 날짜를 문자열로 리스트에 담아 매개변수에 전달
	 * @return : List<Week> 날짜별로 행동기반 탐지 횟수를 반환
	 */ 
	public List<Week> getWeekBehaviorCount(@Param("list")List<String> list);
	
	/*
	 * 당일 기준으로 일주일전까지 패턴기반 탐지 횟수 List 반환 인터페이스
	 * @param  : List<String> list 날짜를 문자열로 리스트에 담아 매개변수에 전달 
	 * @return : List<Week> 날짜별로 패턴기반 탐지 횟수를 반환
	 */ 
	public List<Week> getWeekPatternCount(@Param("list")List<String> list);
	
	
	/*
	 * 당일 기준으로 일주일전까지 로깅횟수를 최근 5건 List 반환 인터페이스
	 * @return : List<Week> 날짜별로 패턴기반 탐지 횟수를 반환
	 */ 
	public List<LoggingTable> getWeekLogging();
	
	/*
	 * 당일 기준으로 IP별 탐지 횟수 List 반환 인터페이스
	 * @param  : List<String> list 날짜를 문자열로 리스트에 담아 매개변수에 전달 
	 * @return : List<Week> 날짜별로 패턴기반 탐지 횟수를 반환
	 */ 
	public List<IpLog> getIpLogging();
	
	/* public List<Policy> getActivePolicy(); */
}
