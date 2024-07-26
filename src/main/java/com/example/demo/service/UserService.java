package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.User;

@Service
public class UserService {

	/* UserDao 객체 */ 
	private final UserDao userDao;
	 
	
	
	/* 생성자 */ 
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	/* 유저 목록 출력 */ 
	public List<User>getAllUser() {
		return userDao.getAllUser();
	}
	
	/* 입력한 ID를 통해 DB에 등록된 ID 가져오기 */
	public String getById(String login_id) {
		return userDao.getById(login_id);
	}
	
	/* 패스워드 가져오기 */ 
	public String getByPw(String password) {
		return userDao.getByPw(password);
	}

	/* 유저 추가 */
	@Transactional
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}
	
	/* 유저 삭제 */
	@Transactional
	public int deleteUser(String user_name) {
		return userDao.deleteUser(user_name);
	}
	
	/* 유저 수정 */
	@Transactional
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}
	
	
	
	
	 public void updateLastLoginDate(String loginId) {
	        // 현재 시간을 가져옵니다
	        LocalDateTime now = LocalDateTime.now();

	        // 파라미터를 맵으로 설정합니다
	        Map<String, Object> params = new HashMap<>();
	        params.put("loginId", loginId);
	        params.put("lastLoginDate", now);

	        // DAO를 통해 DB 업데이트
	        userDao.updateLastLoginDate(params);
	    }
	
	
//	@Transactional
//	public int addUser(String user_name) {
//		return userDao.updatePrivilege(user_name);
//	}
	
	

	
	
}


