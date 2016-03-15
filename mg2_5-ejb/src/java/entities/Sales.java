/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alumno
 */
@Entity
@Table(name = "SALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sales.findAll", query = "SELECT s FROM Sales s"),
    @NamedQuery(name = "Sales.findBySaleId", query = "SELECT s FROM Sales s WHERE s.saleId = :saleId"),
    @NamedQuery(name = "Sales.findByProductId", query = "SELECT s FROM Sales s WHERE s.productId = :productId"),
    @NamedQuery(name = "Sales.findByUserId", query = "SELECT s FROM Sales s WHERE s.userId = :userId"),
    @NamedQuery(name = "Sales.findByDate", query = "SELECT s FROM Sales s WHERE s.date = :date")})
public class Sales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALE_ID")
    private Integer saleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUCT_ID")
    private int productId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;

    public Sales() {
    }

    public Sales(Integer saleId) {
        this.saleId = saleId;
    }

    public Sales(Integer saleId, int productId, int userId, Date date) {
        this.saleId = saleId;
        this.productId = productId;
        this.userId = userId;
        this.date = date;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (saleId != null ? saleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sales)) {
            return false;
        }
        Sales other = (Sales) object;
        if ((this.saleId == null && other.saleId != null) || (this.saleId != null && !this.saleId.equals(other.saleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sales[ saleId=" + saleId + " ]";
    }
    
}
