package io.mohajistudio.tangerine.prototype.domain.place.service;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.domain.place.repository.PlaceRepository;
import io.mohajistudio.tangerine.prototype.infra.place.service.PlaceApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final PlaceApiService regionApiService;

    public void addPlace(Place place) {
        placeRepository.save(place);
    }
}
