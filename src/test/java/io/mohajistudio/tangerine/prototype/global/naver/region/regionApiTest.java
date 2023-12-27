package io.mohajistudio.tangerine.prototype.global.naver.region;

import io.mohajistudio.tangerine.prototype.infra.region.generator.RepresentativeRegionGenerator;
import io.mohajistudio.tangerine.prototype.infra.region.dto.RegionDTO;
import io.mohajistudio.tangerine.prototype.infra.region.service.RegionApiServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringBootTest
class regionApiTest {

    @Autowired
    private RegionApiServiceImpl regionApiServiceImpl;
    @Autowired
    private RepresentativeRegionGenerator RRG;

    @AfterEach
    void afterEach() {
        // 각 테스트 메서드가 종료될 때마다 1초 딜레이 추가
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    void getRegionData() {
        RegionDTO data = regionApiServiceImpl.getRegionData("강남대학교");
        System.out.println("data = " + data.toString());
    }
    @Test
    void generateProvinces(){
        List<RegionDTO> places = new ArrayList<>();
        places.add(regionApiServiceImpl.getRegionData("합정역"));
        places.add(regionApiServiceImpl.getRegionData("강남역"));
        places.add(regionApiServiceImpl.getRegionData("서울역"));
        places.add(regionApiServiceImpl.getRegionData("부산역"));
        places.add(regionApiServiceImpl.getRegionData("백남준아트센터"));
        List<String> RepresentativeRegion = RRG.generate(places);
        System.out.println("대표지역 = " + RepresentativeRegion);
        List<String> expected = Arrays.asList("서울특별시");
        assertArrayEquals(expected.toArray(), RepresentativeRegion.toArray());
    }
    @Test
    void generateCities(){
        List<RegionDTO> places = new ArrayList<>();
        places.add(regionApiServiceImpl.getRegionData("광명시청"));
        places.add(regionApiServiceImpl.getRegionData("하안중학교"));
        places.add(regionApiServiceImpl.getRegionData("소하상업지구"));
        places.add(regionApiServiceImpl.getRegionData("광명역"));
        places.add(regionApiServiceImpl.getRegionData("광명사거리역"));
        List<String> RepresentativeRegion = RRG.generate(places);
        System.out.println("대표지역 = " + RepresentativeRegion);

        List<String> expected = Arrays.asList("경기도", "광명시");
        assertArrayEquals(expected.toArray(), RepresentativeRegion.toArray());
    }
    @Test
    void generateDistricts(){
        List<RegionDTO> places = new ArrayList<>();
        places.add(regionApiServiceImpl.getRegionData("송도센트럴파크"));
        places.add(regionApiServiceImpl.getRegionData("인천대교"));
        places.add(regionApiServiceImpl.getRegionData("미추홀공원"));
        places.add(regionApiServiceImpl.getRegionData("흥륜사"));
        places.add(regionApiServiceImpl.getRegionData("해돋이공원"));
        List<String> RepresentativeRegion = RRG.generate(places);
        System.out.println("대표지역 = " + RepresentativeRegion);
        List<String> expected = Arrays.asList("인천광역시", "연수구", "송도동");
        assertArrayEquals(expected.toArray(), RepresentativeRegion.toArray());
    }
    @Test
    void generateKorea(){
        List<RegionDTO> places = new ArrayList<>();
        places.add(regionApiServiceImpl.getRegionData("강원도청"));
        places.add(regionApiServiceImpl.getRegionData("경기도청"));
        places.add(regionApiServiceImpl.getRegionData("서울시청"));
        places.add(regionApiServiceImpl.getRegionData("전라도청"));
        places.add(regionApiServiceImpl.getRegionData("경상도청"));
        List<String> RepresentativeRegion = RRG.generate(places);
        System.out.println("대표지역 = " + RepresentativeRegion);
        List<String> expected = Arrays.asList("전국");
        assertArrayEquals(expected.toArray(), RepresentativeRegion.toArray());
    }
    @Test
    void generateOne(){
        List<RegionDTO> places = new ArrayList<>();
        places.add(regionApiServiceImpl.getRegionData("범박고등학교"));
        List<String> RepresentativeRegion = RRG.generate(places);
        System.out.println("대표지역 = " + RepresentativeRegion);
        List<String> expected = Arrays.asList("경기도","부천시","범박동");
        assertArrayEquals(expected.toArray(), RepresentativeRegion.toArray());
    }
}