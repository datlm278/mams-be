package com.example.mamsbe.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ImportInvoiceResponse {
    private Long id;
    private Long supplierId;
    private String supplierName;
    private Long employeeId;
    private String employeeName;
    private Long totalPrice;
    private Long status;
    private Timestamp createTime;
    private Timestamp updateTime;
}
