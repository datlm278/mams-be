package com.example.mamsbe.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "MOTORBIKE")
public class Motorbike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "IMPORT_PRICE")
    private Long importPrice;

    @Column(name = "SALE_PRICE")
    private Long salePrice;

    @Column(name = "CREATE_TIME")
    private Timestamp createTime;

    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", referencedColumnName = "id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "MOTORBIKE_TYPE_ID", referencedColumnName = "id")
    private MotorbikeType motorbikeType;

}
