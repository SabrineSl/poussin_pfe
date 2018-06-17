/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author ushiho
 */
@Entity
public class EstimationPoids implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEstimation;
    @ManyToOne
    private Fournisseur fournisseur;
    @OneToMany(mappedBy = "estimationPoids")
    private List<EstimationPoidsItem> estimationPoidsItems;

    public EstimationPoids() {
    }

    public EstimationPoids(Long id, String description, Date dateEstimation) {
        this.id = id;
        this.description = description;
        this.dateEstimation = dateEstimation;
    }

    public EstimationPoids(Long id) {
        this.id = id;
    }

    public List<EstimationPoidsItem> getEstimationPoidsItems() {
        if (estimationPoidsItems == null) {
            estimationPoidsItems = new ArrayList();
        }
        return estimationPoidsItems;
    }

    public void setEstimationPoidsItems(List<EstimationPoidsItem> estimationPoidsItems) {
        this.estimationPoidsItems = estimationPoidsItems;
    }

    public Fournisseur getFournisseur() {
        if(fournisseur==null){
            fournisseur = new Fournisseur();
        }
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateEstimation() {
        return dateEstimation;
    }

    public void setDateEstimation(Date dateEstimation) {
        this.dateEstimation = dateEstimation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof EstimationPoids)) {
            return false;
        }
        EstimationPoids other = (EstimationPoids) object;
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
