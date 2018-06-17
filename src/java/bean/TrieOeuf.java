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
public class TrieOeuf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateTrie;
    private int numSemaine;
    private int annee;
    private BigDecimal entree;// = production des oeufs
    private BigDecimal vente;
    private BigDecimal don;
    private BigDecimal perte;
    private BigDecimal situationFinale;
    private BigDecimal situationInitiale;
    @ManyToOne
    private Ferme ferme;
    @OneToOne
    private CategorieOeuf categorieOeuf;
    @OneToMany(mappedBy = "trieOeuf")
    private List<Incubation> incubations;

    public Ferme getFerme() {
        return ferme;
    }

    public void setFerme(Ferme ferme) {
        this.ferme = ferme;
    }

    public TrieOeuf() {
    }

    public TrieOeuf(Long id) {
        this.id = id;
    }

    public TrieOeuf(Long id, Date dateTrie, int semaine, int annee, BigDecimal entree, BigDecimal vente, BigDecimal don, BigDecimal perte, BigDecimal situationFinale, BigDecimal situationInitiale) {
        this.id = id;
        this.dateTrie = dateTrie;
        this.numSemaine = semaine;
        this.annee = annee;
        this.entree = entree;
        this.vente = vente;
        this.don = don;
        this.perte = perte;
        this.situationFinale = situationFinale;
        this.situationInitiale = situationInitiale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Incubation> getIncubations() {
        if (incubations == null) {
            incubations = new ArrayList();
        }
        return incubations;
    }

    public void setIncubations(List<Incubation> incubations) {
        this.incubations = incubations;
    }

    public Date getDateTrie() {
        return dateTrie;
    }

    public void setDateTrie(Date dateTrie) {
        this.dateTrie = dateTrie;
    }

    public int getNumSemaine() {
        return numSemaine;
    }

    public void setNumSemaine(int numSemaine) {
        this.numSemaine = numSemaine;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public BigDecimal getEntree() {
        return entree;
    }

    public void setEntree(BigDecimal entree) {
        this.entree = entree;
    }

    public BigDecimal getVente() {
        return vente;
    }

    public void setVente(BigDecimal vente) {
        this.vente = vente;
    }

    public BigDecimal getDon() {
        return don;
    }

    public void setDon(BigDecimal don) {
        this.don = don;
    }

    public BigDecimal getPerte() {
        return perte;
    }

    public void setPerte(BigDecimal perte) {
        this.perte = perte;
    }

    public BigDecimal getSituationFinale() {
        return situationFinale;
    }

    public void setSituationFinale(BigDecimal situationFinale) {
        this.situationFinale = situationFinale;
    }

    public BigDecimal getSituationInitiale() {
        return situationInitiale;
    }

    public void setSituationInitiale(BigDecimal situationInitiale) {
        this.situationInitiale = situationInitiale;
    }

    public CategorieOeuf getCategorieOeuf() {
        if (categorieOeuf == null) {
            categorieOeuf = new CategorieOeuf();
        }
        return categorieOeuf;
    }

    public void setCategorieOeuf(CategorieOeuf categorieOeuf) {
        this.categorieOeuf = categorieOeuf;
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
        if (!(object instanceof TrieOeuf)) {
            return false;
        }
        TrieOeuf other = (TrieOeuf) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TrieOeuf{" + "id=" + id + ", dateTrie=" + dateTrie + ", semaine=" + numSemaine + ", annee=" + annee + ", entree=" + entree + ", vente=" + vente + ", don=" + don + ", perte=" + perte + ", situationFinale=" + situationFinale + ", situationInitiale=" + situationInitiale + '}';
    }

}
