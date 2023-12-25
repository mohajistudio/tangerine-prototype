package io.mohajistudio.tangerine.prototype.domain.comment.mapper;

import io.mohajistudio.tangerine.prototype.domain.comment.domain.Comment;
import io.mohajistudio.tangerine.prototype.domain.comment.dto.CommentDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CommentMapper {
    Comment commentAddDtoToComment(CommentDTO.Add commentDTO);
}
