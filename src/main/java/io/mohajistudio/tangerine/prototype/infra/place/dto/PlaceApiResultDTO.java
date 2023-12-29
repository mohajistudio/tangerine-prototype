package io.mohajistudio.tangerine.prototype.infra.place.dto;

import lombok.Data;

import java.util.List;


@Data
public class PlaceApiResultDTO {
        private String lastBuildDate;
        private int total;
        private int start;
        private int display;
        private List<PlaceApiDTO> items;
}
