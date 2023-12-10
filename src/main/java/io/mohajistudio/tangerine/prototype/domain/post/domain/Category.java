package io.mohajistudio.tangerine.prototype.domain.post.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.mohajistudio.tangerine.prototype.global.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
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
