/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.pojo;

import java.io.Serializable;
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
@Table(name = "sub_comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubComment.findAll", query = "SELECT s FROM SubComment s"),
    @NamedQuery(name = "SubComment.findById", query = "SELECT s FROM SubComment s WHERE s.id = :id"),
    @NamedQuery(name = "SubComment.findByContent", query = "SELECT s FROM SubComment s WHERE s.content = :content"),
    @NamedQuery(name = "SubComment.findByCreatedDate", query = "SELECT s FROM SubComment s WHERE s.createdDate = :createdDate"),
    @NamedQuery(name = "SubComment.findByModifiedDate", query = "SELECT s FROM SubComment s WHERE s.modifiedDate = :modifiedDate")})
public class SubComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "content")
    private String content;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    @ManyToOne
    private Comment commentId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User users;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subCommentId")
    private Set<SubCommentAction> subCommentActionSet;

    public SubComment() {
    }

    public SubComment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Comment getCommentId() {
        return commentId;
    }

    public void setCommentId(Comment commentId) {
        this.commentId = commentId;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @XmlTransient
    public Set<SubCommentAction> getSubCommentActionSet() {
        return subCommentActionSet;
    }

    public void setSubCommentActionSet(Set<SubCommentAction> subCommentActionSet) {
        this.subCommentActionSet = subCommentActionSet;
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
        if (!(object instanceof SubComment)) {
            return false;
        }
        SubComment other = (SubComment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.social.pojo.SubComment[ id=" + id + " ]";
    }
    
}
