package io.mohajistudio.tangerine.prototype.domain.post.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.mohajistudio.tangerine.prototype.global.common.BaseEntity;
import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Getter
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "place_block")
public class PlaceBlock extends BaseEntity {

    @Column(nullable = false)
    private short orderNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank
    private String content;

    @Column(nullable = false)
    private short rating;

    @Setter
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Post post;

    @Setter
    @ManyToOne(optional = false)
    private Place place;

    @Setter
    @ManyToOne(optional = false)
    private Category category;

    @OneToMany(mappedBy = "placeBlock", fetch = FetchType.EAGER)
    private Set<PlaceBlockImage> placeBlockImages;

    public void setPlaceBlockImages(Set<PlaceBlockImage> placeBlockImages) {
        this.placeBlockImages = placeBlockImages;
        placeBlockImages.forEach(placeBlockImage -> placeBlockImage.setPlaceBlock(this));
    }
}