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
import javax.persistence.OneToOne;

/**
 *
 * @author ushiho
 */
@Entity
public class SystemInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String boiteEmailSys;
    private String passwordEmailSys;
    @OneToOne
    private Utilisateur adminSys;//utiliser pour créer un profil pr autres utilisateurs
    @OneToMany
    private List<Utilisateur> utilisateurs; // les users(responsable) existent sur le sys
    @OneToMany(mappedBy = "systemInfo")
    private List<Email> emails;

    public SystemInfo() {
    }

    public SystemInfo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Utilisateur> getUtilisateurs() {
        if (utilisateurs == null) {
            utilisateurs = new ArrayList();
        }
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Utilisateur getAdminSys() {
        if (adminSys == null) {
            adminSys = new Utilisateur();
        }
        return adminSys;
    }

    public void setAdminSys(Utilisateur adminSys) {
        this.adminSys = adminSys;
    }

    public String getBoiteEmailSys() {
        return boiteEmailSys;
    }

    public void setBoiteEmailSys(String boiteEmailSys) {
        this.boiteEmailSys = boiteEmailSys;
    }

    public String getPasswordEmailSys() {
        return passwordEmailSys;
    }

    public void setPasswordEmailSys(String passwordEmailSys) {
        this.passwordEmailSys = passwordEmailSys;
    }

    public List<Email> getEmails() {
        if (emails == null) {
            emails = new ArrayList();
        }
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
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
        if (!(object instanceof SystemInfo)) {
            return false;
        }
        SystemInfo other = (SystemInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.SystemInfo[ id=" + id + " ]";
    }

}
