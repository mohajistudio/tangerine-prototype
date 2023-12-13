package io.mohajistudio.tangerine.prototype.domain.member.mapper;

import io.mohajistudio.tangerine.prototype.domain.member.domain.Member;
import io.mohajistudio.tangerine.prototype.domain.member.domain.MemberProfile;
import io.mohajistudio.tangerine.prototype.domain.member.dto.MemberDTO;
import io.mohajistudio.tangerine.prototype.domain.member.dto.MemberProfileDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-13T14:02:18+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Amazon.com Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberDTO toDTO(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDTO.MemberDTOBuilder memberDTO = MemberDTO.builder();

        memberDTO.id( member.getId() );
        memberDTO.memberProfile( toDTO( member.getMemberProfile() ) );

        return memberDTO.build();
    }

    @Override
    public MemberProfileDTO toDTO(MemberProfile memberProfile) {
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
}
