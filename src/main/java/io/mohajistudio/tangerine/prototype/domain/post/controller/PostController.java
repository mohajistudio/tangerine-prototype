package io.mohajistudio.tangerine.prototype.domain.post.controller;

import io.mohajistudio.tangerine.prototype.domain.post.domain.Post;
import io.mohajistudio.tangerine.prototype.domain.post.dto.PostDTO;
import io.mohajistudio.tangerine.prototype.domain.post.mapper.PostMapper;
import io.mohajistudio.tangerine.prototype.domain.post.service.PostService;
import io.mohajistudio.tangerine.prototype.global.auth.domain.SecurityMember;
import io.mohajistudio.tangerine.prototype.global.enums.ErrorCode;
import io.mohajistudio.tangerine.prototype.global.error.exception.BusinessException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping
    public Page<PostDTO.Compact> postListByPage(@PageableDefault Pageable pageable) {
        Page<Post> postListWithPagination = postService.findPostListByPage(pageable);
        return postListWithPagination.map(postMapper::toCompactDTO);
    }

    @PostMapping
    public PostDTO.Details postAdd(@Valid @RequestBody PostDTO.Add postAddDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();

        Post post = postMapper.toEntity(postAddDTO);

        Post addedPost = postService.addPost(post, securityMember.getId());

        return postMapper.toDetailsDTO(addedPost);
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

        if(!Objects.equals(id, postDetailsDTO.getId())) {
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }

        postService.modifyPost(securityMember.getId(), postMapper.toEntity(postDetailsDTO));
    }

    @PatchMapping("/{id}/favorites")
    public void favoritePostModify(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();

        postService.modifyFavoritePost(id, securityMember.getId());
    }
}
