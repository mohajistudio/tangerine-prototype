package io.mohajistudio.tangerine.prototype.domain.place.controller;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.domain.place.dto.PlaceAddRequest;
import io.mohajistudio.tangerine.prototype.domain.place.dto.PlaceResponse;
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
    public PlaceResponse placeAdd(@Valid @RequestBody PlaceAddRequest placeAddRequest) {
        Place place = placeService.addPlace(placeMapper.placeAddRequestToPlace(placeAddRequest));
        return placeMapper.placeToPlaceAddResponse(place);
    }

}
