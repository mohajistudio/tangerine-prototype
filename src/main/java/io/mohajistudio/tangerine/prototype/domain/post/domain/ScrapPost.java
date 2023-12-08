package io.mohajistudio.tangerine.prototype.domain.post.domain;

import io.mohajistudio.tangerine.prototype.domain.member.domain.Member;
import io.mohajistudio.tangerine.prototype.global.common.BaseEntity;
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
@Table(name = "scrap_post")
public class ScrapPost extends BaseEntity {
    @ManyToOne
    private Member member;

    @ManyToOne
    private Post post;
}
