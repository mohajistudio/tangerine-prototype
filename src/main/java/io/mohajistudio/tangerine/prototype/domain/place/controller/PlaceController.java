package io.mohajistudio.tangerine.prototype.domain.place.controller;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.domain.place.dto.PlaceCategoryDTO;
import io.mohajistudio.tangerine.prototype.domain.place.dto.PlaceDTO;
import io.mohajistudio.tangerine.prototype.domain.place.dto.RepresentativeRegionDTO;
import io.mohajistudio.tangerine.prototype.domain.place.mapper.PlaceCategoryMapper;
import io.mohajistudio.tangerine.prototype.domain.place.mapper.PlaceMapper;
import io.mohajistudio.tangerine.prototype.domain.place.service.PlaceService;
import io.mohajistudio.tangerine.prototype.global.common.PageableParam;
import io.mohajistudio.tangerine.prototype.infra.place.dto.AddressDTO;
import io.mohajistudio.tangerine.prototype.infra.place.dto.PlaceKakaoSearchApiDTO;
import io.mohajistudio.tangerine.prototype.infra.place.dto.PlaceKakaoSearchApiResultDTO;
import io.mohajistudio.tangerine.prototype.infra.place.service.PlaceApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceMapper placeMapper;
    private final PlaceCategoryMapper placeCategoryMapper;
    private final PlaceApiService placeApiService;
    private final RepresentService representService;

    @GetMapping
    public Page<PlaceDTO.Details> placeListByPage(@RequestParam("query") String query, @ModelAttribute PageableParam pageableParam) {
        Pageable pageable = PageRequest.of(pageableParam.getPage() - 1, pageableParam.getSize());
        return placeService.findPlaceListByPage(query, pageable).map(placeMapper::toDetailsDTO);
    }

    @PostMapping
    public void placeAdd(@Valid @RequestBody PlaceDTO.Add placeAddRequest) {
        placeService.addPlace(placeMapper.toEntity(placeAddRequest));
    }

    @GetMapping("/kakao")
    public PlaceKakaoSearchApiResultDTO kakaoPlaceListByPage(@RequestParam("query") String query, @ModelAttribute PageableParam pageableParam) {
        return placeApiService.searchPlace(query, pageableParam.getPage(), pageableParam.getSize());
    }

    @PostMapping("/kakao")
    public PlaceDTO.Details kakaoPlaceAdd(@Valid @RequestBody PlaceKakaoSearchApiDTO placeKakaoSearchApiDTO) {
        Place place = placeService.addKakaoPlace(placeMapper.toEntity(placeKakaoSearchApiDTO));
        return placeMapper.toDetailsDTO(place);
    }

    @GetMapping("/categories")
    public List<PlaceCategoryDTO> placeCategoryList() {
        return placeService.findPlaceCategoryList().stream().map(placeCategoryMapper::toDTO).toList();
    }

    @PostMapping("/recommend")
    public RepresentativeRegionDTO recommendRegion(@Valid @RequestBody List<AddressDTO> places){
        RepresentativeRegionDTO regions = new RepresentativeRegionDTO();
        regions.setRegions(representService.extract(places));
        return regions;
    }
}