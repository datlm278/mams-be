package com.example.mamsbe.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "IMPORT_INVOICE")
public class ImportInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE", unique = true)
    private String code;

    @Column(name = "TOTAL_PRICE")
    private Long totalPrice;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "CREATE_TIME", updatable = false)
    private Timestamp createTime;

    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Account employee;

    @ManyToOne
    @JoinColumn(name = "SUPPLIER_ID")
    private Account supplier;

    @OneToMany(mappedBy = "importInvoice", cascade = CascadeType.ALL)
    private List<ImportInvoiceDetail> importInvoiceDetails;
}
