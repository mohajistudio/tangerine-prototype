package io.mohajistudio.tangerine.prototype.infra.place.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class PlaceKakaoSearchApiResultDTO {
        private PlaceKakaoSearchApiMetaDTO meta;
        private List<PlaceKakaoSearchApiDTO> documents;
}
