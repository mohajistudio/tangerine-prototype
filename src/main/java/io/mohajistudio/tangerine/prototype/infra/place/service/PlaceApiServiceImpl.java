package io.mohajistudio.tangerine.prototype.infra.place.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.global.enums.ErrorCode;
import io.mohajistudio.tangerine.prototype.global.error.exception.BusinessException;
import io.mohajistudio.tangerine.prototype.infra.place.config.PlaceApiProperties;
import io.mohajistudio.tangerine.prototype.infra.place.dto.PlaceApiResultDTO;
import io.mohajistudio.tangerine.prototype.infra.place.mapper.PlaceApiMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceApiServiceImpl implements PlaceApiService {
    private final PlaceApiProperties regionApiConfig;
    private final PlaceApiMapper placeApiMapper;

    public Set<Place> searchPlace(String query) {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            String encodedRegion = URLEncoder.encode(query, StandardCharsets.UTF_8);
            HttpGet getRequest = new HttpGet(regionApiConfig.getUrl() + "?query=" + encodedRegion + regionApiConfig.getUrlOptions());
            getRequest.addHeader("X-Naver-Client-Id", regionApiConfig.getClientId());
            getRequest.addHeader("X-Naver-Client-Secret", regionApiConfig.getClientSecret());

            CloseableHttpResponse response = client.execute(getRequest);
            ResponseHandler<String> handler = new BasicResponseHandler();
            String result = handler.handleResponse(response);
            ObjectMapper objectMapper = new ObjectMapper();
            PlaceApiResultDTO placeApiResultDTO = objectMapper.readValue(result, PlaceApiResultDTO.class);

            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                return placeApiResultDTO.getItems().stream().map(placeApiMapper::toEntity).collect(Collectors.toSet());
            } else {
                throw new BusinessException(result, ErrorCode.NAVER_REGION);
            }

        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            throw new BusinessException(ErrorCode.NAVER_REGION);
        }
    }
}
