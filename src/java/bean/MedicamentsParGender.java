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
public class MedicamentsParGender implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal situationInitiale;
    private BigDecimal situationFinale;
    private BigDecimal entree;
    private BigDecimal consomation;
    @OneToOne
    private TypeMedicament typeMedicament;
    @OneToOne(mappedBy = "medicamentsMale")
    private SuivisJournalier suivisJournalier;

    public SuivisJournalier getSuivisJournalier() {
        return suivisJournalier;
    }

    public void setSuivisJournalier(SuivisJournalier suivisJournalier) {
        this.suivisJournalier = suivisJournalier;
    }
        

    public MedicamentsParGender() {
    }

    public MedicamentsParGender(Long id) {
        this.id = id;
    }

    public MedicamentsParGender(Long id, BigDecimal situationInitiale, BigDecimal situationFinale, BigDecimal entree, BigDecimal consomation) {
        this.id = id;
        this.situationInitiale = situationInitiale;
        this.situationFinale = situationFinale;
        this.entree = entree;
        this.consomation = consomation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSituationInitiale() {
        return situationInitiale;
    }

    public void setSituationInitiale(BigDecimal situationInitiale) {
        this.situationInitiale = situationInitiale;
    }

    public BigDecimal getSituationFinale() {
        return situationFinale;
    }

    public void setSituationFinale(BigDecimal situationFinale) {
        this.situationFinale = situationFinale;
    }

    public BigDecimal getEntree() {
        return entree;
    }

    public void setEntree(BigDecimal entree) {
        this.entree = entree;
    }

    public BigDecimal getConsomation() {
        return consomation;
    }

    public void setConsomation(BigDecimal consomation) {
        this.consomation = consomation;
    }

    public TypeMedicament getTypeMedicament() {
        if (typeMedicament == null) {
            typeMedicament = new TypeMedicament();
        }
        return typeMedicament;
    }

    public void setTypeMedicament(TypeMedicament typeMedicament) {
        this.typeMedicament = typeMedicament;
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
        if (!(object instanceof MedicamentsParGender)) {
            return false;
        }
        MedicamentsParGender other = (MedicamentsParGender) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MedicamentsParGender{" + "id=" + id + ", situationInitiale=" + situationInitiale + ", situationFinale=" + situationFinale + ", entree=" + entree + ", consomation=" + consomation + '}';
    }

}
