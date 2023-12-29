package io.mohajistudio.tangerine.prototype.infra.place.service;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.infra.place.dto.PlaceKakaoSearchApiResultDTO;

import java.util.Set;

public interface PlaceApiService {
    PlaceKakaoSearchApiResultDTO searchPlace(String query, int page, int size);
}
