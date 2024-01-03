package io.mohajistudio.tangerine.prototype.domain.comment.repository;

import io.mohajistudio.tangerine.prototype.domain.comment.domain.FavoriteComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FavoriteCommentRepository extends JpaRepository<FavoriteComment, Long> {
    @Query("SELECT fc FROM FavoriteComment fc WHERE fc.member.id = :memberId AND fc.comment.id = :commentId")
    Optional<FavoriteComment> findByMemberIdAndPostId(@Param("memberId") Long memberId, @Param("commentId") Long commentId);
}
