package com.example.mamsbe.mapper;

import com.example.mamsbe.dto.request.ImportInvoiceMotorbikeRequest;
import com.example.mamsbe.dto.response.ImportInvoiceDetailResponse;
import com.example.mamsbe.dto.response.ImportInvoiceResponse;
import com.example.mamsbe.models.ImportInvoice;
import com.example.mamsbe.models.ImportInvoiceDetail;

public class ImportInvoiceMapper {

    public static ImportInvoice toEntity(ImportInvoiceMotorbikeRequest request) {
        ImportInvoice importInvoice = new ImportInvoice();
        importInvoice.setCode(request.getCode());
        importInvoice.setStatus(request.getStatus());
        return importInvoice;
    }

    public static ImportInvoiceResponse toDto(ImportInvoice entity) {
        ImportInvoiceResponse response = new ImportInvoiceResponse();
        response.setId(entity.getId());
        response.setEmployeeId(entity.getEmployee().getId());
        response.setEmployeeName(entity.getEmployee().getFullName());
        response.setSupplierId(entity.getSupplier().getId());
        response.setSupplierName(entity.getSupplier().getFullName());
        response.setStatus(entity.getStatus());
        response.setCreateTime(entity.getCreateTime());
        response.setUpdateTime(entity.getUpdateTime());
        return response;
    }
}
