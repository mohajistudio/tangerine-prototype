package io.mohajistudio.tangerine.prototype.domain.member.repository;

import io.mohajistudio.tangerine.prototype.domain.member.domain.Member;
import io.mohajistudio.tangerine.prototype.global.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m left join fetch m.memberProfile where m.email = :email")
    Optional<Member> findByEmail(@Param("email") String email);

    @Override
    @Query("select m from Member m left join fetch m.memberProfile where m.id = :id")
    Optional<Member> findById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Member m SET m.refreshToken = :refreshToken WHERE m.id = :memberId")
    void updateRefreshToken(@Param("memberId") Long memberId, @Param("refreshToken") String refreshToken);

    @Modifying
    @Query("UPDATE Member m SET m.role = :role WHERE m.id = :memberId")
    void updateRole(@Param("memberId") Long memberId, @Param("role") Role role);
}
