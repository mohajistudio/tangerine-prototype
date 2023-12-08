package io.mohajistudio.tangerine.prototype.domain.member.domain;

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
@Table(name = "notification")
public class Notification extends BaseEntity {
    @ManyToOne
    private Member member;
}
