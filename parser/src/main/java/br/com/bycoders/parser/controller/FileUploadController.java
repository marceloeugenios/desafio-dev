package br.com.bycoders.parser.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(value = "api/v1/transacoes")
public class FileUploadController {

    @ResponseStatus
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("arquivo")MultipartFile arquivo) {

        log.info("Fazendo upload: {}", arquivo.getOriginalFilename());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
