package io.mohajistudio.tangerine.prototype.infra.place.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceKakaoSearchApiDTO {
    @NotNull
    private Long id;
    @NotNull
    private String placeName;
    private String categoryName;
    private String categoryGroupCode;
    private String categoryGroupName;
    private String phone;
    @NotNull
    private String addressName;
    @NotNull
    private String roadAddressName;
    @NotNull
    private String x;
    @NotNull
    private String y;
    private String placeUrl;
}
