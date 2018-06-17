/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author lenovo
 */
@Entity
public class LivraisonPoules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateLivraison;
    @ManyToOne
    private DemandePoules demandePoules;
    private BigDecimal total;
    private BigDecimal qteMale;
    private BigDecimal qteFemale;
    @ManyToOne
    private Utilisateur responsable;
    @ManyToOne
    private Client client;

    public LivraisonPoules(Long id, Date dateLivraison, BigDecimal total, BigDecimal qteMale, BigDecimal qteFemale) {
        this.id = id;
        this.dateLivraison = dateLivraison;
        this.total = total;
        this.qteMale = qteMale;
        this.qteFemale = qteFemale;
    }
    
    

    public BigDecimal getQteMale() {
        return qteMale;
    }

    public void setQteMale(BigDecimal qteMale) {
        this.qteMale = qteMale;
    }

    public BigDecimal getQteFemale() {
        return qteFemale;
    }

    public void setQteFemale(BigDecimal qteFemale) {
        this.qteFemale = qteFemale;
    }
    

    public LivraisonPoules(Long id) {
        this.id = id;
    }

    public LivraisonPoules() {
    }

    public LivraisonPoules(Long id, Date dateLivraison, DemandePoules demandePoules, BigDecimal total, BigDecimal qteMale, BigDecimal qteFemale) {
        this.id = id;
        this.dateLivraison = dateLivraison;
        this.demandePoules = demandePoules;
        this.total = total;
        this.qteMale = qteMale;
        this.qteFemale = qteFemale;
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

    public DemandePoules getDemandePoules() {
        return demandePoules;
    }

    public void setDemandePoules(DemandePoules demandePoules) {
        this.demandePoules = demandePoules;
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
        if (!(object instanceof LivraisonPoules)) {
            return false;
        }
        LivraisonPoules other = (LivraisonPoules) object;
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
