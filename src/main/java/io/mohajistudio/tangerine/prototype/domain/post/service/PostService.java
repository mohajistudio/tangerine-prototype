package io.mohajistudio.tangerine.prototype.domain.post.service;

import io.mohajistudio.tangerine.prototype.domain.member.domain.Member;
import io.mohajistudio.tangerine.prototype.domain.member.repository.MemberRepository;
import io.mohajistudio.tangerine.prototype.domain.post.domain.FavoritePost;
import io.mohajistudio.tangerine.prototype.domain.post.domain.Post;
import io.mohajistudio.tangerine.prototype.domain.post.repository.FavoritePostRepository;
import io.mohajistudio.tangerine.prototype.domain.post.repository.PostRepository;
import io.mohajistudio.tangerine.prototype.global.enums.ErrorCode;
import io.mohajistudio.tangerine.prototype.global.error.exception.BusinessException;
import jakarta.transaction.Transactional;
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
    private final FavoritePostRepository favoritePostRepository;

    public Post addPost(Post post, Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        findMember.ifPresent(post::setMember);

        return postRepository.save(post);
    }

    public Page<Post> findPostListWithPagination(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional
    public void modifyFavoritePost(Long id, Long memberId) {
        Optional<Post> findPost = postRepository.findById(id);
        if (findPost.isEmpty()) throw new BusinessException(ErrorCode.ENTITY_NOT_FOUND);
        Post post = findPost.get();

        Optional<FavoritePost> findFavoritePost = favoritePostRepository.findByMemberIdAndPostId(memberId, id);
        if (findFavoritePost.isPresent()) {
            FavoritePost favoritePost = findFavoritePost.get();
            favoritePostRepository.delete(favoritePost);
            postRepository.updateFavoriteCnt(post.getId(), post.getFavoriteCnt() - 1);
        } else {
            Member member = Member.builder().id(memberId).build();
            FavoritePost favoritePost = FavoritePost.builder().member(member).post(post).build();
            favoritePostRepository.save(favoritePost);
            postRepository.updateFavoriteCnt(post.getId(), post.getFavoriteCnt() + 1);
        }
    }
}
