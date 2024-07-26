package com.example.demo.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.User;

@Mapper
public interface UserDao {
	
	// user테이블 모든 행 반환
	public List<User> getAllUser();
	
    // DB id 가져오기
    public String getById(String login_id);
    
    // DB pw 가져오기
    public String getByPw(String login_id);
    
	// 유저 추가
	public int insertUser(User user);
	
	// 유저 수정
	public int updateUser(User user);
	
	// 유저 삭제
	public int deleteUser(String user_name);

	public void updateLastLoginDate(String loginId, LocalDateTime now);

	public void updateLastLoginDate(Map<String, Object> params);
	
	// 어드민 이니 활성 비활성은 없어도?? 될거 같아용 어떻게 생각하심??
	//활성-비활성
	//public int updatePrivilege(String user_name);
	
	
}
