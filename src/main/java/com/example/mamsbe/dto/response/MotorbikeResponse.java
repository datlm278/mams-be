package com.example.mamsbe.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MotorbikeResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
    private Long importPrice;
    private Long salePrice;
    private Long motorbikeTypeId;
    private String motorbikeType;
    private Long brandId;
    private String brand;
    private Timestamp createTime;
    private Timestamp updateTime;
}
