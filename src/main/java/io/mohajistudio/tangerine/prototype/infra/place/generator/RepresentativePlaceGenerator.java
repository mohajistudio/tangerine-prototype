package io.mohajistudio.tangerine.prototype.infra.place.generator;

import io.mohajistudio.tangerine.prototype.infra.place.dto.AddressDTO;
import io.mohajistudio.tangerine.prototype.infra.place.dto.PlaceApiResultDTO;
import io.mohajistudio.tangerine.prototype.infra.place.dto.PlaceApiDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Component
public class RepresentativePlaceGenerator {
    //post에서 수정, 저장 로직 만들때 대표지역을 설정하기 위해 사용
//post 수정, 저장 만들때 같은 지역 입력받으면 400에러 보내도록 예외처리 요망
    //인프라에 넣어놓긴 했는데 매개변수를 수정해서 place로 옮길 여지 있음(실질적으로 수정,삭제는 도메인에서 이루어지기 때문)
    public List<String> generate(List<PlaceApiResultDTO> places) {
        Map<String, Integer> provinces = new HashMap<>();
        Map<String, Integer> cities = new HashMap<>();
        Map<String, Integer> districts = new HashMap<>();
        if (places.size() == 1) {
            return generateForSinglePlace(places.get(0));
        }
        generateForPlace(places, provinces, cities, districts);

        List<String> result = findKeysInOrder(districts, cities, provinces);
        if (result.isEmpty()) {
            result.add("전국");
        }
        return result;
    }

    public List<String> generateForSinglePlace(PlaceApiResultDTO place) {
        List<PlaceApiDTO> items = place.getItems();
        if (!items.isEmpty()) {
            String address = items.get(0).getAddress();
            AddressDTO addressInfo = extracted(address);
            List<String> result = new ArrayList<>();
            result.add(addressInfo.getProvince());
            result.add(addressInfo.getCity());
            result.add(addressInfo.getDistrict());
            return result;
        } else {
            return Collections.emptyList();
        }
    }

    private static void generateForPlace(List<PlaceApiResultDTO> places, Map<String, Integer> provinces, Map<String, Integer> cities, Map<String, Integer> districts) {
        for (PlaceApiResultDTO place : places) {
            List<PlaceApiDTO> item = place.getItems();
            String address = item.get(0).getAddress();
            //정규표현식을 이용하여 주소 스트링을 파싱
            AddressDTO addressInfo = extracted(address);
            //key value로 저장//동일한 키 존재시 value1 증가
            provinces.compute(addressInfo.getProvince(), (key, value) -> (value == null) ? 1 : ++value);
            cities.compute(addressInfo.getCity(), (key, value) -> (value == null) ? 1 : ++value);
            districts.compute(addressInfo.getDistrict(), (key, value) -> (value == null) ? 1 : ++value);
        }
    }

    private static List<String> findKeysInOrder(Map<String, Integer>... maps) {
        List<String> keys = new ArrayList<>();

        for (Map<String, Integer> map : maps) {
            String key = findKey(map);
            if (key != null) {
                keys.add(key);
            }
        }
        Collections.reverse(keys);
        return keys;
    }

    private static String findKey(Map<String, Integer> map) {
        String maxKey = null;
        int maxValue = Integer.MIN_VALUE; // 최대 값을 가장 작은 정수 값으로 초기화

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int currentValue = entry.getValue();

            if (currentValue > maxValue) {
                maxValue = currentValue;
                maxKey = entry.getKey();
            }
        }

        return (maxValue == 1 ? null : maxKey);
    }

    public static AddressDTO extracted(String address) {
        String regex = "^(?<province>\\S+)\\s+(?<city>\\S+)\\s+(?<district>\\S+)\\s+(?<detail>.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(address);

        if (matcher.matches()) {
            String province = matcher.group("province");
            String city = matcher.group("city");
            String district = matcher.group("district");
            String detail = matcher.group("detail");

            return AddressDTO.builder().province(province).city(city).district(district).detail(detail).build();
        } else {
            throw new RuntimeException();
        }
    }
}
