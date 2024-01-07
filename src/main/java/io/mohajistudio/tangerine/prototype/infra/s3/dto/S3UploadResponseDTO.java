package io.mohajistudio.tangerine.prototype.infra.s3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class S3UploadResponseDTO {
    private String S3UploadImageUrl;
    private String S3UploadImageName;

}
