package io.mohajistudio.tangerine.prototype.security;

import io.jsonwebtoken.*;
import io.mohajistudio.tangerine.prototype.config.JwtConfig;
import io.mohajistudio.tangerine.prototype.dto.GeneratedTokenDTO;
import io.mohajistudio.tangerine.prototype.dto.SecurityMemberDTO;
import io.mohajistudio.tangerine.prototype.entity.Member;
import io.mohajistudio.tangerine.prototype.enums.Provider;
import io.mohajistudio.tangerine.prototype.enums.Role;
import io.mohajistudio.tangerine.prototype.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtConfig jwtConfig;
    private final MemberRepository memberRepository;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private Key signingKey;
    private JwtParser jwtParser;
    private static final Long ACCESS_TOKEN_PERIOD = 1000L * 60L * 60L; // 1시간
    private static final Long REFRESH_TOKEN_PERIOD = 1000L * 60L * 60L * 24L * 14L; // 2주

    @PostConstruct
    protected void init() {
        String secretKey = Base64.getEncoder().encodeToString(jwtConfig.getSecretKey().getBytes());
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        jwtParser = Jwts.parserBuilder().setSigningKey(signingKey).build();
    }

    @Transactional
    public GeneratedTokenDTO generateTokens(Long id, String email, String provider, String role) {
        String accessToken = generateToken(id, email, provider, role, ACCESS_TOKEN_PERIOD);
        String refreshToken = generateToken(id, email, provider, role, REFRESH_TOKEN_PERIOD);

        saveRefreshToken(id, refreshToken);

        return GeneratedTokenDTO.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    public GeneratedTokenDTO generateGuestToken(Long id, String email, String provider, String role) {
        String accessToken = generateToken(id, email, provider, role, ACCESS_TOKEN_PERIOD);

        return GeneratedTokenDTO.builder().accessToken(accessToken).build();
    }

    private String generateToken(Long id, String email, String provider, String role, Long tokenPeriod) {
        Claims claims = Jwts.claims().setSubject("id");
        claims.put("role", role);
        claims.put("provider", provider);
        claims.put("email", email);
        claims.setId(String.valueOf(id));
        Date now = new Date();

        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(new Date(now.getTime() + tokenPeriod)).signWith(signingKey, signatureAlgorithm).compact();
    }

    @Transactional
    public GeneratedTokenDTO reissueToken(String refreshToken) {
        SecurityMemberDTO securityMemberDTO = verifyToken(refreshToken);

        return generateTokens(securityMemberDTO.getId(), securityMemberDTO.getEmail(), securityMemberDTO.getProvider().name(), securityMemberDTO.getRole().name());
    }

    public SecurityMemberDTO verifyToken(String token) {
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();

            return SecurityMemberDTO.builder().id(Long.valueOf(claims.getId())).email(claims.get("email", String.class)).provider(Provider.fromValue(claims.get("provider", String.class))).role(Role.fromValue(claims.get("role", String.class))).build();
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new JwtException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            throw new JwtException("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new JwtException("잘못된 JWT 토큰입니다.");
        }
    }

    private void saveRefreshToken(Long id, String refreshToken) {
        Optional<Member> findMember = memberRepository.findById(id);
        findMember.ifPresent(member -> memberRepository.updateRefreshToken(member.getId(), refreshToken));
    }
}
