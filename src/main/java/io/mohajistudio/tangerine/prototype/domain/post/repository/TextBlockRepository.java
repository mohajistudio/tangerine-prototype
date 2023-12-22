package io.mohajistudio.tangerine.prototype.domain.post.repository;

import io.mohajistudio.tangerine.prototype.domain.post.domain.TextBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TextBlockRepository extends JpaRepository<TextBlock, Long> {
    @Modifying
    @Query("update TextBlock tb set tb.content = :content, tb.orderNumber = :orderNumber where tb.id = :id")
    void update(@Param("id") Long id, @Param("content") String content, @Param("orderNumber") short orderNumber);
}
