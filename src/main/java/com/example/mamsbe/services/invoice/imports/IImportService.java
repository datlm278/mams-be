package com.example.mamsbe.services.invoice.imports;

import com.example.mamsbe.dto.request.ImportInvoiceMotorbikeRequest;
import com.example.mamsbe.dto.response.ImportInvoiceMotorbikeResponse;

public interface IImportService {

    ImportInvoiceMotorbikeResponse creatOrAddImportDetails(ImportInvoiceMotorbikeRequest request);

    void deleteImportInvoice(Long id);
}
