package io.mohajistudio.tangerine.prototype.domain.post.repository;

import io.mohajistudio.tangerine.prototype.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Override
    @Query("select p from Post p left join fetch TextBlock tb on p.id = tb.post.id left join fetch PlaceBlock pb on p.id = pb.post.id where p.id = :id")
    Optional<Post> findById(@Param("id") Long id);
}
