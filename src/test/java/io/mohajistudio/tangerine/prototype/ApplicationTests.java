//package io.mohajistudio.tangerine.prototype;
//
//import io.mohajistudio.tangerine.prototype.domain.member.domain.Member;
//import io.mohajistudio.tangerine.prototype.domain.member.repository.MemberRepository;
//import io.mohajistudio.tangerine.prototype.domain.place.domain.Place;
//import io.mohajistudio.tangerine.prototype.domain.post.domain.*;
//import io.mohajistudio.tangerine.prototype.domain.post.repository.PostRepository;
//import io.mohajistudio.tangerine.prototype.global.enums.ImageMimeType;
//import jakarta.persistence.EntityManager;
//import org.junit.jupiter.api.Test;
//import org.locationtech.jts.geom.Point;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//class ApplicationTests {
//    @Autowired
//    PostRepository postRepository;
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    EntityManager entityManager;
//
//    @Test
//    void contextLoads() {
//        Optional<Member> findMember = memberRepository.findById(1L);
//
//        Post post = Post.builder().title("게시글 제목입니다.").visitedAt(LocalDate.of(2023, 12, 8)).build();
//        findMember.ifPresent(post::setMember);
//
//        List<TextBlock> textBlocks = new ArrayList<>();
//        textBlocks.add(TextBlock.builder().content("첫 번째 블럭에 들어갈 데이터입니다.").orderNumber((short) 1).build());
//
//        List<PlaceBlock> placeBlocks = new ArrayList<>();
//        PlaceBlock placeBlock = PlaceBlock.builder().content("두 번째 블럭에 들어갈 데이터입니다.").orderNumber((short) 2).rating((short) 10).build();
//        placeBlocks.add(placeBlock);
//
//        Place place = Place.builder().address("강동구 성내동 292-16").roadAddress("강동구 풍성로 119-7").name("라움하우스I").coordinates(new Point(37.532316, 127.125500)).description("창희가 사는 집입니다.").build();
//        placeBlock.setPlace(place);
//
//        Category category = Category.builder().name("숙소").build();
//        placeBlock.setCategory(category);
//
//        List<PlaceBlockImage> placeBlockImages = new ArrayList<>();
//        placeBlockImages.add(PlaceBlockImage.builder().imageUrl("http://imageUrl1.com").imageMimeType(ImageMimeType.JPEG).orderNumber((short) 1).build());
//        placeBlockImages.add(PlaceBlockImage.builder().imageUrl("http://imageUrl2.com").imageMimeType(ImageMimeType.JPEG).orderNumber((short) 2).build());
//        placeBlock.setPlaceBlockImages(placeBlockImages);
//
//        post.setPlaceBlocks(placeBlocks);
//        post.setTextBlocks(textBlocks);
//
//        postRepository.save(post);
//    }
//
//    @Test
//    void testGetPost() {
//        Optional<Post> findPost = postRepository.findById(13L);
//        System.out.println("findPost = " + findPost.toString());
//    }
//
//}
