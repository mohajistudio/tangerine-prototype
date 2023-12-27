package io.mohajistudio.tangerine.prototype.domain.post.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.mohajistudio.tangerine.prototype.domain.post.domain.PlaceBlock;
import io.mohajistudio.tangerine.prototype.global.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category extends BaseEntity {
    @Column(nullable = false, length = 20)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<PlaceBlock> placeBlocks;
}
