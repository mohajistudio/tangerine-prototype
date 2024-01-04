package io.mohajistudio.tangerine.prototype.domain.place.mapper;

import io.mohajistudio.tangerine.prototype.domain.place.domain.PlaceCategory;
import io.mohajistudio.tangerine.prototype.domain.place.dto.PlaceCategoryDTO;
import org.mapstruct.Mapper;

@Mapper
public interface PlaceCategoryMapper {
    PlaceCategoryDTO toDTO(PlaceCategory placeCategory);
}
