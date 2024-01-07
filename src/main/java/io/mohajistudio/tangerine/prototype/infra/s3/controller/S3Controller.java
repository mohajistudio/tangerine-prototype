package io.mohajistudio.tangerine.prototype.infra.s3.controller;

import io.mohajistudio.tangerine.prototype.infra.s3.dto.RequestDTO;
import io.mohajistudio.tangerine.prototype.infra.s3.dto.ResponseDTO;
import io.mohajistudio.tangerine.prototype.infra.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping(value = "/upload",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDTO> uploadImage(@RequestPart("imageFile") MultipartFile multipartFile,
                                      @RequestPart RequestDTO requestDTO) {

        s3Service.uploadImageToS3(multipartFile);
        return null;
    }
}
