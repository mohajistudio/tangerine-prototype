package io.mohajistudio.tangerine.prototype.domain.post.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PlaceBlockDTO {
    @NotNull
    private short orderNumber;
    @NotNull
    private String content;
    @NotNull
    private Short rating;

    @Getter
    @Setter
    public static class Details extends PlaceBlockDTO {
        private Long id;
        @Valid
        private CategoryDTO.Details category;
        @Valid
        private Set<PlaceBlockImageDTO.Details> placeBlockImages;
        @Valid
        private PlaceDTO.Details place;
    }

    @Getter
    @Setter
    public static class Add extends PlaceBlockDTO {
        @Valid
        @NotNull
        private CategoryDTO.Add category;
        @Valid
        @NotNull
        private Set<PlaceBlockImageDTO.Add> placeBlockImages;
        @Valid
        @NotNull
        private PlaceDTO.Add place;
    }
}
