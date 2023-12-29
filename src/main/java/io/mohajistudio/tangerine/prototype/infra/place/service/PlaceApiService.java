package io.mohajistudio.tangerine.prototype.infra.place.service;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;

import java.util.Set;

public interface PlaceApiService {
    Set<Place> searchPlace(String query);
}
