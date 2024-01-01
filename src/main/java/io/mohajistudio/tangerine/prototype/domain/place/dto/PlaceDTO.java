package io.mohajistudio.tangerine.prototype.domain.place.dto;

import io.mohajistudio.tangerine.prototype.global.common.PointDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

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
    private PointDTO coordinates;

    public void setCoordinates(Point coordinates) {
        double lat = coordinates.getX();
        double lng = coordinates.getY();
        this.coordinates = PointDTO.builder().lat(lat).lng(lng).build();
    }

    @Getter
    @Setter
    public static class Add extends PlaceDTO {
    }

    @Getter
    @Setter
    public static class Details extends PlaceDTO {
        @NotNull
        private Long id;
        private String description;
    }
}
