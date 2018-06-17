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
import javax.persistence.ManyToOne;

/**
 *
 * @author YOUNES
 */
@Entity
public class Couvoir implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private BigDecimal capaciteMax;
    @ManyToOne
    private Ferme ferme; // dans qulle ferme existe ce couvoir

    public Couvoir() {
    }

    public Couvoir(Long id, String nom, BigDecimal capaciteMax) {
        this.id = id;
        this.nom = nom;
        this.capaciteMax = capaciteMax;
    }

    public Couvoir(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(BigDecimal capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Couvoir)) {
            return false;
        }
        Couvoir other = (Couvoir) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Couvoir{" + "id=" + id + ", nom=" + nom + ", capaciteMax=" + capaciteMax + '}';
    }

}
