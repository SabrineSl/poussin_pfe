/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author ushiho
 */
@Entity
public class Ferme implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private BigDecimal capaciteMin;
    private BigDecimal capaciteMax;
    
    @ManyToOne
    private Utilisateur utilisateurs;
    @OneToMany(mappedBy = "ferme")
    private List<Couvoir> couvoirs;
    @OneToMany(mappedBy = "ferme")
    private List<SuivisJournalier> suivisJournaliers;
    @OneToMany(mappedBy = "ferme")
    private List<Distribution> distributions;
    @OneToMany(mappedBy = "ferme")
    private List<Reception> receptions;
    @OneToMany(mappedBy = "ferme")
    private List<Commande> commandes;
    @OneToMany(mappedBy = "ferme")
    private List<ProductionPoussin> productionPoussins;

    public List<ProductionPoussin> getProductionPoussins() {
        if (productionPoussins == null) {
            productionPoussins = new ArrayList();
        }
        return productionPoussins;
    }

    public void setProductionPoussins(List<ProductionPoussin> productionPoussins) {
        this.productionPoussins = productionPoussins;
    }

    public BigDecimal getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(BigDecimal capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public Ferme() {
    }

    public Ferme(Long id) {
        this.id = id;
    }

    public Ferme(Long id, String nom, BigDecimal capacite) {
        this.id = id;
        this.nom = nom;
        this.capaciteMin = capacite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getCapaciteMin() {
        return capaciteMin;
    }

    public void setCapaciteMin(BigDecimal capaciteMin) {
        this.capaciteMin = capaciteMin;
    }

    public Utilisateur getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Utilisateur utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

   

    public List<Couvoir> getCouvoirs() {
        if (couvoirs == null) {
            couvoirs = new ArrayList();
        }
        return couvoirs;
    }

    public void setCouvoirs(List<Couvoir> couvoirs) {
        this.couvoirs = couvoirs;
    }

    public List<SuivisJournalier> getSuivisJournaliers() {
        if (suivisJournaliers == null) {
            suivisJournaliers = new ArrayList();
        }
        return suivisJournaliers;
    }

    public void setSuivisJournaliers(List<SuivisJournalier> suivisJournaliers) {
        this.suivisJournaliers = suivisJournaliers;
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

    public List<Reception> getReceptions() {
        if (receptions == null) {
            receptions = new ArrayList();
        }
        return receptions;
    }

    public void setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
    }

    public List<Commande> getCommandes() {
        if (commandes == null) {
            commandes = new ArrayList();
        }
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
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
        if (!(object instanceof Ferme)) {
            return false;
        }
        Ferme other = (Ferme) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ferme{" + "id=" + id + ", nom=" + nom + ", capacite=" + capaciteMin + '}';
    }

}
