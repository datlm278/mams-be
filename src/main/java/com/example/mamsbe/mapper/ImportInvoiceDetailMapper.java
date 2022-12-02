package com.example.mamsbe.mapper;

import com.example.mamsbe.dto.request.ImportInvoiceMotorbikeRequest;
import com.example.mamsbe.dto.response.ImportInvoiceDetailResponse;
import com.example.mamsbe.models.ImportInvoiceDetail;

public class ImportInvoiceDetailMapper {

    public static ImportInvoiceDetail toEntity(ImportInvoiceMotorbikeRequest request) {
        ImportInvoiceDetail invoiceDetail = new ImportInvoiceDetail();
        invoiceDetail.setQuantity(request.getQuantity());
        invoiceDetail.setUnitPrice(request.getUnitPrice());
        return invoiceDetail;
    }

    public static ImportInvoiceDetailResponse toDto(ImportInvoiceDetail entity) {
        ImportInvoiceDetailResponse response = new ImportInvoiceDetailResponse();
        response.setId(entity.getId());
        response.setMotorbikeId(entity.getMotorbike().getId());
        response.setMotorbikeName(entity.getMotorbike().getName());
        response.setQuantity(entity.getQuantity());
        response.setUnitPrice(entity.getUnitPrice());
        response.setStatus(entity.getStatus());
        response.setCreateTime(entity.getCreateTime());
        response.setUpdateTime(entity.getUpdateTime());
        return response;
    }
}
