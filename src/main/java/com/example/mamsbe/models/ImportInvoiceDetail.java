package com.example.mamsbe.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "IMPORT_INVOICE_DETAIL")
public class ImportInvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "QUANTITY")
    private Long quantity;

    @Column(name = "UNIT_PRICE")
    private Long unitPrice;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "CREATE_TIME", updatable = false)
    private Timestamp createTime;

    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;

    @ManyToOne
    @JoinColumn(name = "MOTORBIKE_ID", referencedColumnName = "id")
    private Motorbike motorbike;

    @ManyToOne
    @JoinColumn(name = "IMPORT_INVOICE_CODE", referencedColumnName = "CODE")
    private ImportInvoice importInvoice;
}
