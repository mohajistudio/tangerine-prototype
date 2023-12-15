package io.mohajistudio.tangerine.prototype.domain.post.dto;

import io.mohajistudio.tangerine.prototype.global.enums.ImageMimeType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceBlockImageDTO {
    @NotNull
    private String imageUrl;
    @NotNull
    private ImageMimeType imageMimeType;
    @NotNull
    private short orderNumber;

    public void setImageMimeType(String imageMimeType) {
        this.imageMimeType = ImageMimeType.fromValue(imageMimeType);
    }

    @Getter
    @Setter
    public static class Details extends PlaceBlockImageDTO {
        private Long id;
    }

    @Getter
    @Setter
    public static class Add extends PlaceBlockImageDTO {
    }
}
