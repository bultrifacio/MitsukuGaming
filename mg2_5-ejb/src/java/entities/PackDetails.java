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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alumno
 */
@Entity
@Table(name = "PACK_DETAILS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PackDetails.findAll", query = "SELECT p FROM PackDetails p"),
    @NamedQuery(name = "PackDetails.findByPackId", query = "SELECT p FROM PackDetails p WHERE p.packId = :packId"),
    @NamedQuery(name = "PackDetails.findByDiscount", query = "SELECT p FROM PackDetails p WHERE p.discount = :discount")})
public class PackDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PACK_ID")
    private Integer packId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 32700)
    @Column(name = "NAME")
    private String name;
    @Lob
    @Size(max = 32700)
    @Column(name = "DESCRIPTION")
    private String description;
    @Lob
    @Size(max = 32700)
    @Column(name = "LOGO")
    private String logo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DISCOUNT")
    private int discount;

    public PackDetails() {
    }

    public PackDetails(Integer packId) {
        this.packId = packId;
    }

    public PackDetails(Integer packId, String name, int discount) {
        this.packId = packId;
        this.name = name;
        this.discount = discount;
    }

    public Integer getPackId() {
        return packId;
    }

    public void setPackId(Integer packId) {
        this.packId = packId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (packId != null ? packId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PackDetails)) {
            return false;
        }
        PackDetails other = (PackDetails) object;
        if ((this.packId == null && other.packId != null) || (this.packId != null && !this.packId.equals(other.packId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PackDetails[ packId=" + packId + " ]";
    }
    
}
