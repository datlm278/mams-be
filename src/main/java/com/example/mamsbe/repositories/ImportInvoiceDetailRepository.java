package com.example.mamsbe.repositories;

import com.example.mamsbe.models.ImportInvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImportInvoiceDetailRepository extends JpaRepository<ImportInvoiceDetail, Long> {

    @Query(value = "SELECT * FROM IMPORT_INVOICE_DETAIL WHERE IMPORT_INVOICE_CODE = :code", nativeQuery = true)
    List<ImportInvoiceDetail> findByCode(String code);
}
