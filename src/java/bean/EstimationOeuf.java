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
public class EstimationOeuf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEstimation;
    @ManyToOne
    private Utilisateur responsable;
    @OneToMany(mappedBy = "estimationOeuf")
    private List<EstimationOeufItem> estimationOeufItems;

    public EstimationOeuf() {
    }

    public EstimationOeuf(Long id, String description, Date dateEstimation) {
        this.id = id;
        this.description = description;
        this.dateEstimation = dateEstimation;
    }

    public EstimationOeuf(String description, Date dateEstimation) {
        this.description = description;
        this.dateEstimation = dateEstimation;
    }

    public List<EstimationOeufItem> getEstimationOeufItems() {
        if(estimationOeufItems==null){
            estimationOeufItems = new ArrayList();
        }
        return estimationOeufItems;
    }

    public void setEstimationOeufItems(List<EstimationOeufItem> estimationOeufItems) {
        this.estimationOeufItems = estimationOeufItems;
    }

    public EstimationOeuf(Long id) {
        this.id = id;
    }

    public Utilisateur getResponsable() {
        if(responsable==null){
            responsable = new Utilisateur();
        }
        return responsable;
    }

    public void setResponsable(Utilisateur responsable) {
        this.responsable = responsable;
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
        if (!(object instanceof EstimationOeuf)) {
            return false;
        }
        EstimationOeuf other = (EstimationOeuf) object;
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
