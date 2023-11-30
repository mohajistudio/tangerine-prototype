package io.mohajistudio.tangerine.prototype.entity;

import io.mohajistudio.tangerine.prototype.enums.Provider;
import io.mohajistudio.tangerine.prototype.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {
    private String refreshToken;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @OneToOne(mappedBy = "member")
    private MemberProfile memberProfile;

    @OneToMany(mappedBy = "member")
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<FavoriteComment> favoriteComments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Follow> follows;

    @OneToMany(mappedBy = "followMember")
    private List<Follow> followMembers;

    @OneToMany(mappedBy = "member")
    private List<ScrapPost> scrapPosts;

    @OneToMany(mappedBy = "member")
    private List<FavoritePost> favoritePosts;

    public static Member createMember(Provider provider) {
        return Member.builder().provider(provider).role(Role.MEMBER).build();
    }
}
