package io.mohajistudio.tangerine.prototype.domain.place.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

@Getter
@Setter
public class PlaceDTO {

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

    @Getter
    @Setter
    public static class Add extends PlaceDTO {
    }

    @Getter
    @Setter
    public static class Details extends PlaceDTO {
        @NotNull
        private Long id;
    }
}
