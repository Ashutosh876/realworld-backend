package com.realworld.realworldbackend.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "follows", uniqueConstraints = {
        @UniqueConstraint(name = "u_follow_followee_pair_must_be_unique", columnNames = {"followee", "follower"})})
public class FollowEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "followee")
    private UserEntity followee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "follower")
    private UserEntity follower;

//    @Column(name = "Userid")
//    @JoinColumn(name = "userid", referencedColumnName = "id", table = "user_table")
//    private UUID userId;
//    @Column(name = "following")
//    @ElementCollection(targetClass=UUID.class)
//    private List<UUID> followingIds;
//    @Column(name = "followers")
//    @ElementCollection(targetClass=UUID.class)
//    private List<UUID> followerIds;
//
//    public UUID getUserId() {
//        return userId;
//    }
//
//    public void setUserId(UUID userId) {
//        this.userId = userId;
//    }
//
//    public List<UUID> getFollowingIds() {
//        return followingIds;
//    }
//
//    public void setFollowingIds(List<UUID> followingIds) {
//        this.followingIds = followingIds;
//    }
//
//    public List<UUID> getFollowerIds() {
//        return followerIds;
//    }
//
//    public void setFollowerIds(List<UUID> followerIds) {
//        this.followerIds = followerIds;
//    }

    //    @JoinColumn(nullable = false, unique = true)
//    @OneToOne
//    private User user;
//
//    @Column(name = "following")
//    @ElementCollection(targetClass=User.class)
//    private List<User> following; //list of users, a single user is following
//
//    @Column(name = "followers")
//    @ElementCollection(targetClass=User.class)
//    private List<User> followers; //list of users, a single user is being followed by
//
//    public Follows() {
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public List<User> getFollowing() {
//        return following;
//    }
//
//    public void setFollowing(List<User> following) {
//        this.following = following;
//    }
//
//    public List<User> getFollowers() {
//        return followers;
//    }
//
//    public void setFollowers(List<User> followers) {
//        this.followers = followers;
//    }

//    private User followee;
//    private User follower;
//
//    public Follows() {}
//
//    public Follows(User followee, User follower) {
//        this.followee = followee;
//        this.follower = follower;
//    }
//
//    @JoinColumn(name = "id")
//    @ManyToMany(fetch = FetchType.LAZY, targetEntity = User.class)
//    public User getFollowee() {
//        return followee;
//    }
//
//    public void setFollowee(User followee) {
//        this.followee = followee;
//    }
//
//    @JoinColumn(name = "id")
//    @ManyToMany(fetch = FetchType.LAZY, targetEntity = User.class)
//    public User getFollower() {
//        return follower;
//    }
//
//    public void setFollower(User follower) {
//        this.follower = follower;
//    }
}







