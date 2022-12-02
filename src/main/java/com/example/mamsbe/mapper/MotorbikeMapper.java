package com.example.mamsbe.mapper;

import com.example.mamsbe.dto.response.MotorbikeResponse;
import com.example.mamsbe.models.Motorbike;

public class MotorbikeMapper {
    public static MotorbikeResponse toDto(Motorbike motorbike) {
        MotorbikeResponse motorbikeResponse = new MotorbikeResponse();
        motorbikeResponse.setId(motorbike.getId());
        motorbikeResponse.setName(motorbike.getName());
        motorbikeResponse.setDescription(motorbike.getDescription());
        motorbikeResponse.setImage(motorbike.getImage());
        motorbikeResponse.setImportPrice(motorbike.getImportPrice());
        motorbikeResponse.setSalePrice(motorbike.getSalePrice());
        motorbikeResponse.setMotorbikeType(motorbike.getMotorbikeType().getName());
        motorbikeResponse.setMotorbikeTypeId(motorbike.getMotorbikeType().getId());
        motorbikeResponse.setBrandId(motorbike.getBrand().getId());
        motorbikeResponse.setBrand(motorbike.getBrand().getName());
        motorbikeResponse.setCreateTime(motorbike.getCreateTime());
        motorbikeResponse.setUpdateTime(motorbike.getUpdateTime());
        return motorbikeResponse;
    }
}
