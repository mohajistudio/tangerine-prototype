package io.mohajistudio.tangerine.prototype.domain.comment.repository;

import io.mohajistudio.tangerine.prototype.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT MAX(c.groupNumber) FROM Comment c WHERE c.post.id = :postId")
    Integer findMaxGroupNumberByPostId(@Param("postId") Long postId);

    @Query("SELECT c.groupNumber FROM Comment c WHERE c.id = :id")
    Integer findGroupNumberById(@Param("id") Long id);
}
