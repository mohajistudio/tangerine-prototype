package io.mohajistudio.tangerine.prototype.domain.post.repository;

import io.mohajistudio.tangerine.prototype.domain.post.domain.FavoritePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoritePostRepository extends JpaRepository<FavoritePost, Long> {

    Optional<FavoritePost> findByMemberIdAndPostId(Long memberId, Long postId);
}
