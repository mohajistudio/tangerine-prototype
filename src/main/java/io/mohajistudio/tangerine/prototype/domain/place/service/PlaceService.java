package io.mohajistudio.tangerine.prototype.domain.place.service;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.domain.place.repository.PlaceRepository;
import io.mohajistudio.tangerine.prototype.global.enums.PlaceSearchProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<Place> findPlaceListByPage(String query, Pageable pageable) {
        return placeRepository.findByName(query, pageable);
    }
}
