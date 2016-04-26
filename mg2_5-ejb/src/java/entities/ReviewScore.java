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
 * @author ENTRAR
 */
@Entity
@Table(name = "REVIEW_SCORE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReviewScore.findAll", query = "SELECT r FROM ReviewScore r"),
    @NamedQuery(name = "ReviewScore.findByScoreId", query = "SELECT r FROM ReviewScore r WHERE r.scoreId = :scoreId"),
    @NamedQuery(name = "ReviewScore.findByReviewId", query = "SELECT r FROM ReviewScore r WHERE r.reviewId = :reviewId"),
    @NamedQuery(name = "ReviewScore.findByUserId", query = "SELECT r FROM ReviewScore r WHERE r.userId = :userId"),
    @NamedQuery(name = "ReviewScore.findByScore", query = "SELECT r FROM ReviewScore r WHERE r.score = :score")})
public class ReviewScore implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SCORE_ID")
    private Integer scoreId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REVIEW_ID")
    private int reviewId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SCORE")
    private int score;

    public ReviewScore() {
    }

    public ReviewScore(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public ReviewScore(Integer scoreId, int reviewId, int userId, int score) {
        this.scoreId = scoreId;
        this.reviewId = reviewId;
        this.userId = userId;
        this.score = score;
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scoreId != null ? scoreId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewScore)) {
            return false;
        }
        ReviewScore other = (ReviewScore) object;
        if ((this.scoreId == null && other.scoreId != null) || (this.scoreId != null && !this.scoreId.equals(other.scoreId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.ReviewScore[ scoreId=" + scoreId + " ]";
    }
    
}
