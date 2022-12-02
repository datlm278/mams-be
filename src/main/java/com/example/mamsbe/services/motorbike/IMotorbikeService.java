package com.example.mamsbe.services.motorbike;

import com.example.mamsbe.dto.request.MotorbikeRequest;
import com.example.mamsbe.dto.response.MotorbikeResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMotorbikeService {

    MotorbikeResponse createMotor(MotorbikeRequest producerRequest, MultipartFile file);

    MotorbikeResponse updateMotor(MotorbikeRequest producerRequest, Long id, MultipartFile file);

    void deleteMotor(Long id);

    List<MotorbikeResponse> findAllMotor();

    MotorbikeResponse findMotorById(Long id);
}
