/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.social.enums.UserStatus;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @NotNull
    @Column(name = "alumni_id")
    private String alumniId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;
    @Size(max = 255)
    @Column(name = "slug")
    private String slug;
    @Column(name = "created_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdDate;
    @Column(name = "modified_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime modifiedDate;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
//    private Set<SurveyResult> surveyResultSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "founderId")
    private Set<Community> communitySet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Post> posts;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<SubComment> subComments;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Comment> comments;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<PostAction> postActions;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<CommentAction> commentActions;
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Role role;

    {
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
        this.slug = null;
    }

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String alumni, String password) {
        this.id = id;
        this.alumniId = alumni;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (avatar != null && !avatar.isEmpty()) {
            this.avatar = avatar;
        } else {
            this.avatar = null;
        }
    }

    public String getCoverBg() {
        return coverBg;
    }

    public void setCoverBg(String coverBg) {
        if (!coverBg.isEmpty()) {
            this.coverBg = coverBg;
        } else {
            this.coverBg = null;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        if (slug != null && !slug.isEmpty()) {
            this.slug = slug;
        } else {
            this.slug = null;
        }
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

//    @XmlTransient
//    public Set<SurveyResult> getSurveyResultSet() {
//        return surveyResultSet;
//    }
//
//    public void setSurveyResultSet(Set<SurveyResult> surveyResultSet) {
//        this.surveyResultSet = surveyResultSet;
//    }
    @XmlTransient
    public Set<Community> getCommunitySet() {
        return communitySet;
    }

    public void setCommunitySet(Set<Community> communitySet) {
        this.communitySet = communitySet;
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
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
