package io.mohajistudio.tangerine.prototype.infra.region.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;


@Data
public class RegionDTO {

        private String lastBuildDate;
        private int total;
        private int start;
        private int display;
        private List<Item> items;


}
