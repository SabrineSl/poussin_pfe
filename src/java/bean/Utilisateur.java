/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author ushiho
 */
@Entity
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private boolean blocked;
    private int nbrEssaye;
    private boolean mdpChanged;
    private boolean adminSys;
    
    
    @OneToOne(mappedBy = "utilisateur")
    private Question question;
    @OneToMany(mappedBy = "utilisateurs")
    private List<Ferme> fermes;

    public Utilisateur(Long id, String login, String password, String nom, String prenom, String email, String tel, boolean blocked, int nbrCnx, boolean mdpChanged, boolean adminSys) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.blocked = blocked;
        this.nbrEssaye = nbrCnx;
        this.mdpChanged = mdpChanged;
        this.adminSys = adminSys;
    }

    public List<Ferme> getFermes() {
        return fermes;
    }

    public void setFermes(List<Ferme> fermes) {
        this.fermes = fermes;
    }
    

    public Utilisateur(Long id) {
        this.id = id;
    }

    public Utilisateur() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAdminSys() {
        return adminSys;
    }

    public void setAdminSys(boolean adminSys) {
        this.adminSys = adminSys;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getNbrEssaye() {
        return nbrEssaye;
    }

    public void setNbrEssaye(int nbrEssaye) {
        this.nbrEssaye = nbrEssaye;
    }

    public boolean isMdpChanged() {
        return mdpChanged;
    }

    public void setMdpChanged(boolean mdpChanged) {
        this.mdpChanged = mdpChanged;
    }

   
    public Question getQuestion() {
        if (question == null) {
            question = new Question();
        }
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilisateur other = (Utilisateur) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", tel=" + tel + ", blocked=" + blocked + ", nbrCnx=" + nbrEssaye + ", mdpChanged=" + mdpChanged + '}';
    }

}
