package io.mohajistudio.tangerine.prototype.domain.post.domain;

import io.mohajistudio.tangerine.prototype.global.common.BaseEntity;
import jakarta.persistence.Column;
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
@Table(name = "text_block")
public class TextBlock extends BaseEntity {

    @Column(nullable = false)
    private short orderNumber;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private Post post;
}
