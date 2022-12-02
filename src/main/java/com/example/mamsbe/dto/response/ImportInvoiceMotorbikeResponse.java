package com.example.mamsbe.dto.response;

import lombok.Data;

@Data
public class ImportInvoiceMotorbikeResponse {

    private ImportInvoiceResponse importInvoice;
    private ImportInvoiceDetailResponse details;

}
