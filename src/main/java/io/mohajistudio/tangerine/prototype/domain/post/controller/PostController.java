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
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Operation(summary = "페이징 된 게시글 목록", description = "page와 size 값을 넘기면 페이징 된 게시글 목록을 반환합니다. 기본 값은 page는 1, size는 10 입니다.")
    public Page<PostDTO.Compact> postListByPage(@Parameter(name = "PageableParam") @ModelAttribute PageableParam pageableParam) {
        Pageable pageable = PageRequest.of(pageableParam.getPage() - 1, pageableParam.getSize());
        Page<Post> postListWithPagination = postService.findPostListByPage(pageable);
        return postListWithPagination.map(postMapper::toCompactDTO);
    }

    @PostMapping
    @Operation(summary = "게시글 추가", description = "게시글 형식에 맞게 데이터를 전달해주세요.")
    public void postAdd(@Valid @RequestBody PostDTO.Add postAddDTO) {
        if(postAddDTO.getVisitStartDate().isAfter(postAddDTO.getVisitEndDate())) {
            throw new BusinessException(ErrorCode.INVALID_DATE_RANGE);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();

        Post post = postMapper.toEntity(postAddDTO);

        postService.addPost(post, securityMember.getId());
    }

    @GetMapping("/{id}")
    @Operation(summary = "게시글 상세 조회", description = "게시글 상세를 조회합니다.")
    public PostDTO.Details postDetails(@PathVariable("id") Long id) {
        Post postDetails = postService.findPostDetails(id);
        return postMapper.toDetailsDTO(postDetails);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "게시글 수정", description = "게시글 형식에 맞게 데이터를 전달해주세요.")
    public void postModify(@PathVariable("id") Long id, @Valid @RequestBody PostDTO.Details postDetailsDTO) {
        if(postDetailsDTO.getVisitStartDate().isAfter(postDetailsDTO.getVisitEndDate())) {
            throw new BusinessException(ErrorCode.INVALID_DATE_RANGE);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();

        if (!Objects.equals(id, postDetailsDTO.getId())) {
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }

        postService.modifyPost(postMapper.toEntity(postDetailsDTO), securityMember.getId());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    public void postDelete(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();

        postService.deletePost(id, securityMember.getId());
    }

    @PatchMapping("/{id}/favorites")
    @Operation(summary = "좋아하는 게시글 추가/삭제", description = "좋아하는 게시글을 추가 또는 삭제합니다.")
    public void favoritePostModify(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();

        postService.modifyFavoritePost(id, securityMember.getId());
    }

    @PatchMapping("/{id}/scrap")
    public void scrapPostModify(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();

        postService.modifyScrapPost(id, securityMember.getId());
    }
}
