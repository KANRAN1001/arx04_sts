package com.example.demo.dto.dashboard;

import lombok.Data;
/*
 * @ 대시보드 DTO 클래스
 * 최근 일주일간의 행동 및 패턴 기반의 탐지 횟수를 차트로 나타내기 위한 DTO 클래스
 */
@Data
public class Week {
	private String date;		// 날짜
	private int count;			// 횟수
}
