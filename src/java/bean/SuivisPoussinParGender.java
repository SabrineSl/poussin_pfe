/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author ushiho
 */
@Entity
public class SuivisPoussinParGender implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal debutJour;//effectif en debut du Jour des poussins
    private BigDecimal finJour;// = reliquat = effectif en Fin du Jour des poussins
    private BigDecimal mortalite;
    private BigDecimal entree;
    private BigDecimal vente;
    @OneToOne(mappedBy = "suivisPoussinFemale")
    private SuivisJournalier suivisJournalier;

    public SuivisJournalier getSuivisJournalier() {
        return suivisJournalier;
    }

    public void setSuivisJournalier(SuivisJournalier suivisJournalier) {
        this.suivisJournalier = suivisJournalier;
    }

    public SuivisPoussinParGender() {
    }

    public SuivisPoussinParGender(Long id) {
        this.id = id;
    }

    public SuivisPoussinParGender(Long id, BigDecimal DebutJour, BigDecimal FinJour, BigDecimal mortalite, BigDecimal entree, BigDecimal vente) {
        this.id = id;
        this.debutJour = DebutJour;
        this.finJour = FinJour;
        this.mortalite = mortalite;
        this.entree = entree;
        this.vente = vente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDebutJour() {
        return debutJour;
    }

    public void setDebutJour(BigDecimal DebutJour) {
        this.debutJour = DebutJour;
    }

    public BigDecimal getFinJour() {
        return finJour;
    }

    public void setFinJour(BigDecimal FinJour) {
        this.finJour = FinJour;
    }

    public BigDecimal getMortalite() {
        return mortalite;
    }

    public void setMortalite(BigDecimal mortalite) {
        this.mortalite = mortalite;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuivisPoussinParGender)) {
            return false;
        }
        SuivisPoussinParGender other = (SuivisPoussinParGender) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SuivisPoussinParGender{" + "id=" + id + ", DebutJour=" + debutJour + ", FinJour=" + finJour + ", mortalite=" + mortalite + ", entree=" + entree + ", vente=" + vente + '}';
    }

}
