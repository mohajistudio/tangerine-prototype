package io.mohajistudio.tangerine.prototype.domain.comment.repository;

import io.mohajistudio.tangerine.prototype.domain.comment.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT MAX(c.groupNumber) FROM Comment c WHERE c.post.id = :postId AND c.deletedAt IS NULL")
    Integer findMaxGroupNumberByPostId(@Param("postId") Long postId);

    @Query("SELECT c.groupNumber FROM Comment c WHERE c.id = :id AND c.deletedAt IS NULL")
    Integer findGroupNumberById(@Param("id") Long id);

    @Query("SELECT c FROM Comment c " +
            "left join fetch c.member m " +
            "left join fetch m.memberProfile mp " +
            "WHERE c.post.id = :postId " +
            "AND c.deletedAt IS NULL " +
            "ORDER BY c.groupNumber")
    Page<Comment> findByPostId(@Param("postId") Long postId, Pageable pageable);

    @Modifying
    @Query("UPDATE Comment c SET c.content = :content WHERE c.id = :id AND c.deletedAt IS NULL")
    void update(@Param("id") Long id, @Param("content") String content);

    @Modifying
    @Query("UPDATE Comment c SET c.deletedAt = :deletedAt WHERE c.id =:id AND c.deletedAt IS NULL")
    void delete(@Param("id") Long id, @Param("deletedAt") LocalDateTime deletedAt);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Comment c SET c.favoriteCnt = :favoriteCnt where c.id = :id and c.deletedAt IS NULL")
    void updateFavoriteCnt(@Param("id") Long id, @Param("favoriteCnt") int favoriteCnt);
}
