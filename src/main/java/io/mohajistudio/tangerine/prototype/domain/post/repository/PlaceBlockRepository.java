package io.mohajistudio.tangerine.prototype.domain.post.repository;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.domain.post.domain.Category;
import io.mohajistudio.tangerine.prototype.domain.post.domain.PlaceBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PlaceBlockRepository extends JpaRepository<PlaceBlock, Long> {
    @Modifying
    @Query("update PlaceBlock pb set pb.content = :content, pb.orderNumber = :orderNumber, pb.rating = :rating, pb.category = :category, pb.place = :place where pb.id = :id")
    void update(@Param("id") Long id, @Param("content") String content, @Param("orderNumber") short orderNumber, @Param("rating") short rating, @Param("category") Category category, @Param("place") Place place);

    @Modifying
    @Query("update PlaceBlock pb set pb.deletedAt = :deletedAt where pb.id = :id")
    void delete(@Param("id") Long id, @Param("deletedAt") LocalDateTime deletedAt);

    @Override
    @Query("select pb from PlaceBlock pb " +
            "left join fetch pb.category " +
            "left join fetch pb.place " +
            "left join fetch pb.placeBlockImages " +
            "where pb.id = :id")
    Optional<PlaceBlock> findById(@Param("id") Long id);
}
