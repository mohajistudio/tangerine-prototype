package io.mohajistudio.tangerine.prototype.infra.s3.controller;


import io.mohajistudio.tangerine.prototype.infra.s3.dto.S3UploadResponseDTO;
import io.mohajistudio.tangerine.prototype.infra.s3.service.S3UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
public class S3UploadController {

    private final S3UploadService s3UploadService;

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<S3UploadResponseDTO> uploadImage(@RequestPart("imageFile") MultipartFile multipartFile) {

//        @RequestPart S3UploadRequestDTO s3UploadRequestDTO  -> multipartFile 뒤에 넣기
//        S3UploadResponseDTO s3UploadResponseDTO = s3UploadService.uploadImageToS3(multipartFile,s3UploadRequestDTO);
        S3UploadResponseDTO s3UploadResponseDTO = s3UploadService.uploadImageToS3(multipartFile);
        return ResponseEntity.ok(s3UploadResponseDTO);
    }
}
