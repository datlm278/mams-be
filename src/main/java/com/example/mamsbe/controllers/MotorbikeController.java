package com.example.mamsbe.controllers;

import com.example.mamsbe.common.constant.MAMSConstant;
import com.example.mamsbe.dto.request.MotorbikeRequest;
import com.example.mamsbe.dto.response.MotorbikeResponse;
import com.example.mamsbe.services.motorbike.MotorbikeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(MAMSConstant.PREFIX_API_URL + "/motorbike")
public class MotorbikeController {

    private final MotorbikeService motorbikeService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MotorbikeController(MotorbikeService motorbikeService) {
        this.motorbikeService = motorbikeService;
    }


    @PostMapping(value = "/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MotorbikeResponse> createProducer(@RequestPart("motorbike") MotorbikeRequest motorbikeRequest,
                                                            @RequestPart("image") MultipartFile file) {
        LOGGER.info("[POST]{} create a new motorbike", MAMSConstant.PREFIX_API_URL + "/motorbike/create");
        return ResponseEntity.ok().body(motorbikeService.createMotor(motorbikeRequest, file));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MotorbikeResponse> updateProducer(@RequestPart("motorbike") MotorbikeRequest producerRequest,
                                                           @RequestParam Long id,
                                                           @RequestPart("image")MultipartFile file) {
        LOGGER.info("[POST]{} update a existed motorbike", MAMSConstant.PREFIX_API_URL + "/motorbike/update?id=" + id);
        return ResponseEntity.ok().body(motorbikeService.updateMotor(producerRequest, id, file));
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProducer(@RequestParam Long id) {
        motorbikeService.deleteMotor(id);
        LOGGER.info("[POST]{} delete a existed motorbike", MAMSConstant.PREFIX_API_URL + "/motorbike/delete?id=" + id);
        return ResponseEntity.ok().body("Delete motorbike id= " + id + " successfully!");
    }

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MotorbikeResponse>> findAllProducer() {
        LOGGER.info("[GET]{} get list existed motorbike", MAMSConstant.PREFIX_API_URL + "/motorbike/find-all");
        return ResponseEntity.ok().body(motorbikeService.findAllMotor());
    }

    @GetMapping(value = "/find-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MotorbikeResponse> findProducerById(@RequestParam Long id) {
        LOGGER.info("[GET]{} get a existed motorbike", MAMSConstant.PREFIX_API_URL + "/motorbike/find-by-id?id=" + id);
        return ResponseEntity.ok().body(motorbikeService.findMotorById(id));
    }
}
