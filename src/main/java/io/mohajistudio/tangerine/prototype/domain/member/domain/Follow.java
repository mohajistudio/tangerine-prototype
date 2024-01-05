package io.mohajistudio.tangerine.prototype.domain.member.domain;

import io.mohajistudio.tangerine.prototype.global.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "follow")
public class Follow extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Member followMember;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
