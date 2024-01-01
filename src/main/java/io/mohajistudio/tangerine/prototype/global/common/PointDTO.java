package io.mohajistudio.tangerine.prototype.global.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PointDTO {
    @Schema(description = "위도", example = "37.273629699499")
    private double lat;
    @Schema(description = "경도", example = "127.12928668205")
    private double lng;
}
