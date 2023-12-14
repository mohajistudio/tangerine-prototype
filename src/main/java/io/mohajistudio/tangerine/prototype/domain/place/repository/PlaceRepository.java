package io.mohajistudio.tangerine.prototype.domain.place.repository;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> { }
