package com.example.demo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User {
	private String user_id;					// 사용자 ID
    private String login_id;				// 로그인 ID
    private String user_privilege; 			// 사용자 권한
    private String user_name; 				// 사용자 이름
    private LocalDateTime last_login_date;	// 마지막 접속 시간
    
  @JsonIgnore
    private String password;				// 로그인 PW
    
}

