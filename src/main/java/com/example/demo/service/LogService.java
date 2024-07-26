package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.LogDAO;
import com.example.demo.dto.Log;


@Service
public class LogService {

    private final LogDAO logDao;

   
    public LogService(LogDAO logDao) {
        this.logDao = logDao;
    }

    public List<String> getTableName() {
    	return logDao.getTableName();
    }
    
    /**
     * 특정 날짜 리스트에 해당하는 로그를 조회합니다.
     * @param dates - 조회할 로그의 날짜 리스트 (형식: yyyyMMdd)
     */
    public List<Log> getAllLogsByDates(List<String> tableNames) {
        return logDao.getAllLogsByDates(tableNames);
    }

    /**
     * 기간과 필터링 조건에 따라 로그를 조회합니다.
     */
    public List<Log> getLogsByFilter(
            List<String> dates, 
            String startTime, 
            String endTime,
            String srcIp, 
            String srcPort, 
            String dstIp, 
            String dstPort, 
            String type, 
            Double minViolationTime, 
            Double maxViolationTime, 
            Integer minViolationCnt, 
            Integer maxViolationCnt, 
            Integer ruleNo) {
        return logDao.getAllLogsByFilter(dates, startTime, endTime, srcIp, srcPort, dstIp, dstPort, type, minViolationTime, maxViolationTime, minViolationCnt, maxViolationCnt, ruleNo);
    }
}
