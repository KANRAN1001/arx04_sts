package com.example.demo.dashboard;

import lombok.Data;


/*
 * @ 대시보드 DTO 클래스
 * 최근 5건의 로깅 건수를 나타내기위한 DTO 클래스
 */
@Data
public class LoggingTable {
	private String type;
	private String rule_no;
	private String src_ip;
	private String time_stamp;
	private String payload;
}
