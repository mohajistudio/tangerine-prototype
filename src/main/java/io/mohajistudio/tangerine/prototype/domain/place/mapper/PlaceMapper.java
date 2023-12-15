package io.mohajistudio.tangerine.prototype.domain.place.mapper;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.domain.place.dto.PlaceDTO;
import io.mohajistudio.tangerine.prototype.domain.place.dto.PlaceResponse;
import org.mapstruct.Mapper;

@Mapper
public interface PlaceMapper {
    Place placeAddRequestToPlace(PlaceDTO placeAddRequest);
    PlaceResponse placeToPlaceAddResponse(Place place);
}
