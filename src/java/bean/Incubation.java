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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ushiho
 */
@Entity
public class Incubation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateIncubation;
    private int numeroSemaine;
    private int annee;
    private BigDecimal qteIncube;
    @ManyToOne
    private TrieOeuf trieOeuf; 
    @ManyToOne
    private Couvoir couvoir;
    @OneToOne(mappedBy = "incubation")
    private Eclosion eclosion;

    public Incubation() {
    }

    public Incubation(Long id) {
        this.id = id;
    }

    public Incubation(Long id, Date dateIncubation, int numeroSemaine, int annee, BigDecimal qteIncube) {
        this.id = id;
        this.dateIncubation = dateIncubation;
        this.numeroSemaine = numeroSemaine;
        this.annee = annee;
        this.qteIncube = qteIncube;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateIncubation() {
        return dateIncubation;
    }

    public void setDateIncubation(Date dateIncubation) {
        this.dateIncubation = dateIncubation;
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

    public BigDecimal getQteIncube() {
        return qteIncube;
    }

    public void setQteIncube(BigDecimal qteIncube) {
        this.qteIncube = qteIncube;
    }

    public TrieOeuf getTrieOeuf() {
        if (trieOeuf == null) {
            trieOeuf = new TrieOeuf();
        }
        return trieOeuf;
    }

    public void setTrieOeuf(TrieOeuf trieOeuf) {
        this.trieOeuf = trieOeuf;
    }

    public Couvoir getCouvoir() {
        if (couvoir == null) {
            couvoir = new Couvoir();
        }
        return couvoir;
    }

    public void setCouvoir(Couvoir couvoir) {
        this.couvoir = couvoir;
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
        if (!(object instanceof Incubation)) {
            return false;
        }
        Incubation other = (Incubation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Incubation{" + "id=" + id + ", dateIncubation=" + dateIncubation + ", numeroSemaine=" + numeroSemaine + ", annee=" + annee + ", qteIncube=" + qteIncube + '}';
    }

}
