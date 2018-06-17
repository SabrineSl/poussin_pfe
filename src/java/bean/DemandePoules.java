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
import javax.persistence.Temporal;

/**
 *
 * @author ushiho
 */
@Entity
public class DemandePoules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDemande;
    private int etatPaiement;//1 : payé ; 2: non payé ; 3: en cours de paiement
    private int etatLivraison; // 1: livraie ; 2 :non livraie ; 3 : en cours de livraisons
    private BigDecimal montantTotal;
    private BigDecimal totalPaye;
    private BigDecimal totalNonEncaisse;
    private BigDecimal qteTotal; // qteMale+ qteFemale
    private BigDecimal qteMale;
    private BigDecimal qteFemale;
    private BigDecimal qteMaleLivree;
    private BigDecimal qteFemaleLivree;
    private String reference;
    private int mois;
    private int annee;
    
    @ManyToOne
    private Utilisateur responsable;
    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "demandePoules")
    private List<PaiementDemandePoules> paiementDemandePouless;

    public DemandePoules(Long id, Date dateDemande, BigDecimal qteTotal, BigDecimal qteMale, BigDecimal qteFemale, Client client) {
        this.id = id;
        this.dateDemande = dateDemande;
        this.qteTotal = qteTotal;
        this.qteMale = qteMale;
        this.qteFemale = qteFemale;
        this.client = client;
    }

    public DemandePoules(Long id, Date dateDemande, Client client) {
        this.id = id;
        this.dateDemande = dateDemande;
        this.client = client;
    }

    public BigDecimal getTotalNonEncaisse() {
        return totalNonEncaisse;
    }

    public void setTotalNonEncaisse(BigDecimal totalNonEncaisse) {
        this.totalNonEncaisse = totalNonEncaisse;
    }

    public DemandePoules(Long id, Date dateDemande, int etatPaiement, int etatLivraison, BigDecimal montantTotal, BigDecimal totalPaye, BigDecimal totalNonEncaisse, BigDecimal qteTotal, BigDecimal qteMale, BigDecimal qteFemale, BigDecimal qteMaleLivree, BigDecimal qteFemaleLivree, String reference, int mois, int annee) {
        this.id = id;
        this.dateDemande = dateDemande;
        this.etatPaiement = etatPaiement;
        this.etatLivraison = etatLivraison;
        this.montantTotal = montantTotal;
        this.totalPaye = totalPaye;
        this.totalNonEncaisse = totalNonEncaisse;
        this.qteTotal = qteTotal;
        this.qteMale = qteMale;
        this.qteFemale = qteFemale;
        this.qteMaleLivree = qteMaleLivree;
        this.qteFemaleLivree = qteFemaleLivree;
        this.reference = reference;
        this.mois = mois;
        this.annee = annee;
    }



 

    
    
    
    
    
    public BigDecimal getQteTotal() {
        return qteTotal;
    }

    public void setQteTotal(BigDecimal qteTotal) {
        this.qteTotal = qteTotal;
    }

    public BigDecimal getQteMaleLivree() {
        return qteMaleLivree;
    }

    public void setQteMaleLivree(BigDecimal qteMaleLivree) {
        this.qteMaleLivree = qteMaleLivree;
    }

    public BigDecimal getQteFemaleLivree() {
        return qteFemaleLivree;
    }

    public void setQteFemaleLivree(BigDecimal qteFemaleLivree) {
        this.qteFemaleLivree = qteFemaleLivree;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
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

    public DemandePoules() {
    }

    public DemandePoules(Long id, Date dateCommande, int etat, BigDecimal montantTotal) {
        this.id = id;
        this.dateDemande = dateCommande;
        this.etatPaiement = etat;
        this.montantTotal = montantTotal;
    }

    public DemandePoules(Long id) {
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

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public List<PaiementDemandePoules> getPaiementDemandePouless() {
        if(paiementDemandePouless==null){
            paiementDemandePouless = new ArrayList();
        }
        return paiementDemandePouless;
    }

    public void setPaiementDemandePouless(List<PaiementDemandePoules> paiementDemandePouless) {
        this.paiementDemandePouless = paiementDemandePouless;
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

    public Utilisateur getResponsable() {
        if (responsable == null) {
            responsable = new Utilisateur();
        }
        return responsable;
    }

    public void setResponsable(Utilisateur responsable) {
        this.responsable = responsable;
    }

    public int getEtatLivraison() {
        return etatLivraison;
    }

    public void setEtatLivraison(int etatLivraisons) {
        this.etatLivraison = etatLivraisons;
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
        final DemandePoules other = (DemandePoules) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DemandePoules{" + "id=" + id + ", dateDemande=" + dateDemande + ", etatPaiement=" + etatPaiement + ", etatLivraison=" + etatLivraison + ", montantTotal=" + montantTotal + ", totalPaye=" + totalPaye + ", totalNonEncaisse=" + totalNonEncaisse + ", qteTotal=" + qteTotal + ", qteMale=" + qteMale + ", qteFemale=" + qteFemale + ", qteMaleLivree=" + qteMaleLivree + ", qteFemaleLivree=" + qteFemaleLivree + ", reference=" + reference + ", mois=" + mois + ", annee=" + annee + ", client=" + client + '}';
    }

  

}
