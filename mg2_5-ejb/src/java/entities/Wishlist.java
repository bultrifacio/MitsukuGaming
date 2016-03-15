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
@Table(name = "WISHLIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wishlist.findAll", query = "SELECT w FROM Wishlist w"),
    @NamedQuery(name = "Wishlist.findByWishId", query = "SELECT w FROM Wishlist w WHERE w.wishId = :wishId"),
    @NamedQuery(name = "Wishlist.findByUserId", query = "SELECT w FROM Wishlist w WHERE w.userId = :userId"),
    @NamedQuery(name = "Wishlist.findByProductId", query = "SELECT w FROM Wishlist w WHERE w.productId = :productId")})
public class Wishlist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "WISH_ID")
    private Integer wishId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUCT_ID")
    private int productId;

    public Wishlist() {
    }

    public Wishlist(Integer wishId) {
        this.wishId = wishId;
    }

    public Wishlist(Integer wishId, int userId, int productId) {
        this.wishId = wishId;
        this.userId = userId;
        this.productId = productId;
    }

    public Integer getWishId() {
        return wishId;
    }

    public void setWishId(Integer wishId) {
        this.wishId = wishId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wishId != null ? wishId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wishlist)) {
            return false;
        }
        Wishlist other = (Wishlist) object;
        if ((this.wishId == null && other.wishId != null) || (this.wishId != null && !this.wishId.equals(other.wishId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Wishlist[ wishId=" + wishId + " ]";
    }
    
}
