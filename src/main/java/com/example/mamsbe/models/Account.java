package com.example.mamsbe.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "CREATE_TIME")
    private Timestamp createTime;

    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;

//    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
//    private List<ImportInvoice> employeeImports;
//
//    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
//    private List<ImportInvoice> supplierImports;
}
