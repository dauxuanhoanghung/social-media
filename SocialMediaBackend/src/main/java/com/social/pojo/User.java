/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByAlumniId", query = "SELECT u FROM User u WHERE u.alumniId = :alumniId"),
    @NamedQuery(name = "User.findByDisplayName", query = "SELECT u FROM User u WHERE u.displayName = :displayName"),
    @NamedQuery(name = "User.findByAvatar", query = "SELECT u FROM User u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "User.findByCoverBg", query = "SELECT u FROM User u WHERE u.coverBg = :coverBg"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByStatus", query = "SELECT u FROM User u WHERE u.status = :status"),
    @NamedQuery(name = "User.findBySlug", query = "SELECT u FROM User u WHERE u.slug = :slug"),
    @NamedQuery(name = "User.findByCreatedDate", query = "SELECT u FROM User u WHERE u.createdDate = :createdDate"),
    @NamedQuery(name = "User.findByModifiedDate", query = "SELECT u FROM User u WHERE u.modifiedDate = :modifiedDate")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 255)
    @Column(name = "alumni_id")
    private String alumniId;
    @Size(max = 255)
    @Column(name = "display_name")
    private String displayName;
    @Size(max = 255)
    @Column(name = "avatar")
    private String avatar;
    @Size(max = 255)
    @Column(name = "cover_bg")
    private String coverBg;
    //@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "status")
    private String status;
    @Size(max = 255)
    @Column(name = "user_type")
    private String userType;
    @Size(max = 255)
    @Column(name = "slug")
    private String slug;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user1Id")
    private Set<Friendship> friendshipSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user2Id")
    private Set<Friendship> friendshipSet1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<SurveyResult> surveyResultSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "founderId")
    private Set<Community> communitySet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserSettings> userSettings;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Post> posts;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<SubComment> subComments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<CommentAction> commentActions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Comment> comments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<SubCommentAction> subCommentActions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userReportId")
    private Set<PostReport> postReportSet;
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Role role;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<PostAction> postActions;

    {
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlumniId() {
        return alumniId;
    }

    public void setAlumniId(String alumniId) {
        this.alumniId = alumniId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCoverBg() {
        return coverBg;
    }

    public void setCoverBg(String coverBg) {
        this.coverBg = coverBg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @XmlTransient
    public Set<Friendship> getFriendshipSet() {
        return friendshipSet;
    }

    public void setFriendshipSet(Set<Friendship> friendshipSet) {
        this.friendshipSet = friendshipSet;
    }

    @XmlTransient
    public Set<Friendship> getFriendshipSet1() {
        return friendshipSet1;
    }

    public void setFriendshipSet1(Set<Friendship> friendshipSet1) {
        this.friendshipSet1 = friendshipSet1;
    }

    @XmlTransient
    public Set<SurveyResult> getSurveyResultSet() {
        return surveyResultSet;
    }

    public void setSurveyResultSet(Set<SurveyResult> surveyResultSet) {
        this.surveyResultSet = surveyResultSet;
    }

    @XmlTransient
    public Set<Community> getCommunitySet() {
        return communitySet;
    }

    public void setCommunitySet(Set<Community> communitySet) {
        this.communitySet = communitySet;
    }

    @XmlTransient
    public Set<UserSettings> getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(Set<UserSettings> userSettings) {
        this.userSettings = userSettings;
    }

    @XmlTransient
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @XmlTransient
    public Set<SubComment> getSubComments() {
        return subComments;
    }

    public void setSubComments(Set<SubComment> subComments) {
        this.subComments = subComments;
    }

    @XmlTransient
    public Set<CommentAction> getCommentActions() {
        return commentActions;
    }

    public void setCommentActions(Set<CommentAction> commentActions) {
        this.commentActions = commentActions;
    }

    @XmlTransient
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @XmlTransient
    public Set<SubCommentAction> getSubCommentActions() {
        return subCommentActions;
    }

    public void setSubCommentActions(Set<SubCommentAction> subCommentActions) {
        this.subCommentActions = subCommentActions;
    }

    @XmlTransient
    public Set<PostReport> getPostReportSet() {
        return postReportSet;
    }

    public void setPostReportSet(Set<PostReport> postReportSet) {
        this.postReportSet = postReportSet;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @XmlTransient
    public Set<PostAction> getPostActions() {
        return postActions;
    }

    public void setPostActions(Set<PostAction> postActions) {
        this.postActions = postActions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.social.pojo.User[ id=" + id + " ]";
    }

}
