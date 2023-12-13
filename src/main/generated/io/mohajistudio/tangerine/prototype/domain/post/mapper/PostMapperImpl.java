package io.mohajistudio.tangerine.prototype.domain.post.mapper;

import io.mohajistudio.tangerine.prototype.domain.member.domain.Member;
import io.mohajistudio.tangerine.prototype.domain.member.domain.MemberProfile;
import io.mohajistudio.tangerine.prototype.domain.member.dto.MemberDTO;
import io.mohajistudio.tangerine.prototype.domain.member.dto.MemberProfileDTO;
import io.mohajistudio.tangerine.prototype.domain.post.domain.Post;
import io.mohajistudio.tangerine.prototype.domain.post.dto.PostDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-13T14:00:19+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Amazon.com Inc.)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDTO toDTO(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDTO.PostDTOBuilder postDTO = PostDTO.builder();

        postDTO.title( post.getTitle() );
        postDTO.visitedAt( post.getVisitedAt() );
        postDTO.commentCnt( post.getCommentCnt() );
        postDTO.favoriteCnt( post.getFavoriteCnt() );
        postDTO.blockCnt( post.getBlockCnt() );
        postDTO.member( memberToMemberDTO( post.getMember() ) );

        return postDTO.build();
    }

    protected MemberProfileDTO memberProfileToMemberProfileDTO(MemberProfile memberProfile) {
        if ( memberProfile == null ) {
            return null;
        }

        MemberProfileDTO.MemberProfileDTOBuilder memberProfileDTO = MemberProfileDTO.builder();

        memberProfileDTO.id( memberProfile.getId() );
        memberProfileDTO.name( memberProfile.getName() );
        memberProfileDTO.nickname( memberProfile.getNickname() );
        memberProfileDTO.sex( memberProfile.getSex() );
        memberProfileDTO.phone( memberProfile.getPhone() );
        memberProfileDTO.thumbnail( memberProfile.getThumbnail() );
        memberProfileDTO.birthday( memberProfile.getBirthday() );

        return memberProfileDTO.build();
    }

    protected MemberDTO memberToMemberDTO(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDTO.MemberDTOBuilder memberDTO = MemberDTO.builder();

        memberDTO.id( member.getId() );
        memberDTO.memberProfile( memberProfileToMemberProfileDTO( member.getMemberProfile() ) );

        return memberDTO.build();
    }
}
