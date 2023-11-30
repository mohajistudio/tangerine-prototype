package io.mohajistudio.tangerine.prototype.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "place")
public class Place extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Point coordinates;
    private String thumbnail;
    private String address;
    private String roadAddress;
    @Column(length = 500)
    private String description;

    @OneToMany(mappedBy = "place")
    private List<PlaceBlock> placeBlocks;
}