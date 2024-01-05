package io.mohajistudio.tangerine.prototype.domain.member.repository;

import io.mohajistudio.tangerine.prototype.domain.member.domain.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query("SELECT f FROM Follow f WHERE f.member.id = :memberId AND f.followMember.id = :followMemberId")
    Optional<Follow> findByMemberIdAndFollowMemberId(@Param("memberId") Long memberId,@Param("followMemberId") Long followMemberId);

    @Query("SELECT f FROM Follow f " +
            "LEFT JOIN FETCH f.followMember fm " +
            "LEFT JOIN FETCH fm.memberProfile " +
            "WHERE f.member.id = :memberId")
    Page<Follow> findFollow(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT f FROM Follow f " +
            "LEFT JOIN FETCH f.member fm " +
            "LEFT JOIN FETCH fm.memberProfile " +
            "WHERE f.followMember.id = :memberId")
    Page<Follow> findFollowMember(@Param("memberId") Long memberId, Pageable pageable);
}
