package io.mohajistudio.tangerine.prototype.domain.comment.controller;

import io.mohajistudio.tangerine.prototype.domain.comment.dto.CommentDTO;
import io.mohajistudio.tangerine.prototype.domain.comment.mapper.CommentMapper;
import io.mohajistudio.tangerine.prototype.domain.comment.service.CommentService;
import io.mohajistudio.tangerine.prototype.global.auth.domain.SecurityMember;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @GetMapping
    public Page<CommentDTO.Details> commentListByPage(@PathVariable(name = "postId") Long postId, @ModelAttribute PageableParam pageableParam) {
        Pageable pageable = PageRequest.of(pageableParam.getPage(), pageableParam.getSize());
        Page<Comment> commentListByPage = commentService.findCommentListByPage(postId, pageable);

        return commentListByPage.map(commentMapper::commentAddDtoToComment);
    }

    @PostMapping
    public void commentAdd(@RequestBody @Valid CommentDTO.Add commentAddDTO, @PathVariable(name = "postId") Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();

        commentService.AddComment(commentMapper.commentAddDtoToComment(commentAddDTO), postId, securityMember.getId());
    }
}
