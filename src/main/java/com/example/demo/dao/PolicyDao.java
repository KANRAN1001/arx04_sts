package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Policy;

/*
 * @ Policy DAO 인터페이스
 * xml파일과 직접적으로 연동되어 쿼리문을 작성하는 DAO 인터페이스
 */
@Mapper
public interface PolicyDao {
	
	/*
	 * 정책 목록을 리스트로 반환해주는 인터페이스 메서드
	 * @return : List<Policy> 형식으로 정책을 반환
	 */ 
	public List<Policy> getAllPolicy();
	
	/*
	 * 해당 정책번호의 Policy 클래스 형식으로 반환해주는 인터페이스 메서드
	 * @param : String rule_no 반환받을 정책번호
	 * @return : Policy 형식으로 정책을 반환
	 */ 
	public Policy getPolicy(String rule_no);
	
	/*
	 * 정책 추가
	 * @param : Policy policy 추가할 정보가 담긴 dto 클래스
	 * @return : int 성공 및 실패 여부 판단
	 */ 
	public int addPolicy(Policy policy);
	
	/*
	 * 정책 수정
	 * @param : Policy policy 추가할 정보가 담긴 dto 클래스
	 * @return : int 성공 및 실패 여부 판단
	 */ 
	public int updatePolicy(Policy policy);
	
	/*
	 * 정책 활성화 ON/OFF 인터페이스 메서드 
	 * @param : String rule_no ON/OFF할 정책의 번호
	 * @return : int 성공 및 실패 여부 판단
	 */ 
	public int updateActivation(String rule_no);
	
	/*
	 * 정책 삭제 
	 * @param : String rule_no 삭제할 정책의 번호
	 * @return : int 성공 및 실패 여부 판단
	 */ 
	public int deletePolicy(String rule_no);
	
	
	/*
	 * 정책 검색
	 * @param : 
	 * @return : List<Policy> 검색한 정책을 반환해주는 리스트
	 */ 
	public List<Policy> searchPolicy(@Param("activation") String activation
									,@Param("rule_no") String rule_no
									,@Param("from_ip") String from_ip
									,@Param("to_ip") String to_ip
									,@Param("port") String port
									,@Param("txt1") String txt1
									,@Param("txt2") String txt2
									,@Param("txt3") String txt3
									,@Param("base_cnt") String base_cnt
									,@Param("base_sec") String base_sec
									,@Param("policy_name")  String policy_name
									,@Param("policy_info") String policy_info
									,@Param("rule_type") String rule_type
									,@Param("action_type") String action_type
									,@Param("from_create_date") String from_create_date
									,@Param("to_create_date") String to_create_date
									,@Param("from_modify_date") String from_modify_date
									,@Param("to_modify_date") String to_modify_date);
	
}



