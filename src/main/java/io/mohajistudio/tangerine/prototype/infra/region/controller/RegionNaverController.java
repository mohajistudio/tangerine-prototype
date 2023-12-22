package io.mohajistudio.tangerine.prototype.infra.region.controller;


import io.mohajistudio.tangerine.prototype.infra.region.dto.RegionDTO;
import io.mohajistudio.tangerine.prototype.infra.region.service.RegionApiServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/naver")
@RequiredArgsConstructor
public class RegionNaverController {
    private final RegionApiServiceImpl regionApiServiceImpl;


    @GetMapping
    public RegionDTO placeSearch(@RequestParam("query") String searchWord) {

        return regionApiServiceImpl.getRegionData(searchWord);
    }
}
