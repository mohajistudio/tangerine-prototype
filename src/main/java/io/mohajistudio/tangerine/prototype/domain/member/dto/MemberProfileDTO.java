package io.mohajistudio.tangerine.prototype.domain.member.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberProfileDTO {
    private Long id;
    private String name;
    private String nickname;
    private char sex;
    private String phone;
    private String thumbnail;
    private LocalDate birthday;
}
