package io.mohajistudio.tangerine.prototype.domain.comment.dto;

import io.mohajistudio.tangerine.prototype.domain.member.dto.MemberDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDTO {
    @NotNull
    private String content;


    @Getter
    @Setter
    public static class Compact {
        private Long id;
    }

    @Getter
    @Setter
    public static class Add extends CommentDTO {
        private CommentDTO.Compact parentComment;
        private CommentDTO.Compact replyComment;
    }

    @Getter
    @Setter
    public static class Details extends CommentDTO.Add {
        private Long id;
        private int groupNumber;
        private int favoriteCnt;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private MemberDTO member;
    }

    @Getter
    @Setter
    public static class Patch extends CommentDTO {
        private Long id;
    }
}
