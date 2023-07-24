/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "user_settings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSettings.findAll", query = "SELECT u FROM UserSettings u"),
    @NamedQuery(name = "UserSettings.findById", query = "SELECT u FROM UserSettings u WHERE u.id = :id"),
    @NamedQuery(name = "UserSettings.findByTheme", query = "SELECT u FROM UserSettings u WHERE u.theme = :theme"),
    @NamedQuery(name = "UserSettings.findByLanguage", query = "SELECT u FROM UserSettings u WHERE u.language = :language"),
    @NamedQuery(name = "UserSettings.findByLockProfile", query = "SELECT u FROM UserSettings u WHERE u.lockProfile = :lockProfile")})
public class UserSettings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "theme")
    private String theme;
    @Size(max = 255)
    @Column(name = "language")
    private String language;
    @Column(name = "lock_profile")
    private Boolean lockProfile;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    public UserSettings() {
    }

    public UserSettings(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getLockProfile() {
        return lockProfile;
    }

    public void setLockProfile(Boolean lockProfile) {
        this.lockProfile = lockProfile;
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
        if (!(object instanceof UserSettings)) {
            return false;
        }
        UserSettings other = (UserSettings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.social.pojo.UserSettings[ id=" + id + " ]";
    }
    
}
