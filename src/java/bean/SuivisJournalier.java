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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ushiho
 */
@Entity
public class SuivisJournalier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSuivis;
    private int semaine;
    private int numSemaine;
    private int annee;
    @ManyToOne
    private Ferme ferme;
    
    @OneToOne
    private SuivisPoussinParGender suivisPoussinFemale;
    @OneToOne
    private SuivisPoussinParGender suivisPoussinMale;
    private BigDecimal production;
    private BigDecimal poids;
    
    
    @OneToOne
    private AlimentsParGender alimentsMale;
   
    @OneToOne
    private AlimentsParGender alimentsFemale;
    private BigDecimal totalConsommationAliments;//consomation M + conso F
    
    @OneToOne
    private MedicamentsParGender medicamentsMale;
    @OneToOne
    private MedicamentsParGender medicamentsFemale;
    private BigDecimal totalConsommationMedicaments;// conso medic F + conso Medi M

    public BigDecimal getPoids() {
        return poids;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public SuivisJournalier() {
    }

    public SuivisJournalier(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateSuivis() {
        return dateSuivis;
    }

    public void setDateSuivis(Date dateSuivis) {
        this.dateSuivis = dateSuivis;
    }

    public int getSemaine() {
        return semaine;
    }

    public void setSemaine(int semaine) {
        this.semaine = semaine;
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

    public Ferme getFerme() {
        if (ferme == null) {
            ferme = new Ferme();
        }
        return ferme;
    }

    public void setFerme(Ferme ferme) {
        this.ferme = ferme;
    }

    public SuivisPoussinParGender getSuivisPoussinFemale() {
        if (suivisPoussinFemale == null) {
            suivisPoussinFemale = new SuivisPoussinParGender();
        }
        return suivisPoussinFemale;
    }

    public void setSuivisPoussinFemale(SuivisPoussinParGender suivisPoussinFemale) {
        this.suivisPoussinFemale = suivisPoussinFemale;
    }

    public SuivisPoussinParGender getSuivisPoussinMale() {
        if (suivisPoussinMale == null) {
            suivisPoussinMale = new SuivisPoussinParGender();
        }
        return suivisPoussinMale;
    }

    public void setSuivisPoussinMale(SuivisPoussinParGender suivisPoussinMale) {
        this.suivisPoussinMale = suivisPoussinMale;
    }

    public AlimentsParGender getAlimentsMale() {
        if (alimentsMale == null) {
            alimentsMale = new AlimentsParGender();
        }
        return alimentsMale;
    }

    public void setAlimentsMale(AlimentsParGender alimentsMale) {
        this.alimentsMale = alimentsMale;
    }

    public AlimentsParGender getAlimentsFemale() {
        if (alimentsFemale == null) {
            alimentsFemale = new AlimentsParGender();
        }
        return alimentsFemale;
    }

    public void setAlimentsFemale(AlimentsParGender alimentsFemale) {
        this.alimentsFemale = alimentsFemale;
    }

    public BigDecimal getProduction() {
        return production;
    }

    public void setProduction(BigDecimal production) {
        this.production = production;
    }

    public BigDecimal getTotalConsommationAliments() {
        return totalConsommationAliments;
    }

    public void setTotalConsommationAliments(BigDecimal totalConsommationAliments) {
        this.totalConsommationAliments = totalConsommationAliments;
    }

    public MedicamentsParGender getMedicamentsMale() {
        if (medicamentsMale == null) {
            medicamentsMale = new MedicamentsParGender();
        }
        return medicamentsMale;
    }

    public void setMedicamentsMale(MedicamentsParGender medicamentsMale) {
        this.medicamentsMale = medicamentsMale;
    }

    public MedicamentsParGender getMedicamentsFemale() {
        if (medicamentsFemale == null) {
            medicamentsFemale = new MedicamentsParGender();
        }
        return medicamentsFemale;
    }

    public void setMedicamentsFemale(MedicamentsParGender medicamentsFemale) {
        this.medicamentsFemale = medicamentsFemale;
    }

    public BigDecimal getTotalConsommationMedicaments() {
        return totalConsommationMedicaments;
    }

    public void setTotalConsommationMedicaments(BigDecimal totalConsommationMedicaments) {
        this.totalConsommationMedicaments = totalConsommationMedicaments;
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
        if (!(object instanceof SuivisJournalier)) {
            return false;
        }
        SuivisJournalier other = (SuivisJournalier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SuivisJournalier{" + "id=" + id + ", dateSuivis=" + dateSuivis + ", semaine=" + semaine + ", numSemaine=" + numSemaine + ", annee=" + annee + ", production=" + production + ", poids=" + poids + ", alimentsFemale=" + alimentsFemale + ", totalConsommationAliments=" + totalConsommationAliments + ", totalConsommationMedicaments=" + totalConsommationMedicaments + '}';
    }

}
