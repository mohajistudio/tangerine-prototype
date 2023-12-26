package io.mohajistudio.tangerine.prototype.domain.comment.service;

import io.mohajistudio.tangerine.prototype.domain.comment.domain.Comment;
import io.mohajistudio.tangerine.prototype.domain.comment.dto.CommentDTO;
import io.mohajistudio.tangerine.prototype.domain.comment.repository.CommentRepository;
import io.mohajistudio.tangerine.prototype.domain.member.domain.Member;
import io.mohajistudio.tangerine.prototype.domain.member.repository.MemberRepository;
import io.mohajistudio.tangerine.prototype.domain.post.domain.Post;
import io.mohajistudio.tangerine.prototype.domain.post.repository.PostRepository;
import io.mohajistudio.tangerine.prototype.global.enums.ErrorCode;
import io.mohajistudio.tangerine.prototype.global.error.exception.BusinessException;
import io.mohajistudio.tangerine.prototype.global.error.exception.UrlNotFoundException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public void AddComment(Comment comment, Long postId, Long memberId) {

        Optional<Member> findMember = memberRepository.findById(memberId);
        findMember.ifPresent(comment::setMember);

        Optional<Post> findPost = postRepository.findById(postId);
        if (findPost.isEmpty()) {
            throw new UrlNotFoundException();
        }
        comment.setPost(findPost.get());

        if (comment.getReplyComment() == null && comment.getParentComment() == null) { //대댓글이 아닐경우
            Integer maxGroupNumber = commentRepository.findMaxGroupNumberByPostId(postId);
            if (maxGroupNumber == null) {
                comment.setGroupNumber(0);
            } else {
                comment.setGroupNumber(maxGroupNumber + 1);
            }
        } else if (comment.getReplyComment() != null && comment.getParentComment() != null) { //대댓글일 경우
            Integer parentGroupNumber = commentRepository.findGroupNumberById(comment.getParentComment().getId());
            if (parentGroupNumber == null) {
                throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
            }
            comment.setGroupNumber(parentGroupNumber);
        } else {
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }

        commentRepository.save(comment);
    }

    public Page<Comment> findCommentListByPage(Long postId, Pageable pageable) {
        Optional<Post> findPost = postRepository.findById(postId);
        if (findPost.isEmpty()) {
            throw new UrlNotFoundException();
        }

        return commentRepository.findByPostId(postId, pageable);
    }

    public void patchComment(Comment comment, Long postId, Long memberId) {

    }
}
