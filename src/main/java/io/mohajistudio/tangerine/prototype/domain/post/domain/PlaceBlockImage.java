package io.mohajistudio.tangerine.prototype.domain.post.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.mohajistudio.tangerine.prototype.global.common.BaseEntity;
import io.mohajistudio.tangerine.prototype.global.enums.ImageMimeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "place_block_image")
public class PlaceBlockImage extends BaseEntity {
    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ImageMimeType imageMimeType;

    @Column(nullable = false)
    private short orderNumber;

    @JsonIgnore
    @ManyToOne
    private PlaceBlock placeBlock;

    public void setPlaceBlock(PlaceBlock placeBlock) {
        this.placeBlock = placeBlock;
    }

    public void setImageMimeType(String imageMimeType) {
        this.imageMimeType = ImageMimeType.fromValue(imageMimeType);
    }
}
