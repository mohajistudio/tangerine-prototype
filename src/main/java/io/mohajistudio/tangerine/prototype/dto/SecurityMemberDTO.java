package io.mohajistudio.tangerine.prototype.dto;

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
}
