package io.mohajistudio.tangerine.prototype.infra.s3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class ResponseDTO {
    private String S3UploadImageUrl;
    private String S3UploadImageName;


}
