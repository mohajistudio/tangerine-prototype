package io.mohajistudio.tangerine.prototype.domain.place.repository;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("SELECT p FROM Place p WHERE p.name LIKE %:query%")
    Page<Place> findByName(@Param("query") String query, Pageable pageable);
}
