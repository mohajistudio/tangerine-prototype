package io.mohajistudio.tangerine.prototype.domain.post.repository;

import io.mohajistudio.tangerine.prototype.domain.post.domain.PlaceBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaceBlockRepository extends JpaRepository<PlaceBlock, Long> {
    @Modifying
    @Query("update PlaceBlock pb set pb.content = :content, pb.orderNumber = :orderNumber, pb.rating = :rating where pb.id = :id")
    void update(@Param("id") Long id, @Param("content") String content, @Param("orderNumber") short orderNumber, @Param("rating") short rating);
}
