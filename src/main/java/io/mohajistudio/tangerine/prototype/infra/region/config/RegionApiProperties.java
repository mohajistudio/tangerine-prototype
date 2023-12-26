package io.mohajistudio.tangerine.prototype.infra.region.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "region")
public class RegionApiProperties {
    private String url;
    private String client_id;
    private String client_secret;


}
