package io.mohajistudio.tangerine.prototype.domain.place.controller;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.domain.place.dto.PlaceDTO;
import io.mohajistudio.tangerine.prototype.domain.place.mapper.PlaceMapper;
import io.mohajistudio.tangerine.prototype.domain.place.service.PlaceService;
import io.mohajistudio.tangerine.prototype.global.common.PageableParam;
import io.mohajistudio.tangerine.prototype.infra.place.dto.PlaceKakaoSearchApiDTO;
import io.mohajistudio.tangerine.prototype.infra.place.dto.PlaceKakaoSearchApiResultDTO;
import io.mohajistudio.tangerine.prototype.infra.place.service.PlaceApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceMapper placeMapper;
    private final PlaceApiService placeApiService;

    @GetMapping("/search")
    public PlaceKakaoSearchApiResultDTO placeSearch(@RequestParam("query") String query, @ModelAttribute PageableParam pageableParam) {
        return placeApiService.searchPlace(query, pageableParam.getPage(), pageableParam.getSize());
    }

    @PostMapping("/kakao")
    public PlaceDTO.Details kakaoPlaceAdd(@Valid @RequestBody PlaceKakaoSearchApiDTO placeKakaoSearchApiDTO) {
        Place place = placeService.addKakaoPlace(placeMapper.toEntity(placeKakaoSearchApiDTO));
        return placeMapper.toDetailsDTO(place);
    }

    @PostMapping
    public void placeAdd(@Valid @RequestBody PlaceDTO.Add placeAddRequest) {
        placeService.addPlace(placeMapper.toEntity(placeAddRequest));
    }
}