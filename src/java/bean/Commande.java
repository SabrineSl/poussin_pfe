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
import java.util.Objects;
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
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCommande;
    private int etatPaiement;//1 : payé ; 2: non payé ; 3: en cours de paiement
    private int etatReception; // 1: livraie ; 2 :non livraie ; 3 : en cours de livraisons
    private BigDecimal montantTotal;
    private BigDecimal totalPaye;
    private BigDecimal qteTotal; // qteMale+ qteFemale
    private BigDecimal qteMale;
    private BigDecimal qteMaleReception;
    private BigDecimal qteFemale;
    private BigDecimal qteFemaleReception;

    @ManyToOne
    private EstimationPoids estimationPoids;
    @OneToMany(mappedBy = "commande")
    private List<Reception> receptions;
    @ManyToOne
    private Fournisseur fournisseur;
    @ManyToOne
    private Ferme ferme;
    @OneToOne
    private Utilisateur responsable;
    @OneToMany(mappedBy = "commande")
    private List<PaiementCommande> paiementCommandes;

    public EstimationPoids getEstimationPoids() {
        if (estimationPoids == null) {
            estimationPoids = new EstimationPoids();
        }
        return estimationPoids;
    }

    public void setEstimationPoids(EstimationPoids estimationPoids) {
        this.estimationPoids = estimationPoids;
    }

    public BigDecimal getQteTotal() {
        return qteTotal;
    }

    public void setQteTotal(BigDecimal qteTotal) {
        this.qteTotal = qteTotal;
    }

    public BigDecimal getQteMaleReception() {
        return qteMaleReception;
    }

    public void setQteMaleReception(BigDecimal qteMaleReception) {
        this.qteMaleReception = qteMaleReception;
    }

    public BigDecimal getQteFemaleReception() {
        return qteFemaleReception;
    }

    public void setQteFemaleReception(BigDecimal qteFemaleReception) {
        this.qteFemaleReception = qteFemaleReception;
    }

    public BigDecimal getTotalPaye() {
        return totalPaye;
    }

    public void setTotalPaye(BigDecimal totalPaye) {
        this.totalPaye = totalPaye;
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

    public Commande() {
    }

    public Commande(Long id, Date dateCommande, int etat, BigDecimal montantTotal) {
        this.id = id;
        this.dateCommande = dateCommande;
        this.etatPaiement = etat;
        this.montantTotal = montantTotal;
    }

    public Commande(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEtatPaiement() {
        return etatPaiement;
    }

    public void setEtatPaiement(int etatPaiement) {
        this.etatPaiement = etatPaiement;
    }

    public List<Reception> getLivraisons() {
        if (receptions == null) {
            receptions = new ArrayList();
        }
        return receptions;
    }

    public void setLivraisons(List<Reception> livraisons) {
        this.receptions = livraisons;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public List<PaiementCommande> getPaiementCommandes() {
        if (paiementCommandes == null) {
            paiementCommandes = new ArrayList();
        }
        return paiementCommandes;
    }

    public void setPaiementCommandes(List<PaiementCommande> paiementCommandes) {
        this.paiementCommandes = paiementCommandes;
    }

    public Fournisseur getFournisseur() {
        if (fournisseur == null) {
            fournisseur = new Fournisseur();
        }
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public int getEtatReception() {
        return etatReception;
    }

    public void setEtatReception(int etatLivraisons) {
        this.etatReception = etatLivraisons;
    }

    public List<Reception> getReceptions() {
        if (receptions == null) {
            receptions = new ArrayList();
        }
        return receptions;
    }

    public void setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
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
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commande other = (Commande) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", dateCommande=" + dateCommande + ", etat=" + etatPaiement + ", montantTotal=" + montantTotal + '}';
    }

}
