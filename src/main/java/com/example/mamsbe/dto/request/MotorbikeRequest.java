package com.example.mamsbe.dto.request;

import lombok.Data;

@Data
public class MotorbikeRequest {

    private Long id;
    private String name;
    private String description;
    private String image;
    private Long importPrice;
    private Long salePrice;
    private Long motorbikeTypeId;
    private Long brandId;
}
