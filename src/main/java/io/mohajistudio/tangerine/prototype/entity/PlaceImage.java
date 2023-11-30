package io.mohajistudio.tangerine.prototype.entity;

import io.mohajistudio.tangerine.prototype.enums.ImageMimeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "place_image")
public class PlaceImage extends BaseEntity {
    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ImageMimeType imageMimeType;

    @ManyToOne
    private PlaceBlock placeBlock;
}
