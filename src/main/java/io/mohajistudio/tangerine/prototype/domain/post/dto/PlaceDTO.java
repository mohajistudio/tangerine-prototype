package io.mohajistudio.tangerine.prototype.domain.post.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

@Getter
@Setter
public class PlaceDTO {
    @NotNull
    private Long id;

    @Getter
    @Setter
    public static class Details extends PlaceDTO {
        private String name;
        private Point coordinates;
        private String thumbnail;
        private String address;
        private String roadAddress;
        private String description;
    }

    @Getter
    public static class Add extends PlaceDTO {
    }


    
}
