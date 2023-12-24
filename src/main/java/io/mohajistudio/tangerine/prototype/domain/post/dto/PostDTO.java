package io.mohajistudio.tangerine.prototype.domain.post.dto;

import io.mohajistudio.tangerine.prototype.domain.member.dto.MemberDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class PostDTO {
    @NotNull
    private String title;
    @NotNull
    private LocalDate visitedAt;

    @Getter
    @Setter
    public static class Details extends PostDTO.Compact {
        private Long id;
        @Valid
        private Set<TextBlockDTO.Details> textBlocks;
        @Valid
        private Set<PlaceBlockDTO.Details> placeBlocks;
    }

    @Getter
    @Setter
    public static class Add extends PostDTO {
        @Valid
        private Set<TextBlockDTO.Add> textBlocks;
        @Valid
        private Set<PlaceBlockDTO.Add> placeBlocks;
    }

    @Getter
    @Setter
    public static class Compact extends PostDTO {
        private int commentCnt;
        private int favoriteCnt;
        private short blockCnt;
        private MemberDTO member;
    }
}
