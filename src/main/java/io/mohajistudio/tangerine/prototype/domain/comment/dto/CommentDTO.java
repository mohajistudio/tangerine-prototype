package io.mohajistudio.tangerine.prototype.domain.comment.dto;

import io.mohajistudio.tangerine.prototype.domain.member.dto.MemberDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    @NotNull
    private String content;
    private CommentDTO.Compact parentComment;
    private CommentDTO.Compact replyComment;

    @Getter
    @Setter
    public static class Compact {
        private Long id;
    }

    @Getter
    @Setter
    public static class Add extends CommentDTO {

    }

    @Getter
    @Setter
    public static class Details extends CommentDTO {
        private int groupNumber;
        private int favoriteCnt;
        private MemberDTO member;
        private PostDTO post;
    }
}
