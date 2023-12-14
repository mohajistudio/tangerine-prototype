package io.mohajistudio.tangerine.prototype.domain.place.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

@Getter
@Setter
public class PlaceResponse {
    private Long id;
    private String name;
    private Point coordinates;
    private String thumbnail;
    private String address;
    private String roadAddress;
    private String description;
}
