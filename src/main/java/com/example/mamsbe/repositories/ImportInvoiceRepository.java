package com.example.mamsbe.repositories;

import com.example.mamsbe.models.ImportInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportInvoiceRepository extends JpaRepository<ImportInvoice, Long> {
    ImportInvoice findByCode(String code);
}
