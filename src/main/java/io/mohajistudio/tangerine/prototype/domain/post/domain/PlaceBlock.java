package io.mohajistudio.tangerine.prototype.domain.post.domain;

import io.mohajistudio.tangerine.prototype.global.common.BaseEntity;
import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "place_block")
public class PlaceBlock extends BaseEntity {

    @Column(nullable = false)
    private short orderNumber;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private short rating;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Place place;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "placeBlock")
    private List<PlaceBlockImage> placeImages;
}