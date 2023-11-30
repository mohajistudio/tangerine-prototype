package io.mohajistudio.tangerine.prototype.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trending_post")
public class TrendingPost extends BaseEntity {
    @OneToOne
    private Post post;
}
