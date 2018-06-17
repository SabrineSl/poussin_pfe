/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ushiho
 */
@Entity
public class ProductionPoussin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEntreePoussin;
    private int numSemaine;
    private int annee;
    private BigDecimal qteFemale;
    private BigDecimal qteMale;
    private BigDecimal qteTotal;
    @ManyToOne
    private Ferme ferme;
    @OneToMany(mappedBy = "productionPoussin")
    private List<TransfertPoussins> transfertPoussinss;
    @OneToOne(mappedBy = "productionPoussin")
    private Eclosion eclosion;

    public BigDecimal getQteTotal() {
        return qteTotal;
    }

    public void setQteTotal(BigDecimal qteTotal) {
        this.qteTotal = qteTotal;
    }

    public List<TransfertPoussins> getTransfertPoussinss() {
        return transfertPoussinss;
    }

    public void setTransfertPoussinss(List<TransfertPoussins> transfertPoussinss) {
        this.transfertPoussinss = transfertPoussinss;
    }

    public Ferme getFerme() {
        return ferme;
    }

    public void setFerme(Ferme ferme) {
        this.ferme = ferme;
    }

    public ProductionPoussin() {
    }

    public ProductionPoussin(Long id) {
        this.id = id;
    }

    public ProductionPoussin(Long id, Date dateProduction, int numSemaine, int annee, BigDecimal qteFemale, BigDecimal qteMale) {
        this.id = id;
        this.dateEntreePoussin = dateProduction;
        this.numSemaine = numSemaine;
        this.annee = annee;
        this.qteFemale = qteFemale;
        this.qteMale = qteMale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateEntreePoussin() {
        return dateEntreePoussin;
    }

    public void setDateEntreePoussin(Date dateEntreePoussin) {
        this.dateEntreePoussin = dateEntreePoussin;
    }

    public int getNumSemaine() {
        return numSemaine;
    }

    public void setNumSemaine(int numSemaine) {
        this.numSemaine = numSemaine;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
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

    public Eclosion getEclosion() {
        if (eclosion == null) {
            eclosion = new Eclosion();
        }
        return eclosion;
    }

    public void setEclosion(Eclosion eclosion) {
        this.eclosion = eclosion;
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
        if (!(object instanceof ProductionPoussin)) {
            return false;
        }
        ProductionPoussin other = (ProductionPoussin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductionPoussins{" + "id=" + id + ", dateProduction=" + dateEntreePoussin + ", numSemaine=" + numSemaine + ", annee=" + annee + ", qteFemale=" + qteFemale + ", qteMale=" + qteMale + '}';
    }

}
