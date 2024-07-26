package com.example.demo.dashboard;

import lombok.Data;

/*
 * @ 대시보드 DTO 클래스
 * 행동기반 탐지 횟수를 정책번호 차트로 나타내기 위한 DTO 클래스
 */
@Data
public class Behavior {
	private String rule_no;		// 행동기반 정책 번호
	private int count;			// 행동기반 탐지 횟수
}
