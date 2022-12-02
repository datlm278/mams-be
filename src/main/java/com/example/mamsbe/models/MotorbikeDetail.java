package com.example.mamsbe.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "MOTORBIKE_DETAIL")
public class MotorbikeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CHASSIS_NUMBER")
    private String chassisNumber;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "CREATE_TIME")
    private Timestamp createTime;

    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MOTORBIKE_ID", referencedColumnName = "id")
    private Motorbike motorbike;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IMPORT_INVOICE_DETAIL_ID", referencedColumnName = "id")
    private ImportInvoice importInvoice;


}
