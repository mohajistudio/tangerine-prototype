package io.mohajistudio.tangerine.prototype.entity;

import io.mohajistudio.tangerine.prototype.enums.Provider;
import io.mohajistudio.tangerine.prototype.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {
    private String refreshToken;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
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

    public static Member createMember(Provider provider, String email) {
        return Member.builder().provider(provider).role(Role.MEMBER).email(email).build();
    }

    public static Member createGuest(Provider provider, String email) {
        return Member.builder().provider(provider).role(Role.GUEST).email(email).build();
    }
}
