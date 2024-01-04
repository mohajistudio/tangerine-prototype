package io.mohajistudio.tangerine.prototype.infra.place.service;

import io.mohajistudio.tangerine.prototype.infra.place.dto.PlaceKakaoSearchApiResultDTO;

public interface PlaceApiService {
    PlaceKakaoSearchApiResultDTO searchPlace(String query, int page, int size);
}
