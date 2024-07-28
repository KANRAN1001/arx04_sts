package com.example.demo.dto.dashboard;

import lombok.Data;

/*
 * @ 대시보드 DTO 클래스
 * 패턴기반 탐지 횟수를 정책번호별 횟수를 차트로 나타내기 위한 DTO 클래스
 */
@Data
public class Pattern {
	private String rule_no;		// 패턴기반 정책 번호
	private int count;		// 패턴기반 탐지 횟수
	
	public Pattern(String rule_no, int count) {
        this.rule_no = rule_no;
        this.count = count;
	}
}
