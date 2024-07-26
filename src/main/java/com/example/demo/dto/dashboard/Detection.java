package com.example.demo.dto.dashboard;

import lombok.Data;

/*
 * @ 대시보드 DTO 클래스
 * 행동 및 패턴의 탐지 횟수를 차트로 나타내기 위한 DTO 클래스
 */
@Data
public class Detection {
	private String type;	// 정책의 타입
	private int count;	// 탐지 횟수
}
