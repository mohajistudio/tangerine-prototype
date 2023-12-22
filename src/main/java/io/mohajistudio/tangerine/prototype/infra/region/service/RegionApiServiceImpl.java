package io.mohajistudio.tangerine.prototype.infra.region.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mohajistudio.tangerine.prototype.domain.place.controller.RegionApiService;
import io.mohajistudio.tangerine.prototype.global.error.exception.BusinessException;
import io.mohajistudio.tangerine.prototype.global.error.exception.CustomAuthenticationException;
import io.mohajistudio.tangerine.prototype.infra.region.config.RegionApiProperties;
import io.mohajistudio.tangerine.prototype.infra.region.dto.RegionDTO;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.NotActiveException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class RegionApiServiceImpl implements RegionApiService {
    private final RegionApiProperties regionApiConfig;


    public RegionDTO getRegionData(String region)  {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
            String encodedRegion = URLEncoder.encode(region, StandardCharsets.UTF_8.toString());
            HttpGet getRequest = new HttpGet(regionApiConfig.getUrl()+"?query="+encodedRegion); //GET 메소드 URL 생성
            getRequest.addHeader("X-Naver-Client-Id", regionApiConfig.getClient_id()); //KEY 입력
            getRequest.addHeader("X-Naver-Client-Secret", regionApiConfig.getClient_secret()); //KEY 입력

            CloseableHttpResponse response = client.execute(getRequest);
            ResponseHandler<String> handler = new BasicResponseHandler();
            String result = handler.handleResponse(response);
            //객체 매핑
            ObjectMapper objectMapper = new ObjectMapper();
            RegionDTO regionDto = objectMapper.readValue(result, RegionDTO.class);

            //Response 출력
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                return regionDto;
            } else {
                // 외부 API에서 받은 에러 메시지를 그대로 클라이언트에게 반환
                throw new HttpResponseException(response.getStatusLine().getStatusCode(),result);
            }

        } catch (Exception e){
            String errorMessage = "외부 API 호출 중 오류 발생: " + e.getMessage();
            throw new RuntimeException(errorMessage);
        }
    }
}
