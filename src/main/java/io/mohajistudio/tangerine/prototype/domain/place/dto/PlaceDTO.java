package io.mohajistudio.tangerine.prototype.domain.place.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.data.geo.Point;

@Getter
public class PlaceAddRequest {
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String roadAddress;
    @NotNull
    private Point coordinates;
    @NotNull
    private String description;
}
