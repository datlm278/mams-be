package com.example.mamsbe.controllers;


import com.example.mamsbe.common.constant.MAMSConstant;
import com.example.mamsbe.dto.request.ImportInvoiceMotorbikeRequest;
import com.example.mamsbe.dto.response.ImportInvoiceMotorbikeResponse;
import com.example.mamsbe.services.invoice.imports.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(MAMSConstant.PREFIX_API_URL + "/import-invoice")
public class ImportInvoiceController {

    private final ImportService importService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ImportInvoiceController(ImportService importService) {
        this.importService = importService;
    }

    @PostMapping(value = "/create-or-add-import-invoice-details")
    public ResponseEntity<ImportInvoiceMotorbikeResponse> createCinemaRoomAndSeatRoom(@RequestBody ImportInvoiceMotorbikeRequest request) {
        LOGGER.info("[POST]{} create a new cinema-room and seat-room of new room",
                MAMSConstant.PREFIX_API_URL + "import-invoice/create-or-add-import-invoice-details");
        return ResponseEntity.ok().body(importService.creatOrAddImportDetails(request));
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteImportInvoice(@RequestParam Long id) {
        importService.deleteImportInvoice(id);
        LOGGER.info("[POST]{} delete a import-invoice existed", MAMSConstant.PREFIX_API_URL + "import-invoice/delete");
        return ResponseEntity.ok().body("Deleted import invoice has id= " + id + " successfully!");
    }
}
