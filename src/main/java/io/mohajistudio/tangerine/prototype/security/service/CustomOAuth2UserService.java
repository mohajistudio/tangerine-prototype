package io.mohajistudio.tangerine.prototype.security.service;

import io.mohajistudio.tangerine.prototype.entity.Member;
import io.mohajistudio.tangerine.prototype.entity.MemberProfile;
import io.mohajistudio.tangerine.prototype.enums.Provider;
import io.mohajistudio.tangerine.prototype.repository.MemberProfileRepository;
import io.mohajistudio.tangerine.prototype.repository.MemberRepository;
import io.mohajistudio.tangerine.prototype.security.OAuth2Attribute;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;
    private final MemberProfileRepository memberProfileRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> service = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = service.loadUser(userRequest);

        Map<String, Object> originAttributes = oAuth2User.getAttributes();

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2Attribute oAuth2Attribute = OAuth2Attribute.of(registrationId, userNameAttributeName, originAttributes);
        Map<String, Object> memberAttribute = oAuth2Attribute.convertToMap();

        Optional<Member> findMember = memberRepository.findByEmail(oAuth2Attribute.getEmail());
        Member member;
        if (findMember.isEmpty()) {
            member = Member.createGuest(Provider.fromValue(oAuth2Attribute.getProvider()), oAuth2Attribute.getEmail());
            memberRepository.save(member);
        } else {
            member = findMember.get();
        }

        Optional<MemberProfile> findMemberProfile = memberProfileRepository.findByMemberId(member.getId());

        memberAttribute.put("registered", findMemberProfile.isPresent());

        memberAttribute.put("id", member.getId());

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(member.getRole().name())), memberAttribute, oAuth2Attribute.getAttributeKey());
    }
}