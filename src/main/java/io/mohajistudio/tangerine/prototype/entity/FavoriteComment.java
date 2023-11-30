package io.mohajistudio.tangerine.prototype.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "favorite_comment")
public class FavoriteComment extends BaseEntity {
    @ManyToOne
    private Member member;

    @ManyToOne
    private Comment comment;
}
