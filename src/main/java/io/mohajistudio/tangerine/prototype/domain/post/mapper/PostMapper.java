package io.mohajistudio.tangerine.prototype.domain.post.mapper;

import io.mohajistudio.tangerine.prototype.domain.post.domain.Post;
import io.mohajistudio.tangerine.prototype.domain.post.dto.PostDTO;
import org.mapstruct.Mapper;

@Mapper
public interface PostMapper {
    PostDTO toDTO(Post post);
}
