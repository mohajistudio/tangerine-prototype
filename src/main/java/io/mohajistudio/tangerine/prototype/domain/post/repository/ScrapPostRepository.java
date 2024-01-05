package io.mohajistudio.tangerine.prototype.domain.post.repository;

import io.mohajistudio.tangerine.prototype.domain.post.domain.ScrapPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScrapPostRepository extends JpaRepository<ScrapPost, Long> {

    @Query("SELECT sp FROM ScrapPost sp WHERE sp.member.id = :memberId AND sp.post.id = :postId")
    Optional<ScrapPost> findByMemberIdAndPostId(@Param("memberId") Long memberId, @Param("postId") Long postId);
}
