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
 * @author ushiho
 */
@Entity
public class DemandeOeufs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal qte;
    private BigDecimal qteLivree;
    private int etatLivraison; // 1: livraie ; 2 :non livraie ; 3 : en cours de livraisons
    private BigDecimal montantTotal;
    private BigDecimal totalPaye;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDemande;
    private int etatPaiement;//1 : payé ; 2: non payé ; 3: en cours de paiement
    @ManyToOne
    private Utilisateur responsable;
    @ManyToOne
    private Client client;
    @OneToOne
    private EstimationOeuf estimationOeuf;
    @OneToMany(mappedBy = "demandeOeufs")
    private List<LivraisonOeufs> livraisonOeufss;
    @OneToMany(mappedBy = "demandeOeufs")
    private List<PaiementDemandeOeufs> paiementDemandeOeufss;

    public DemandeOeufs() {
    }

    public DemandeOeufs(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstimationOeuf getEstimationOeuf() {
        if (estimationOeuf == null) {
            estimationOeuf = new EstimationOeuf();
        }
        return estimationOeuf;
    }

    public void setEstimationOeuf(EstimationOeuf estimationOeuf) {
        this.estimationOeuf = estimationOeuf;
    }

    public DemandeOeufs(BigDecimal qte, BigDecimal qteLivree) {
        this.qte = qte;
        this.qteLivree = qteLivree;
    }

    public BigDecimal getQte() {
        return qte;
    }

    public void setQte(BigDecimal qte) {
        this.qte = qte;
    }

    public BigDecimal getQteLivree() {
        return qteLivree;
    }

    public void setQteLivree(BigDecimal qteLivree) {
        this.qteLivree = qteLivree;
    }

    public int getEtatLivraison() {
        return etatLivraison;
    }

    public void setEtatLivraison(int etatLivraison) {
        this.etatLivraison = etatLivraison;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public BigDecimal getTotalPaye() {
        return totalPaye;
    }

    public void setTotalPaye(BigDecimal totalPaye) {
        this.totalPaye = totalPaye;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public int getEtatPaiement() {
        return etatPaiement;
    }

    public void setEtatPaiement(int etatPaiement) {
        this.etatPaiement = etatPaiement;
    }

    public List<LivraisonOeufs> getLivraisonOeufss() {
        if (livraisonOeufss == null) {
            livraisonOeufss = new ArrayList();
        }
        return livraisonOeufss;
    }

    public void setLivraisonOeufss(List<LivraisonOeufs> livraisonOeufss) {
        this.livraisonOeufss = livraisonOeufss;
    }

    public List<PaiementDemandeOeufs> getPaiementDemandeOeufss() {
        if (paiementDemandeOeufss == null) {
            paiementDemandeOeufss = new ArrayList();
        }
        return paiementDemandeOeufss;
    }

    public void setPaiementDemandeOeufss(List<PaiementDemandeOeufs> paiementDemandeOeufss) {
        this.paiementDemandeOeufss = paiementDemandeOeufss;
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
        if (!(object instanceof DemandeOeufs)) {
            return false;
        }
        DemandeOeufs other = (DemandeOeufs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.DemandeOeufs[ id=" + id + " ]";
    }

}
