package io.mohajistudio.tangerine.prototype.domain.place.mapper;

import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.domain.place.dto.PlaceDTO;
import io.mohajistudio.tangerine.prototype.global.enums.ErrorCode;
import io.mohajistudio.tangerine.prototype.global.error.exception.BusinessException;
import io.mohajistudio.tangerine.prototype.infra.place.dto.PlaceKakaoSearchApiDTO;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.mapstruct.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mapper
public interface PlaceMapper {
    String regex = "^(?<province>\\S+)\\s+(?<city>\\S+)\\s+(?<district>\\S+)\\s+(?<detail>.+)$";
    Pattern pattern = Pattern.compile(regex);

    @Mapping(source = "address", target = "addressProvince", qualifiedByName = "convertToProvince")
    @Mapping(source = "address", target = "addressCity", qualifiedByName = "convertToCity")
    @Mapping(source = "address", target = "addressDistrict", qualifiedByName = "convertToDistrict")
    @Mapping(source = "address", target = "addressDetail", qualifiedByName = "convertToDetail")
    @Mapping(source = ".", target = "coordinates", qualifiedByName = "setAddDTOCoordinates")
    Place toEntity(PlaceDTO.Add placeAddDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "id", target = "providerId")
    @Mapping(source = "placeName", target = "name")
    @Mapping(source = "roadAddressName", target = "roadAddress")
    @Mapping(source = "addressName", target = "addressProvince", qualifiedByName = "convertToProvince")
    @Mapping(source = "addressName", target = "addressCity", qualifiedByName = "convertToCity")
    @Mapping(source = "addressName", target = "addressDistrict", qualifiedByName = "convertToDistrict")
    @Mapping(source = "addressName", target = "addressDetail", qualifiedByName = "convertToDetail")
    @Mapping(source = ".", target = "coordinates", qualifiedByName = "setKakaoCoordinates")
    Place toEntity(PlaceKakaoSearchApiDTO placeKakaoSearchApiDTO);

    @Mapping(source = ".", target = "address", qualifiedByName = "setAddress")
    PlaceDTO.Details toDetailsDTO(Place place);

    @Named("setAddress")
    default String setAddress(Place place) {
        return place.getAddress();
    }

    @Named("setKakaoCoordinates")
    default Point setCoordinates(PlaceKakaoSearchApiDTO placeKakaoSearchApiDTO) {
        double lat = Double.parseDouble(placeKakaoSearchApiDTO.getX());
        double lng = Double.parseDouble(placeKakaoSearchApiDTO.getY());
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        return geometryFactory.createPoint(new Coordinate(lat, lng));
    }

    @Named("setAddDTOCoordinates")
    default Point setCoordinates(PlaceDTO.Add placeDetailsDTO) {
        double lat = placeDetailsDTO.getCoordinates().getLat();
        double lng = placeDetailsDTO.getCoordinates().getLng();
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        return geometryFactory.createPoint(new Coordinate(lat, lng));
    }

    @Named("convertToProvince") // 2
    default String convertToProvince(String address) {
        Matcher matcher = pattern.matcher(address);
        if (matcher.matches()) {
            return matcher.group("province");
        }
        throw new BusinessException("지원하지 않는 주소 형식입니다", ErrorCode.INVALID_INPUT_VALUE);
    }

    @Named("convertToCity") // 2
    default String convertToCity(String address) {
        Matcher matcher = pattern.matcher(address);
        if (matcher.matches()) {
            return matcher.group("city");
        }
        throw new BusinessException("지원하지 않는 주소 형식입니다", ErrorCode.INVALID_INPUT_VALUE);
    }

    @Named("convertToDistrict") // 2
    default String convertToDistrict(String address) {
        Matcher matcher = pattern.matcher(address);
        if (matcher.matches()) {
            return matcher.group("district");
        }
        throw new BusinessException("지원하지 않는 주소 형식입니다", ErrorCode.INVALID_INPUT_VALUE);
    }

    @Named("convertToDetail") // 2
    default String convertToDetail(String address) {
        Matcher matcher = pattern.matcher(address);
        if (matcher.matches()) {
            return matcher.group("detail");
        }
        throw new BusinessException("지원하지 않는 주소 형식입니다", ErrorCode.INVALID_INPUT_VALUE);
    }
}
