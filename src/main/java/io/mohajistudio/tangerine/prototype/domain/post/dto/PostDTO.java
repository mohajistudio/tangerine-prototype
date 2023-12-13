package io.mohajistudio.tangerine.prototype.domain.post.dto;

import io.mohajistudio.tangerine.prototype.domain.member.dto.MemberDTO;
import io.mohajistudio.tangerine.prototype.domain.member.mapper.MemberMapper;
import io.mohajistudio.tangerine.prototype.domain.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;


@Getter
@Builder
@AllArgsConstructor
public class PostDTO {
    private String title;

    private LocalDate visitedAt;

    private int commentCnt;

    private int favoriteCnt;

    private short blockCnt;

    private MemberDTO member;
}
