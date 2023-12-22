package io.mohajistudio.tangerine.prototype.domain.place.controller;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.domain.place.dto.PlaceDTO;
import io.mohajistudio.tangerine.prototype.domain.place.mapper.PlaceMapper;
import io.mohajistudio.tangerine.prototype.domain.place.service.PlaceService;
import io.mohajistudio.tangerine.prototype.infra.region.dto.RegionDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceMapper placeMapper;
    private final RegionApiService regionApiService;

    //url을 통해 검색어를 입력받기에 인코딩된 문자를 넘겨주어야 함
    @GetMapping("/api")
    public RegionDTO placeSearch(@RequestParam("query") String searchWord) {
        return regionApiService.getRegionData(searchWord);
    }

    @PostMapping
    public PlaceDTO.Details placeAdd(@Valid @RequestBody PlaceDTO.Add placeAddRequest) {
        Place place = placeService.addPlace(placeMapper.placeAddRequestToPlace(placeAddRequest));
        return placeMapper.placeToPlaceAddResponse(place);
    }
}
