package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.Log;
import com.example.demo.service.LogService;

@Controller
public class LogController {

    // LogService 인스턴스 주입
    private final LogService logService;

    // 생성자 주입을 통해 LogService를 초기화
    public LogController(LogService logService) {
        this.logService = logService;
        System.out.println("git test");
    }

    /**
     * 현재 날짜의 로그를 조회하는 메소드
     * @param model - View에 전달할 데이터 모델
     * @return - 로그를 표시할 뷰의 이름
     */
    @GetMapping("/Log")
    public String showLog(Model model) {
        try {
        	List<String> tableNames = logService.getTableName();
        	System.out.println(tableNames);
        	
        	//model.addAttribute("tableNames", tableNames);
        	
        	
            // 현재 날짜를 yyyyMMdd 형식으로 포맷하여 리스트에 추가
            List<String> dates = Arrays.asList(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            
            // 날짜를 기준으로 로그를 조회
            List<Log> log = logService.getAllLogsByDates(tableNames);
            
            // 조회된 로그를 모델에 추가
            model.addAttribute("log", log);
        } catch (Exception e) {
            // 예외 발생 시 경고 메시지를 모델에 추가
            model.addAttribute("warning", "로그 조회 중 문제가 발생했습니다. 관리자에게 문의하십시오.");
        }
        return "Log"; // 뷰의 이름을 반환
    }

    /**
     * 기간 및 필터링 조건에 따라 로그를 조회하는 메소드
     */
    @GetMapping("/LogByPeriod")
    public String showLogByPeriod(
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            @RequestParam(value = "srcIp", required = false) String srcIp,
            @RequestParam(value = "srcPort", required = false) String srcPort,
            @RequestParam(value = "dstIp", required = false) String dstIp,
            @RequestParam(value = "dstPort", required = false) String dstPort,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "minViolationTime", required = false) Double minViolationTime,
            @RequestParam(value = "maxViolationTime", required = false) Double maxViolationTime,
            @RequestParam(value = "minViolationCnt", required = false) Integer minViolationCnt,
            @RequestParam(value = "maxViolationCnt", required = false) Integer maxViolationCnt,
            @RequestParam(value = "rule_no", required = false) Integer ruleNo,
            Model model) {

        try {
            // 시작 시간과 종료 시간 사이의 모든 날짜를 계산
            List<String> dates = getDatesBetween(startTime, endTime);
            
            // 필터링 조건과 날짜를 기준으로 로그를 조회
            List<Log> log = logService.getLogsByFilter(dates, startTime, endTime, srcIp, srcPort, dstIp, dstPort, type, minViolationTime, maxViolationTime, minViolationCnt, maxViolationCnt, ruleNo);

            // 조회된 로그가 없으면 경고 메시지 추가
            if (log.isEmpty()) {
                model.addAttribute("warning", "없는 데이터입니다.");
            } else {
                // 조회된 로그를 모델에 추가
                model.addAttribute("log", log);
            }
        } catch (Exception e) {
            // 예외 발생 시 경고 메시지를 모델에 추가
            model.addAttribute("warning", "로그 기간 조회 중 문제가 발생했습니다.");
        }
        return "Log"; // 뷰의 이름을 반환
    }

    /**
     * 시작 시간과 종료 시간 사이의 모든 날짜를 계산하여 리스트로 반환하는 메소드
     * @param startTime - 시작 시간 (ISO 8601 형식)
     * @param endTime - 종료 시간 (ISO 8601 형식)
     * @return - 날짜 문자열 리스트
     */
    private List<String> getDatesBetween(String startTime, String endTime) {
        List<String> dates = new ArrayList<>();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // 시작 시간과 종료 시간을 LocalDate로 변환
        LocalDate startDate = LocalDate.parse(startTime, inputFormatter);
        LocalDate endDate = LocalDate.parse(endTime, inputFormatter);

        // 시작 날짜부터 종료 날짜까지 날짜를 계산하여 리스트에 추가
        while (!startDate.isAfter(endDate)) {
            dates.add(startDate.format(outputFormatter));
            startDate = startDate.plusDays(1);
        }

        return dates; // 날짜 리스트 반환
    }

    /**
     * 로그의 세부 사항을 조회하는 메소드
     * @param payload - 로그의 페이로드 (상세 정보)
     * @param model - View에 전달할 데이터 모델
     * @return - 로그 상세 정보를 표시할 뷰의 이름
     */
    @GetMapping("/LogDetail")
    public String getLogDetail(@RequestParam("payload") String payload, Model model) {
        // 페이로드를 모델에 추가
        model.addAttribute("payload", payload);
        return "logDetail"; // 로그 상세 정보를 표시할 뷰의 이름을 반환
    }
}
