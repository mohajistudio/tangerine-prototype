package io.mohajistudio.tangerine.prototype.infra.s3.controller;


import io.mohajistudio.tangerine.prototype.domain.post.dto.PlaceBlockImageDTO;
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
    public ResponseEntity<PlaceBlockImageDTO> uploadImage(@RequestPart("imageFile") MultipartFile multipartFile) {

        PlaceBlockImageDTO placeBlockImageDTO = s3UploadService.uploadImageToS3(multipartFile);
        return ResponseEntity.ok(placeBlockImageDTO);
    }
}
