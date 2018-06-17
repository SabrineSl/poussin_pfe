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
 * @author lenovo
 */
@Entity
public class LivraisonOeufs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateLivraison;
    @ManyToOne
    private DemandeOeufs demandeOeufs;
    private BigDecimal total;
    private BigDecimal qte;
    @ManyToOne
    private Utilisateur responsable;
    @ManyToOne
    private Client client;

    public LivraisonOeufs(Long id) {
        this.id = id;
    }

    public LivraisonOeufs() {
    }

    public LivraisonOeufs(Long id, Date dateLivraison, BigDecimal total) {
        this.id = id;
        this.dateLivraison = dateLivraison;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public DemandeOeufs getDemandeOeufs() {
        if (demandeOeufs == null) {
            demandeOeufs = new DemandeOeufs();
        }
        return demandeOeufs;
    }

    public void setDemandeOeufs(DemandeOeufs demandeOeufs) {
        this.demandeOeufs = demandeOeufs;
    }

    public BigDecimal getQte() {
        return qte;
    }

    public void setQte(BigDecimal qte) {
        this.qte = qte;
    }

    public Client getClient() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
        if (!(object instanceof LivraisonOeufs)) {
            return false;
        }
        LivraisonOeufs other = (LivraisonOeufs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", dateLivraison=" + dateLivraison + ", total=" + total + '}';
    }

}
