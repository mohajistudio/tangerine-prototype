package io.mohajistudio.tangerine.prototype.infra.place.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddressDTO {
    private String province;//광역시/도
    private String city;//시/군/구
    private String district;//읍/면/동
    private String detail;//이하
}
