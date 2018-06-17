/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ushiho
 */
@Entity
public class PaiementDemandeOeufs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private DemandeOeufs demandeOeufs;
    @ManyToOne
    private Paiement paiement;

    public DemandeOeufs getDemandeOeufs() {
        if (demandeOeufs == null) {
            demandeOeufs = new DemandeOeufs();
        }
        return demandeOeufs;
    }

    public void setDemandeOeufs(DemandeOeufs demandeOeufs) {
        this.demandeOeufs = demandeOeufs;
    }

    public Paiement getPaiement() {
        if (paiement == null) {
            paiement = new Paiement();
        }
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaiementDemandeOeufs(Long id) {
        this.id = id;
    }

    public PaiementDemandeOeufs() {
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
        if (!(object instanceof PaiementDemandeOeufs)) {
            return false;
        }
        PaiementDemandeOeufs other = (PaiementDemandeOeufs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.PaiementDemande[ id=" + id + " ]";
    }

}
