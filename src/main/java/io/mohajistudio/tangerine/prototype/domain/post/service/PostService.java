package io.mohajistudio.tangerine.prototype.domain.post.service;

import io.mohajistudio.tangerine.prototype.domain.member.domain.Member;
import io.mohajistudio.tangerine.prototype.domain.member.repository.MemberRepository;
import io.mohajistudio.tangerine.prototype.domain.post.domain.Post;
import io.mohajistudio.tangerine.prototype.domain.post.dto.PostDTO;
import io.mohajistudio.tangerine.prototype.domain.post.mapper.PostMapper;
import io.mohajistudio.tangerine.prototype.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PostMapper postMapper;

    public Post addPost (Post post, Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        findMember.ifPresent(post::setMember);

        return postRepository.save(post);
    }

    public Page<PostDTO> findPostListWithPagination(Pageable pageable) {
        Page<Post> findPost = postRepository.findAll(pageable);
        return findPost.map(postMapper::toDTO);
    }
}
