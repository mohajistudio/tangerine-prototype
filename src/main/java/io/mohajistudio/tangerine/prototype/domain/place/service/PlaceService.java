package io.mohajistudio.tangerine.prototype.domain.place.service;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.domain.place.repository.PlaceRepository;
import io.mohajistudio.tangerine.prototype.global.enums.PlaceSearchProvider;
import io.mohajistudio.tangerine.prototype.infra.place.service.PlaceApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public void addPlace(Place place) {
        placeRepository.save(place);
    }

    public Place addKakaoPlace(Place place) {
        place.setPlaceSearchProvider(PlaceSearchProvider.KAKAO);

        return placeRepository.save(place);
    }
}
