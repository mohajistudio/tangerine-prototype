package io.mohajistudio.tangerine.prototype.domain.post.domain;

import io.mohajistudio.tangerine.prototype.domain.member.domain.Member;
import io.mohajistudio.tangerine.prototype.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int groupNumber;

    @Column(nullable = false)
    private int favoriteCnt = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> childComments;

    @ManyToOne
    private Comment replyComment;

    @OneToMany(mappedBy = "replyComment")
    private List<Comment> repliedComments;

    @OneToMany(mappedBy = "comment")
    private List<FavoriteComment> favoriteComments;

    public static Comment createComment(String content, int groupNumber) {
        return Comment.builder().content(content).groupNumber(groupNumber).build();
    }
}
