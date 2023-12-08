package io.mohajistudio.tangerine.prototype.domain.post.domain;

import io.mohajistudio.tangerine.prototype.domain.member.domain.Member;
import io.mohajistudio.tangerine.prototype.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate visitedAt;

    @Column(nullable = false)
    private int commentCnt = 0;

    @Column(nullable = false)
    private int favoriteCnt = 0;

    private short blockCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToOne(mappedBy = "post")
    private TrendingPost trendingPost;

    @OneToMany(mappedBy = "post")
    private List<ScrapPost> scrapPosts;

    @OneToMany(mappedBy = "post")
    private List<FavoritePost> favoritePosts;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    private List<TextBlock> textBlocks;

    @OneToMany(mappedBy = "post")
    private List<PlaceBlock> placeBlocks;

    public static Post createPost(String title, LocalDate visitedAt) {
        //if(blockCnt == 0) throw Exception();
        return Post.builder().title(title).visitedAt(visitedAt).build();
    }
}
