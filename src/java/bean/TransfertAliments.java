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
public class TransfertAliments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateTransfere;
    private BigDecimal qteTransferee;
    @ManyToOne
    private Ferme fermeSource;
    @ManyToOne
    private Ferme fermeDestination;
    @OneToOne
    private TypeAliment typeAliment;
  

    public TransfertAliments() {
    }

    public TransfertAliments(Long id) {
        this.id = id;
    }

    public TransfertAliments(Long id, Date dateTransfere, BigDecimal qteTransferee) {
        this.id = id;
        this.dateTransfere = dateTransfere;
        this.qteTransferee = qteTransferee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateTransfere() {
        return dateTransfere;
    }

    public void setDateTransfere(Date dateTransfere) {
        this.dateTransfere = dateTransfere;
    }

    public BigDecimal getQteTransferee() {
        return qteTransferee;
    }

    public void setQteTransferee(BigDecimal qteTransferee) {
        this.qteTransferee = qteTransferee;
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

    public TypeAliment getTypeAliment() {
        if (typeAliment == null) {
            typeAliment = new TypeAliment();
        }
        return typeAliment;
    }

    public void setTypeAliment(TypeAliment typeAliment) {
        this.typeAliment = typeAliment;
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
        if (!(object instanceof TransfertAliments)) {
            return false;
        }
        TransfertAliments other = (TransfertAliments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransfertAliments{" + "id=" + id + ", dateTransfere=" + dateTransfere + ", qteTransferee=" + qteTransferee + '}';
    }

}
