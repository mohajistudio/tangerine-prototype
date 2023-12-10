package io.mohajistudio.tangerine.prototype.domain.place.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.mohajistudio.tangerine.prototype.domain.post.domain.PlaceBlock;
import io.mohajistudio.tangerine.prototype.global.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.List;

@Getter
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

    @JsonIgnore
    @OneToMany(mappedBy = "place")
    private List<PlaceBlock> placeBlocks;
}