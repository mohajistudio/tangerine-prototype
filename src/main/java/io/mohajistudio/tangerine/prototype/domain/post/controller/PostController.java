package io.mohajistudio.tangerine.prototype.domain.post.controller;

import io.mohajistudio.tangerine.prototype.domain.post.domain.Post;
import io.mohajistudio.tangerine.prototype.domain.post.dto.PostDTO;
import io.mohajistudio.tangerine.prototype.domain.post.service.PostService;
import io.mohajistudio.tangerine.prototype.global.auth.domain.SecurityMember;
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

    @PostMapping
    public Post postAdd(@RequestBody Post post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();
        return postService.addPost(post, securityMember.getId());
    }

    @GetMapping
    public Page<PostDTO> postListWithPagination(@PageableDefault Pageable pageable) {
        return postService.findPostListWithPagination(pageable);
    }
}
