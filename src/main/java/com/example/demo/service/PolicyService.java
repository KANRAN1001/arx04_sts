package com.example.demo.service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.PolicyDao;
import com.example.demo.dto.Policy;

/*
 * @ Policy Service 클래스
 * PolicyDao를 주입받아 로직을 구현하는 클래스
 */
@Service
public class PolicyService {
	// PolicyDao 객체 생성
	private final PolicyDao policyDao;

	// 생성자
	public PolicyService(PolicyDao policyDao) {
		this.policyDao = policyDao;
	}
	
	/*
	 * 정책 목록을 리스트로 반환해주는 서비스 메서드
	 * @return : List<Policy> 형식으로 정책을 반환
	 */ 
	  public List<Policy> getAllPolicy() { return policyDao.getAllPolicy(); }
	  
	/*
	 * 해당 정책번호의 Policy 클래스 형식으로 반환해주는 서비스 메서드
	 * @param : String rule_no 반환받을 정책번호
	 * @return : Policy 형식으로 정책을 반환
	 */ 
	  public Policy getPolicy(String rule_no) {
		  return policyDao.getPolicy(rule_no);
	  }

	/*
	 * 정책 추가
	 * @param : Policy policy 추가할 정보가 담긴 dto 클래스
	 * @return : int 성공 및 실패 여부 판단
	 */ 
	@Transactional
	public int addPolicy(Policy policy) {
		return policyDao.addPolicy(policy);
	}

	/*
	 * 정책 수정
	 * @param : Policy policy 추가할 정보가 담긴 dto 클래스
	 * @return : int 성공 및 실패 여부 판단
	 */ 
	@Transactional
	public int updatePolicy(Policy policy) {
		return policyDao.updatePolicy(policy);
	}
	
	/*
	 * 정책 활성화 ON/OFF 서비스 메서드 
	 * @param : String rule_no ON/OFF할 정책의 번호
	 * @return : int 성공 및 실패 여부 판단
	 */ 
	@Transactional
	public int updateActivation(String rule_no) {
		return policyDao.updateActivation(rule_no);
	}
	
	
	/*
	 * 정책 삭제
	 * @param : String rule_no 삭제할 정책의 번호
	 * @return : int 성공 및 실패 여부 판단
	 */ 
	@Transactional
	public int deletePolicy(String rule_no) {
		return policyDao.deletePolicy(rule_no);
	}
	
	/*
	 * 정책 검색
	 * @param : 
	 * @return : List<Policy> 검색한 정책을 반환해주는 리스트
	 */ 
	public List<Policy> searchPolicy(
			String activation
			, String rule_no
			, String from_ip
			, String to_ip
			, String port
			, String txt1
			, String txt2
			, String txt3
			, String base_cnt
			, String base_sec
			, String policy_name
			, String policy_info
			, String rule_type
			, String action_type
			, String from_create_date
			, String to_create_date
			, String from_modify_date
			, String to_modify_date
			) {
		
		return policyDao.searchPolicy(activation, rule_no, from_ip, to_ip, port,txt1, txt2, txt3, base_cnt, base_sec, 
									policy_name, policy_info, rule_type, action_type, from_create_date, to_create_date,
									from_modify_date, to_modify_date);
	}

	
	/*
	 * 정책이 추가 및 수정 되었을 때 탐지 엔진에 UDP신호를 전송하는 서비스 메서드
	 * @return : int 성공 및 실패 여부 판단
	 */ 	
	public int policyUdp() {
		// 소켓 생성 및 serverport 전달
		try (DatagramSocket datagramSocket = new DatagramSocket()) {
			String serverAddress = "192.168.1.39";
			String hash_key = "policy_change";
			
			// 전송할 패킷 전달
			DatagramPacket datagramPacket = new DatagramPacket(hash_key.getBytes(), hash_key.length(), InetAddress.getByName(serverAddress), 9090);
			datagramSocket.send(datagramPacket);
			return 1;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

}
