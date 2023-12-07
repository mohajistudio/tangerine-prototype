package io.mohajistudio.tangerine.prototype.dto;

import io.jsonwebtoken.Claims;
import io.mohajistudio.tangerine.prototype.enums.Provider;
import io.mohajistudio.tangerine.prototype.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SecurityMemberDTO {
    private final Long id;
    private final Role role;
    private final String email;
    private final Provider provider;

    public static SecurityMemberDTO fromClaims(Claims claims) {
        return SecurityMemberDTO.builder().id(Long.valueOf(claims.getId())).email(claims.get("email", String.class)).provider(Provider.fromValue(claims.get("provider", String.class))).role(Role.fromValue(claims.get("role", String.class))).build();
    }
}
