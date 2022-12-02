package com.example.mamsbe.services.motorbike;

import com.example.mamsbe.dto.FileDTO;
import com.example.mamsbe.dto.request.MotorbikeRequest;
import com.example.mamsbe.dto.response.MotorbikeResponse;
import com.example.mamsbe.mapper.MotorbikeMapper;
import com.example.mamsbe.models.Motorbike;
import com.example.mamsbe.repositories.MotorbikeRepository;
import com.example.mamsbe.services.minio.MinIOService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class MotorbikeService implements IMotorbikeService{

    private final MotorbikeRepository motorbikeRepository;
    private final ModelMapper modelMapper;
    private final MinIOService minIOService;

    @Autowired
    public MotorbikeService(MotorbikeRepository motorbikeRepository, ModelMapper modelMapper, MinIOService minIOService) {
        this.motorbikeRepository = motorbikeRepository;
        this.modelMapper = modelMapper;
        this.minIOService = minIOService;
    }

    @Override
    public MotorbikeResponse createMotor(MotorbikeRequest motorbikeRequest, MultipartFile file) {
        Motorbike motorbike = modelMapper.map(motorbikeRequest, Motorbike.class);
        FileDTO fileDTO = uploadToMinIO(file);
        motorbike.setImage(fileDTO.getUrl());
        motorbike.setCreateTime(Timestamp.from(Instant.now()));
        return MotorbikeMapper.toDto(motorbikeRepository.save(motorbike));
    }

    @Override
    public MotorbikeResponse updateMotor(MotorbikeRequest motorbikeRequest, Long id, MultipartFile file) {
        if (motorbikeRepository.findById(id).isEmpty()) {
            throw new RuntimeException("motorbike isn't existed");
        }
        if (!motorbikeRequest.getId().equals(id)) {
            throw new RuntimeException("motorbike request id not equal id request");
        }
        Motorbike motorbike = modelMapper.map(motorbikeRequest, Motorbike.class);
        FileDTO fileDTO = uploadToMinIO(file);
        motorbike.setImage(fileDTO.getUrl());
        motorbike.setUpdateTime(Timestamp.from(Instant.now()));
        motorbike = motorbikeRepository.save(motorbike);
        return MotorbikeMapper.toDto(motorbike);
    }

    @Override
    public void deleteMotor(Long id) {
        if (motorbikeRepository.findById(id).isEmpty()) {
            throw new RuntimeException("motorbike isn't existed");
        }
        motorbikeRepository.deleteById(id);
    }

    @Override
    public List<MotorbikeResponse> findAllMotor() {
        return motorbikeRepository.findAll()
                .stream()
                .map(MotorbikeMapper::toDto).toList();
    }

    @Override
    public MotorbikeResponse findMotorById(Long id) {
        Motorbike motorbike = motorbikeRepository.findById(id).orElseThrow();
        if (ObjectUtils.isEmpty(motorbike)) {
            throw new RuntimeException("motorbike isn't existed");
        }
        return MotorbikeMapper.toDto(motorbike);
    }

    public FileDTO uploadToMinIO(MultipartFile file) {
        FileDTO fileDTO = new FileDTO();

        if (!ObjectUtils.isEmpty(file)) {
            fileDTO.setFile(file);
            fileDTO = minIOService.uploadFile(fileDTO);
        }
        return fileDTO;
    }
}
