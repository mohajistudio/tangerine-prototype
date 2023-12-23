package io.mohajistudio.tangerine.prototype.domain.post.service;

import io.mohajistudio.tangerine.prototype.domain.member.domain.Member;
import io.mohajistudio.tangerine.prototype.domain.member.repository.MemberRepository;
import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
import io.mohajistudio.tangerine.prototype.domain.post.domain.*;
import io.mohajistudio.tangerine.prototype.domain.post.repository.*;
import io.mohajistudio.tangerine.prototype.global.enums.ImageMimeType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
@Transactional
class PostServiceTest {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;
    private final Post post;

    PostServiceTest() {
        this.post = createPost();
    }

    Post createPost() {
        Set<TextBlock> textBlocks = new HashSet<>();
        Set<PlaceBlock> placeBlocks = new HashSet<>();
        Set<PlaceBlockImage> placeBlockImages = new HashSet<>();

        Post post = Post.builder().title("테스트 타이틀").visitedAt(LocalDate.of(2023, 12, 25)).build();
        Category category = Category.builder().id(1L).build();
        Place place = Place.builder().id(1L).build();

        textBlocks.add(TextBlock.builder().content("첫 번째 블럭에 들어갈 내용입니다.").orderNumber((short) 1).build());
        textBlocks.add(TextBlock.builder().content("세 번째 블럭에 들어갈 내용입니다.").orderNumber((short) 3).build());

        placeBlockImages.add(PlaceBlockImage.builder().imageUrl("https://image-url-2-1.com").imageMimeType(ImageMimeType.JPEG).orderNumber((short) 1).build());
        placeBlockImages.add(PlaceBlockImage.builder().imageUrl("https://image-url-2-2.com").imageMimeType(ImageMimeType.PNG).orderNumber((short) 1).build());

        placeBlocks.add(PlaceBlock.builder().content("두 번째 블럭에 들어갈 내용입니다.").orderNumber((short) 2).category(category).place(place).placeBlockImages(placeBlockImages).build());

        post.setTextBlocks(textBlocks);
        post.setPlaceBlocks(placeBlocks);
        return post;
    }

    @Test
    void addPost() {
        Optional<Member> findMember = memberRepository.findById(1L);
        Assertions.assertTrue(findMember.isPresent());


        postService.addPost(post, findMember.get().getId());

        Optional<Post> findPost = postRepository.findByIdDetails(post.getId());

        Assertions.assertTrue(findPost.isPresent());
    }

    @Test
    void findPostListByPage() {
        int times = 4;
        Optional<Member> findMember = memberRepository.findById(1L);
        Assertions.assertTrue(findMember.isPresent());

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id").ascending());
        for(int i = 0; i < times; i ++) {
            postService.addPost(createPost(), findMember.get().getId());
        }

        Page<Post> postListByPage = postService.findPostListByPage(pageRequest);

        Assertions.assertEquals(postListByPage.stream().count(), times);
    }

    @Test
    void findPostDetails() {
    }

    @Test
    void modifyPost() {
    }

    @Test
    void modifyFavoritePost() {
    }

    @Test
    void deletePost() {
    }
}