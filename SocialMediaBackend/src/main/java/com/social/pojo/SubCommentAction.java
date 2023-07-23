/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "sub_comment_action")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubCommentAction.findAll", query = "SELECT s FROM SubCommentAction s"),
    @NamedQuery(name = "SubCommentAction.findById", query = "SELECT s FROM SubCommentAction s WHERE s.id = :id")})
public class SubCommentAction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "action_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Action action;
    @JoinColumn(name = "sub_comment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SubComment subCommentId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    public SubCommentAction() {
    }

    public SubCommentAction(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public SubComment getSubCommentId() {
        return subCommentId;
    }

    public void setSubCommentId(SubComment subCommentId) {
        this.subCommentId = subCommentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof SubCommentAction)) {
            return false;
        }
        SubCommentAction other = (SubCommentAction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.social.pojo.SubCommentAction[ id=" + id + " ]";
    }
    
}
