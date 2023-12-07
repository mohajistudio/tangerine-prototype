package io.mohajistudio.tangerine.prototype.config;

import io.mohajistudio.tangerine.prototype.security.JwtAccessDeniedHandler;
import io.mohajistudio.tangerine.prototype.security.JwtAuthenticationEntryPoint;
import io.mohajistudio.tangerine.prototype.security.filter.JwtAuthFilter;
import io.mohajistudio.tangerine.prototype.security.handler.OAuth2FailureHandler;
import io.mohajistudio.tangerine.prototype.security.handler.OAuth2SuccessHandler;
import io.mohajistudio.tangerine.prototype.security.service.CustomOAuth2UserService;
import io.mohajistudio.tangerine.prototype.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final OAuth2FailureHandler oAuth2FailureHandler;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/", "/login/**", "/error", "/token").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/register").hasAuthority("GUEST");
                    auth.requestMatchers(HttpMethod.GET, "/secured/home").hasAuthority("MEMBER");
                    auth.anyRequest().authenticated();
                }).csrf(AbstractHttpConfigurer::disable).sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2Login(oauth2Login -> {
                    oauth2Login.successHandler(oAuth2SuccessHandler);
                    oauth2Login.failureHandler(oAuth2FailureHandler);
                    oauth2Login.userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(customOAuth2UserService));
                })
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling.authenticationEntryPoint(userAuthenticationEntryPoint);
                    exceptionHandling.accessDeniedHandler(jwtAccessDeniedHandler);
                })
                .addFilterBefore(new JwtAuthFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
