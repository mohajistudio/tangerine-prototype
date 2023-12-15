package io.mohajistudio.tangerine.prototype.domain.post.repository;

import io.mohajistudio.tangerine.prototype.domain.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Override
    @Query("select p from Post p left join fetch TextBlock tb on p.id = tb.post.id left join fetch PlaceBlock pb on p.id = pb.post.id where p.id = :id")
    Optional<Post> findById(@Param("id") Long id);

    @Override
    @Query("select distinct p from Post p " +
            "join fetch p.member m " +
            "join fetch m.memberProfile mp")
    Page<Post> findAll(Pageable pageable);

    @Modifying
    @Query("update Post p set p.favoriteCnt = :favoriteCnt where p.id = :id")
    void updateFavoriteCnt(@Param("id") Long id, @Param("favoriteCnt") int favoriteCnt);
}
