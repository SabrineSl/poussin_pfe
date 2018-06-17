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
import javax.persistence.OneToOne;

/**
 *
 * @author ushiho
 */
@Entity
public class EstimationOeufItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal mesure;
    private int numeroSemaine;
    @OneToOne
    private CategorieOeuf categorieOeuf;
    @ManyToOne
    private EstimationOeuf estimationOeuf;

    public EstimationOeufItem() {
    }

    public EstimationOeufItem(Long id) {
        this.id = id;
    }

    public EstimationOeufItem(Long id, BigDecimal mesure, int numeroSemaine) {
        this.id = id;
        this.mesure = mesure;
        this.numeroSemaine = numeroSemaine;
    }

    public BigDecimal getMesure() {
        return mesure;
    }

    public void setMesure(BigDecimal mesure) {
        this.mesure = mesure;
    }

    public int getNumeroSemaine() {
        return numeroSemaine;
    }

    public void setNumeroSemaine(int numeroSemaine) {
        this.numeroSemaine = numeroSemaine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EstimationOeuf getEstimationOeuf() {
        if (estimationOeuf == null) {
            estimationOeuf = new EstimationOeuf();
        }
        return estimationOeuf;
    }

    public void setEstimationOeuf(EstimationOeuf estimationOeuf) {
        this.estimationOeuf = estimationOeuf;
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
        if (!(object instanceof EstimationOeufItem)) {
            return false;
        }
        EstimationOeufItem other = (EstimationOeufItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Estimation[ id=" + id + " ]";
    }

}
