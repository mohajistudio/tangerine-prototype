package io.mohajistudio.tangerine.prototype.domain.member.dto;

import java.util.Set;

public class FollowDTO {
    static class Follow {
        Set<MemberDTO> follows;
    }

    static class FollowMember {
        Set<MemberDTO> followMembers;
    }
}
