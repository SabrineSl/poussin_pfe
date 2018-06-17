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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ushiho
 */
@Entity
public class Eclosion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateEclosion;
    private int numeroSemaine;
    private int annee;
    private BigDecimal qteEclos;
    private BigDecimal ecartTrie;
    private BigDecimal ecartEclosion;
    private BigDecimal commercialise;
    @OneToOne
    private Incubation incubation;
    @OneToOne
    private ProductionPoussin productionPoussin;

    public Eclosion() {
    }

    public Eclosion(Long id) {
        this.id = id;
    }

    public Eclosion(Long id, Date dateEclosion, int numeroSemaine, int annee, BigDecimal qteEclos, BigDecimal ecartTrie, BigDecimal ecartEclosion, BigDecimal commercialise) {
        this.id = id;
        this.dateEclosion = dateEclosion;
        this.numeroSemaine = numeroSemaine;
        this.annee = annee;
        this.qteEclos = qteEclos;
        this.ecartTrie = ecartTrie;
        this.ecartEclosion = ecartEclosion;
        this.commercialise = commercialise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateEclosion() {
        return dateEclosion;
    }

    public void setDateEclosion(Date dateEclosion) {
        this.dateEclosion = dateEclosion;
    }

    public int getNumeroSemaine() {
        return numeroSemaine;
    }

    public void setNumeroSemaine(int numeroSemaine) {
        this.numeroSemaine = numeroSemaine;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public BigDecimal getQteEclos() {
        return qteEclos;
    }

    public void setQteEclos(BigDecimal qteEclos) {
        this.qteEclos = qteEclos;
    }

    public BigDecimal getEcartTrie() {
        return ecartTrie;
    }

    public void setEcartTrie(BigDecimal ecartTrie) {
        this.ecartTrie = ecartTrie;
    }

    public BigDecimal getEcartEclosion() {
        return ecartEclosion;
    }

    public void setEcartEclosion(BigDecimal ecartEclosion) {
        this.ecartEclosion = ecartEclosion;
    }

    public BigDecimal getCommercialise() {
        return commercialise;
    }

    public void setCommercialise(BigDecimal commercialise) {
        this.commercialise = commercialise;
    }

    public Incubation getIncubation() {
        if (incubation == null) {
            incubation = new Incubation();
        }
        return incubation;
    }

    public void setIncubation(Incubation incubation) {
        this.incubation = incubation;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eclosion)) {
            return false;
        }
        Eclosion other = (Eclosion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Eclosion{" + "id=" + id + ", dateEclosion=" + dateEclosion + ", numeroSemaine=" + numeroSemaine + ", annee=" + annee + ", qteEclos=" + qteEclos + ", ecartTrie=" + ecartTrie + ", ecartEclosion=" + ecartEclosion + ", commercialise=" + commercialise + '}';
    }

}
