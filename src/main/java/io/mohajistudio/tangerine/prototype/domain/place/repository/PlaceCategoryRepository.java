package io.mohajistudio.tangerine.prototype.domain.place.repository;

import io.mohajistudio.tangerine.prototype.domain.place.domain.PlaceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceCategoryRepository extends JpaRepository<PlaceCategory, Long> {
}
