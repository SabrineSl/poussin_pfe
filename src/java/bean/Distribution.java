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
public class Distribution implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDistribution;
    private BigDecimal qte;
    @ManyToOne
    private Ferme ferme;
    @ManyToOne
    private Reception reception;
    @OneToOne
    private Utilisateur responsable;

    public Distribution(Long id) {
        this.id = id;
    }

    public Distribution() {
    }

    public Distribution(Long id, Date dateDistribution, BigDecimal qtedistribue) {
        this.id = id;
        this.dateDistribution = dateDistribution;
        this.qte = qtedistribue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDistribution() {
        return dateDistribution;
    }

    public void setDateDistribution(Date dateDistribution) {
        this.dateDistribution = dateDistribution;
    }

    public BigDecimal getQte() {
        return qte;
    }

    public void setQte(BigDecimal qte) {
        this.qte = qte;
    }

    public Ferme getFerme() {
        if (ferme == null) {
            ferme = new Ferme();
        }
        return ferme;
    }

    public void setFerme(Ferme ferme) {
        this.ferme = ferme;
    }

    public Reception getReception() {
        if (reception == null) {
            reception = new Reception();
        }
        return reception;
    }

    public void setReception(Reception reception) {
        this.reception = reception;
    }

    public Utilisateur getResponsable() {
        if (responsable == null) {
            responsable = new Utilisateur();
        }
        return responsable;
    }

    public void setResponsable(Utilisateur responsable) {
        this.responsable = responsable;
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
        if (!(object instanceof Distribution)) {
            return false;
        }
        Distribution other = (Distribution) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Distribution{" + "id=" + id + ", dateDistribution=" + dateDistribution + ", qtedistribue=" + qte + '}';
    }

}
