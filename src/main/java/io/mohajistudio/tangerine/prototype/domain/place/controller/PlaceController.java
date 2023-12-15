package io.mohajistudio.tangerine.prototype.domain.place.controller;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.domain.place.dto.PlaceDTO;
import io.mohajistudio.tangerine.prototype.domain.place.mapper.PlaceMapper;
import io.mohajistudio.tangerine.prototype.domain.place.service.PlaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceMapper placeMapper;

    @PostMapping
    public PlaceDTO.Details placeAdd(@Valid @RequestBody PlaceDTO.Add placeAddRequest) {
        Place place = placeService.addPlace(placeMapper.placeAddRequestToPlace(placeAddRequest));
        return placeMapper.placeToPlaceAddResponse(place);
    }
}
