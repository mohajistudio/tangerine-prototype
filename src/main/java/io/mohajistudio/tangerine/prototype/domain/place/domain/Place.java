package io.mohajistudio.tangerine.prototype.domain.place.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.mohajistudio.tangerine.prototype.domain.post.domain.PlaceBlock;
import io.mohajistudio.tangerine.prototype.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.geo.Point;

import java.util.List;

@Getter
@SuperBuilder
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
    private  String province;//광역시/도
    private  String city;//시/군/구
    private  String district;//읍/면/동
    private  String detail;//이하
    private String roadAddress;
    @Column(length = 500)
    private String description;


    @JsonIgnore
    @OneToMany(mappedBy = "place")
    private List<PlaceBlock> placeBlocks;
}