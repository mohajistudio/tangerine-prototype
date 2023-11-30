package io.mohajistudio.tangerine.prototype.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_profile")
public class MemberProfile extends BaseEntity {
    @Column(length = 20)
    private String name;
    private LocalDate birthday;
    @Column(length = 15)
    private String phone;
    private char sex;
    private String thumbnail;
    @Column(length = 20)
    private String nickname;

    @OneToOne
    Member member;
}
