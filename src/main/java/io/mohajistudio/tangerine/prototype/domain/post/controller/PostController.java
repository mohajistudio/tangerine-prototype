package io.mohajistudio.tangerine.prototype.domain.post.controller;

import io.mohajistudio.tangerine.prototype.domain.post.domain.Post;
import io.mohajistudio.tangerine.prototype.domain.post.dto.PostDTO;
import io.mohajistudio.tangerine.prototype.domain.post.mapper.PostMapper;
import io.mohajistudio.tangerine.prototype.domain.post.service.PostService;
import io.mohajistudio.tangerine.prototype.global.auth.domain.SecurityMember;
import io.mohajistudio.tangerine.prototype.global.common.PageableParam;
import io.mohajistudio.tangerine.prototype.global.enums.ErrorCode;
import io.mohajistudio.tangerine.prototype.global.error.exception.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Tag(name = "Post", description = "Post API")
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping
    @Operation(summary = "페이징 된 게시글 목록", description = "page와 size 값을 넘기면 페이징 된 게시글 목록을 반환합니다. 기본 값은 page는 0, size는 10 입니다.")
    public Page<PostDTO.Compact> postListByPage(@Parameter(name = "PageableParam") @ModelAttribute PageableParam pageableParam) {
        Pageable pageable = PageRequest.of(pageableParam.getPage(), pageableParam.getSize());
        Page<Post> postListWithPagination = postService.findPostListByPage(pageable);
        return postListWithPagination.map(postMapper::toCompactDTO);
    }

    @PostMapping
    public void postAdd(@Valid @RequestBody PostDTO.Add postAddDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();

        Post post = postMapper.toEntity(postAddDTO);

        postService.addPost(post, securityMember.getId());
    }

    @GetMapping("/{id}")
    public PostDTO.Details postDetails(@PathVariable("id") Long id) {
        Post postDetails = postService.findPostDetails(id);
        return postMapper.toDetailsDTO(postDetails);
    }

    @PatchMapping("/{id}")
    public void postModify(@PathVariable("id") Long id, @Valid @RequestBody PostDTO.Details postDetailsDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();

        if (!Objects.equals(id, postDetailsDTO.getId())) {
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }

        postService.modifyPost(postMapper.toEntity(postDetailsDTO), securityMember.getId());
    }

    @DeleteMapping("/{id}")
    public void postDelete(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();

        postService.deletePost(id, securityMember.getId());
    }

    @PatchMapping("/{id}/favorites")
    public void favoritePostModify(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();

        postService.modifyFavoritePost(id, securityMember.getId());
    }


}
