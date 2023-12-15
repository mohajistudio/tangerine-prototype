package io.mohajistudio.tangerine.prototype.domain.post.controller;

import io.mohajistudio.tangerine.prototype.domain.post.domain.Post;
import io.mohajistudio.tangerine.prototype.domain.post.dto.PostDTO;
import io.mohajistudio.tangerine.prototype.domain.post.mapper.PostMapper;
import io.mohajistudio.tangerine.prototype.domain.post.service.PostService;
import io.mohajistudio.tangerine.prototype.global.auth.domain.SecurityMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;

    @PostMapping
    public PostDTO.Details postAdd(@Valid @RequestBody PostDTO.Add postAddDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();

        Post post = postMapper.toEntity(postAddDTO);

        Post addedPost = postService.addPost(post, securityMember.getId());

        return postMapper.toDetailsDTO(addedPost);
    }

    @GetMapping
    public Page<PostDTO.Compact> postListWithPagination(@PageableDefault Pageable pageable) {
        Page<Post> postListWithPagination = postService.findPostListWithPagination(pageable);
        return postListWithPagination.map(postMapper::toCompactDTO);
    }

    @PatchMapping("/{id}/favorites")
    public void favoritePostModify(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();

        postService.modifyFavoritePost(id, securityMember.getId());

    }
}
