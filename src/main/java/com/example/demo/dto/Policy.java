package com.example.demo.dto;

import lombok.Data;

/*
 * @ Policy DTO 클래스
 * 데이터베이스의 테이블의 형식과 맞춘 dto 클래스
 */
@Data
public class Policy {
    private int activation;      	 // 활성화/비활성화
    private String rule_no;          // 정책 번호
    private String from_ip;          // 탐지 IP
    private String to_ip;            // 탐지 IP
    private String port;             // 탐지 port
    private String txt1;             // 탐지 룰
    private String txt2;             // 탐지 룰
    private String txt3;             // 탐지 룰
    private int base_cnt;        	 // 기준 횟수
    private int base_sec;        	 // 기준 초
    private String policy_name;      // 정책 이름
    private String policy_info;      // 정책 정보
    private int rule_type;       	 // 행동 / 패턴
    private int action_type;     	 // 차단, 격리, 패스, 탐지
    private String create_date;      // 정책 생성 일
    private String modify_date;      // 정책 수정 일
}
