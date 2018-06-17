/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ushiho
 */
@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String tel;
    private String email;
    @OneToMany(mappedBy = "client")
    private List<DemandePoules> demandePouless;
    @OneToMany(mappedBy = "client")
    private List<DemandeOeufs> demandeOeufss;
    @OneToMany(mappedBy = "client")
    private List<LivraisonPoules> livraisons;
    @OneToMany(mappedBy = "client")
    private List<LivraisonOeufs> livraisonOeufss;

    public Client(Long id, String nom, String tel, String email) {
        this.id = id;
        this.nom = nom;
        this.tel = tel;
        this.email = email;
    }

    public Client() {
    }

    public Client(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<LivraisonPoules> getLivraisons() {
        return livraisons;
    }

    public void setLivraisons(List<LivraisonPoules> livraisons) {
        this.livraisons = livraisons;
    }

    public List<LivraisonOeufs> getLivraisonOeufss() {
        return livraisonOeufss;
    }

    public void setLivraisonOeufss(List<LivraisonOeufs> livraisonOeufss) {
        this.livraisonOeufss = livraisonOeufss;
    }
    

    public List<DemandePoules> getDemandePouless() {
        if (demandePouless == null) {
            demandePouless = new ArrayList();
        }
        return demandePouless;
    }

    public void setDemandePouless(List<DemandePoules> demandePouless) {
        this.demandePouless = demandePouless;
    }

    public List<DemandeOeufs> getDemandeOeufss() {
        if (demandeOeufss == null) {
            demandeOeufss = new ArrayList();
        }
        return demandeOeufss;
    }

    public void setDemandeOeufss(List<DemandeOeufs> demandeOeufss) {
        this.demandeOeufss = demandeOeufss;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Client[ id=" + id + " ]";
    }

}
