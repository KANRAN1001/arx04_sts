package com.example.demo.dto;

import lombok.Data;

@Data
public class Log {

    private String type;
    private String rule_no;
    private String src_ip;
    private String src_port;
    private String dst_ip;
    private String dst_port;
    private String time_stamp;
    private Double violation_time;
    private Integer violation_cnt;
    private byte[] payload;

    public String getPayloadHex() {
        if (payload == null) return "";
        StringBuilder sb = new StringBuilder();
        for (byte b : payload) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString().trim();
    }
}
