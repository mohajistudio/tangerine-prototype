package io.mohajistudio.tangerine.prototype.global.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PointDTO {
    private double lat;
    private double lng;
}
