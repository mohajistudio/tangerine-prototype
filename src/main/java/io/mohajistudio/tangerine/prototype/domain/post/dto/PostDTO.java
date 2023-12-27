package io.mohajistudio.tangerine.prototype.domain.post.dto;

import io.mohajistudio.tangerine.prototype.domain.member.dto.MemberDTO;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "게시글의 제목", example = "테스트 제목")
    private String title;
    @NotNull
    @Schema(description = "방문 날짜")
    private LocalDate visitedAt;

    @Getter
    @Setter
    @Schema(name = "PostDTO.Details", description = "게시글의 상세를 반환할 때 사용할 DTO")
    public static class Details extends PostDTO.Compact {
        @Valid
        private Set<TextBlockDTO.Details> textBlocks;
        @Valid
        private Set<PlaceBlockDTO.Details> placeBlocks;
    }

    @Getter
    @Setter
    @Schema(name = "PostDTO.Add", description = "게시글 추가를 위한 DTO")
    public static class Add extends PostDTO {
        @Valid
        @ArraySchema(arraySchema = @Schema(description = "텍스트 블럭"))
        private Set<TextBlockDTO.Add> textBlocks;
        @Valid
        @ArraySchema(arraySchema = @Schema(description = "장소 블럭"))
        private Set<PlaceBlockDTO.Add> placeBlocks;
    }

    @Getter
    @Setter
    @Schema(name = "PostDTO.Details", description = "게시글 목록을 반환할 때 사용할 DTO")
    public static class Compact extends PostDTO {
        private Long id;
        private int commentCnt;
        private int favoriteCnt;
        private short blockCnt;
        private MemberDTO member;
    }
}
