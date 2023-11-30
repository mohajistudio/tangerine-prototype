package io.mohajistudio.tangerine.prototype.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category extends BaseEntity {
    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<PlaceBlock> placeBlocks;
}
