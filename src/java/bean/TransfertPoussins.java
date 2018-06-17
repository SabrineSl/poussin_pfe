/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ushiho
 */
@Entity
public class TransfertPoussins implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal qteFemale;
    private BigDecimal qteMale;
    private BigDecimal qteTotal;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateTransfere;
    @ManyToOne
    private Ferme fermeSource;
    @ManyToOne
    private Ferme fermeDestination;
    @ManyToOne
    private ProductionPoussin productionPoussin;

    public BigDecimal getQteTotal() {
        return qteTotal;
    }

    public void setQteTotal(BigDecimal qteTotal) {
        this.qteTotal = qteTotal;
    }

    public ProductionPoussin getProductionPoussin() {
        if (productionPoussin == null) {
            productionPoussin = new ProductionPoussin();
        }
        return productionPoussin;
    }

    public void setProductionPoussin(ProductionPoussin productionPoussin) {
        this.productionPoussin = productionPoussin;
    }

    public TransfertPoussins() {
    }

    public TransfertPoussins(Long id) {
        this.id = id;
    }

    public TransfertPoussins(Long id, BigDecimal qteFemaleTransfere, BigDecimal qteMaleTransfere, Date dateTransfere) {
        this.id = id;
        this.qteFemale = qteFemaleTransfere;
        this.qteMale = qteMaleTransfere;
        this.dateTransfere = dateTransfere;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQteFemale() {
        return qteFemale;
    }

    public void setQteFemale(BigDecimal qteFemale) {
        this.qteFemale = qteFemale;
    }

    public BigDecimal getQteMale() {
        return qteMale;
    }

    public void setQteMale(BigDecimal qteMale) {
        this.qteMale = qteMale;
    }

    public Date getDateTransfere() {
        return dateTransfere;
    }

    public void setDateTransfere(Date dateTransfere) {
        this.dateTransfere = dateTransfere;
    }

    public Ferme getFermeSource() {
        if (fermeSource == null) {
            fermeSource = new Ferme();
        }
        return fermeSource;
    }

    public void setFermeSource(Ferme fermeSource) {
        this.fermeSource = fermeSource;
    }

    public Ferme getFermeDestination() {
        if (fermeDestination == null) {
            fermeDestination = new Ferme();
        }
        return fermeDestination;
    }

    public void setFermeDestination(Ferme fermeDestination) {
        this.fermeDestination = fermeDestination;
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
        if (!(object instanceof TransfertPoussins)) {
            return false;
        }
        TransfertPoussins other = (TransfertPoussins) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransfertPoussins{" + "id=" + id + ", qteFemaleTransfere=" + qteFemale + ", qteMaleTransfere=" + qteMale + ", dateTransfere=" + dateTransfere + '}';
    }

}
