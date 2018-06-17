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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author lenovo
 */
@Entity
public class Reception implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateReception;
    @ManyToOne
    private Commande commande;
    private BigDecimal total;
    private BigDecimal qteMale;
    private BigDecimal qteFemale;
    @ManyToOne
    private Ferme ferme;
    @OneToOne
    private Utilisateur responsable;
    @OneToMany(mappedBy = "reception")
    private List<Distribution> distributions;
    @ManyToOne
    private Fournisseur fournisseur;

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

    public Reception(Long id) {
        this.id = id;
    }

    public Reception() {
    }

    public Reception(Long id, Date dateLivraison, BigDecimal total) {
        this.id = id;
        this.dateReception = dateLivraison;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateReception() {
        return dateReception;
    }

    public void setDateReception(Date dateReception) {
        this.dateReception = dateReception;
    }

    public Commande getCommande() {
        if (commande == null) {
            commande = new Commande();
        }
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<Distribution> getDistributions() {
        if (distributions == null) {
            distributions = new ArrayList();
        }
        return distributions;
    }

    public void setDistributions(List<Distribution> distributions) {
        this.distributions = distributions;
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
        if (!(object instanceof Reception)) {
            return false;
        }
        Reception other = (Reception) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", dateLivraison=" + dateReception + ", total=" + total + '}';
    }

}
