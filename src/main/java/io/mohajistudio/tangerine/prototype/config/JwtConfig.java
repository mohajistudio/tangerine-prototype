package io.mohajistudio.tangerine.prototype.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.jwt")
public class JwtConfig {
    private String secretKey;
}