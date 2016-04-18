/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alumno
 */
@Entity
@Table(name = "FOLLOWER_LIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FollowerList.findAll", query = "SELECT f FROM FollowerList f"),
    @NamedQuery(name = "FollowerList.findByFollowerId", query = "SELECT f FROM FollowerList f WHERE f.followerId = :followerId"),
    @NamedQuery(name = "FollowerList.findByUserId", query = "SELECT f FROM FollowerList f WHERE f.userId = :userId"),
    @NamedQuery(name = "FollowerList.findByFollower", query = "SELECT f FROM FollowerList f WHERE f.follower = :follower")})
public class FollowerList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FOLLOWER_ID")
    private Integer followerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FOLLOWER")
    private int follower;

    public FollowerList() {
    }

    public FollowerList(Integer followerId) {
        this.followerId = followerId;
    }

    public FollowerList(Integer followerId, int userId, int follower) {
        this.followerId = followerId;
        this.userId = userId;
        this.follower = follower;
    }

    public Integer getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (followerId != null ? followerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FollowerList)) {
            return false;
        }
        FollowerList other = (FollowerList) object;
        if ((this.followerId == null && other.followerId != null) || (this.followerId != null && !this.followerId.equals(other.followerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.FollowerList[ followerId=" + followerId + " ]";
    }
    
}
