package io.mohajistudio.tangerine.prototype.entity;

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
    private List<PlaceImage> placeImages;
}