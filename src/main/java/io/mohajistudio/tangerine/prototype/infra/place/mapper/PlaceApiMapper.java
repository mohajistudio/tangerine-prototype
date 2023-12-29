package io.mohajistudio.tangerine.prototype.infra.place.mapper;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.global.enums.ErrorCode;
import io.mohajistudio.tangerine.prototype.global.error.exception.BusinessException;
import io.mohajistudio.tangerine.prototype.infra.place.dto.PlaceApiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mapper
public interface PlaceApiMapper {
    String regex = "^(?<province>\\S+)\\s+(?<city>\\S+)\\s+(?<district>\\S+)\\s+(?<detail>.+)$";
    Pattern pattern = Pattern.compile(regex);

    @Mapping(source = "title", target = "name", qualifiedByName = "removeHtmlTag")
    @Mapping(source = "address", target = "addressProvince", qualifiedByName = "convertToProvince")
    @Mapping(source = "address", target = "addressCity", qualifiedByName = "convertToCity")
    @Mapping(source = "address", target = "addressDistrict", qualifiedByName = "convertToDistrict")
    @Mapping(source = "address", target = "addressDetail", qualifiedByName = "convertToDetail")
    @Mapping(source = "category", target = "description")
    Place toEntity(PlaceApiDTO placeApiDTO);

    @Named("removeHtmlTag")
    default String removeHtmlTag(String title) {
        return title.replace("<b>", "").replace("</b>", "");
    }

    @Named("convertToProvince") // 2
    default String convertToProvince(String address) {
        Matcher matcher = pattern.matcher(address);
        if (matcher.matches()) {
            return matcher.group("province");
        }
        throw new BusinessException("지원하지 않는 주소 형식이기 때문에 변환할 수 없습니다", ErrorCode.NAVER_REGION);
    }

    @Named("convertToCity") // 2
    default String convertToCity(String address) {
        Matcher matcher = pattern.matcher(address);
        if (matcher.matches()) {
            return matcher.group("city");
        }
        throw new BusinessException("지원하지 않는 주소 형식이기 때문에 변환할 수 없습니다", ErrorCode.NAVER_REGION);
    }

    @Named("convertToDistrict") // 2
    default String convertToDistrict(String address) {
        Matcher matcher = pattern.matcher(address);
        if (matcher.matches()) {
            return matcher.group("district");
        }
        throw new BusinessException("지원하지 않는 주소 형식이기 때문에 변환할 수 없습니다", ErrorCode.NAVER_REGION);
    }

    @Named("convertToDetail") // 2
    default String convertToDetail(String address) {
        Matcher matcher = pattern.matcher(address);
        if (matcher.matches()) {
            return matcher.group("detail");
        }
        throw new BusinessException("지원하지 않는 주소 형식이기 때문에 변환할 수 없습니다", ErrorCode.NAVER_REGION);
    }
}
