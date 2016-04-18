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
@Table(name = "PACK_CONTENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PackContent.findAll", query = "SELECT p FROM PackContent p"),
    @NamedQuery(name = "PackContent.findById", query = "SELECT p FROM PackContent p WHERE p.id = :id"),
    @NamedQuery(name = "PackContent.findByPackId", query = "SELECT p FROM PackContent p WHERE p.packId = :packId"),
    @NamedQuery(name = "PackContent.findByProductId", query = "SELECT p FROM PackContent p WHERE p.productId = :productId")})
public class PackContent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PACK_ID")
    private int packId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUCT_ID")
    private int productId;

    public PackContent() {
    }

    public PackContent(Integer id) {
        this.id = id;
    }

    public PackContent(Integer id, int packId, int productId) {
        this.id = id;
        this.packId = packId;
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPackId() {
        return packId;
    }

    public void setPackId(int packId) {
        this.packId = packId;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PackContent)) {
            return false;
        }
        PackContent other = (PackContent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PackContent[ id=" + id + " ]";
    }
    
}
