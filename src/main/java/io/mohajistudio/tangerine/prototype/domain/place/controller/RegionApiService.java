package io.mohajistudio.tangerine.prototype.domain.place.controller;

import io.mohajistudio.tangerine.prototype.infra.region.dto.RegionDTO;

public interface RegionApiService  {
    RegionDTO getRegionData(String query);
}
