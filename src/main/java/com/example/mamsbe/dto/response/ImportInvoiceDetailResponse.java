package com.example.mamsbe.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ImportInvoiceDetailResponse {
    private Long id;
    private Long motorbikeId;
    private String motorbikeName;
    private Long quantity;
    private Long unitPrice;
    private Long status;
    private Timestamp createTime;
    private Timestamp updateTime;
}
