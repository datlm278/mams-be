package com.example.mamsbe.dto.request;

import lombok.Data;

@Data
public class ImportInvoiceMotorbikeRequest {

    private Long motorbikeId;
    private Long supplierId;
    private String code;
    private Long quantity;
    private Long unitPrice;
    private Long status;

}
