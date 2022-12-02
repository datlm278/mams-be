package com.example.mamsbe.services.invoice.imports;

import com.example.mamsbe.common.constant.MAMSConstant;
import com.example.mamsbe.dto.request.ImportInvoiceMotorbikeRequest;
import com.example.mamsbe.dto.response.ImportInvoiceMotorbikeResponse;
import com.example.mamsbe.dto.response.ImportInvoiceResponse;
import com.example.mamsbe.mapper.ImportInvoiceDetailMapper;
import com.example.mamsbe.mapper.ImportInvoiceMapper;
import com.example.mamsbe.models.ImportInvoice;
import com.example.mamsbe.models.ImportInvoiceDetail;
import com.example.mamsbe.models.Motorbike;
import com.example.mamsbe.repositories.ImportInvoiceDetailRepository;
import com.example.mamsbe.repositories.ImportInvoiceRepository;
import com.example.mamsbe.repositories.MotorbikeRepository;
import org.hibernate.sql.Delete;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class ImportService implements IImportService {

    private final ImportInvoiceDetailRepository importInvoiceDetailRepository;
    private final ImportInvoiceRepository importInvoiceRepository;
    private final MotorbikeRepository motorbikeRepository;

    @Autowired
    public ImportService(ImportInvoiceDetailRepository importInvoiceDetailRepository,
                         ImportInvoiceRepository importInvoiceRepository,
                         MotorbikeRepository motorbikeRepository, ModelMapper modelMapper) {
        this.importInvoiceDetailRepository = importInvoiceDetailRepository;
        this.importInvoiceRepository = importInvoiceRepository;
        this.motorbikeRepository = motorbikeRepository;
    }

    @Override
    public ImportInvoiceMotorbikeResponse creatOrAddImportDetails(ImportInvoiceMotorbikeRequest request) {

        ImportInvoice invoice = importInvoiceRepository.findByCode(request.getCode());
        Motorbike motorbike = motorbikeRepository.findById(request.getMotorbikeId()).orElseThrow();
        if (ObjectUtils.isEmpty(invoice)) {
            ImportInvoice importInvoice = ImportInvoiceMapper.toEntity(request);
            ImportInvoiceDetail importInvoiceDetail = ImportInvoiceDetailMapper.toEntity(request);

            importInvoiceDetail.setQuantity(request.getQuantity());
            importInvoiceDetail.setUnitPrice(request.getUnitPrice());
            importInvoiceDetail.setImportInvoice(importInvoice);
            importInvoiceDetail.setMotorbike(motorbike);
            importInvoiceDetail.setStatus(request.getStatus());
            importInvoiceDetail.setCreateTime(Timestamp.from(Instant.now()));

            importInvoice.setCode(request.getCode());
            importInvoice.setStatus(request.getStatus());
            importInvoice.setTotalPrice(request.getQuantity() * request.getUnitPrice());
            importInvoice.setCreateTime(Timestamp.from(Instant.now()));


            ImportInvoiceMotorbikeResponse response = new ImportInvoiceMotorbikeResponse();
            response.setImportInvoice(ImportInvoiceMapper.toDto(importInvoiceRepository.save(importInvoice)));
            response.setDetails(ImportInvoiceDetailMapper.toDto(importInvoiceDetailRepository.save(importInvoiceDetail)));

            return response;
        }

        ImportInvoiceDetail importInvoiceDetail = ImportInvoiceDetailMapper.toEntity(request);

        invoice.setTotalPrice(invoice.getTotalPrice() + (request.getUnitPrice() * request.getQuantity()));

        importInvoiceDetail.setMotorbike(motorbike);
        importInvoiceDetail.setQuantity(request.getQuantity());
        importInvoiceDetail.setUnitPrice(request.getUnitPrice());
        importInvoiceDetail.setCreateTime(Timestamp.from(Instant.now()));
        importInvoiceDetail.setStatus(request.getStatus());
        importInvoiceDetail.setImportInvoice(invoice);

        ImportInvoiceMotorbikeResponse response = new ImportInvoiceMotorbikeResponse();
        response.setImportInvoice(ImportInvoiceMapper.toDto(invoice));
        response.setDetails(ImportInvoiceDetailMapper.toDto(importInvoiceDetailRepository.save(importInvoiceDetail)));

        return response;
    }

    @Override
    public void deleteImportInvoice(Long id) {
        ImportInvoice importInvoice = importInvoiceRepository.findById(id).orElseThrow();

        List<ImportInvoiceDetail> importInvoiceDetailList = importInvoiceDetailRepository.findByCode(importInvoice.getCode());

        for (ImportInvoiceDetail detail: importInvoiceDetailList) {
            detail.setStatus(MAMSConstant.DELETE_STATUS);
            importInvoiceDetailRepository.save(detail);
        }

        importInvoice.setStatus(MAMSConstant.DELETE_STATUS);
        importInvoiceRepository.save(importInvoice);
    }
}
