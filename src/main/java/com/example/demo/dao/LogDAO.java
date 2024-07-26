package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Log;


@Mapper
public interface LogDAO {

	// 로그 테이블을 가지고 오는 메서드
	List<String> getTableName();
	
    /**
     * 특정 날짜 리스트에 해당하는 로그를 조회하는 메소드.
     * @param dates - 조회할 로그의 날짜 리스트 (형식: yyyyMMdd)
     * @return - 해당 날짜의 로그 리스트
     */
    List<Log> getAllLogsByDates(List<String> tableNames);

    /**
     * 기간과 필터링 조건에 따라 로그를 조회하는 메소드.
     */
    List<Log> getAllLogsByFilter(
            @Param("dates") List<String> dates,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime,
            @Param("src_ip") String src_ip,
            @Param("src_port") String src_port,
            @Param("dst_ip") String dst_ip,
            @Param("dst_port") String dst_port,
            @Param("type") String type,
            @Param("minViolationTime") Double minViolationTime,
            @Param("maxViolationTime") Double maxViolationTime,
            @Param("minViolationCnt") Integer minViolationCnt,
            @Param("maxViolationCnt") Integer maxViolationCnt,
            @Param("rule_no") Integer rule_no);
}
