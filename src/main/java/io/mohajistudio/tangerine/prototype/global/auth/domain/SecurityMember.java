package io.mohajistudio.tangerine.prototype.global.auth.domain;

import io.jsonwebtoken.Claims;
import io.mohajistudio.tangerine.prototype.global.enums.Provider;
import io.mohajistudio.tangerine.prototype.global.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SecurityMember {
    private final Long id;
    private Role role;
    private final String email;
    private final Provider provider;

    public static SecurityMember fromClaims(Claims claims) {
        return SecurityMember.builder().id(Long.valueOf(claims.getId())).email(claims.get("email", String.class)).provider(Provider.fromValue(claims.get("provider", String.class))).role(Role.fromValue(claims.get("role", String.class))).build();
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
