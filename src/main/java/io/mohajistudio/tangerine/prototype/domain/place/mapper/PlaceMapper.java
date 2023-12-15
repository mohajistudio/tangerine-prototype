package io.mohajistudio.tangerine.prototype.domain.place.mapper;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.domain.place.dto.PlaceDTO;
import org.mapstruct.Mapper;

@Mapper
public interface PlaceMapper {
    Place placeAddRequestToPlace(PlaceDTO.Add placeAddDTO);
    PlaceDTO.Details placeToPlaceAddResponse(Place place);
}
