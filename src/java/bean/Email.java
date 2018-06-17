/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ushiho
 */
@Entity
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private String contenu;
    private int type;
//1:acceptation d'une demande adhesion
    //2: envoyer le code/login generer
    //3: restaurer le mot de passe
    //4 : envoyer code de verification
    //5: autre... : fait attention au penalite vous avez une telle penaite etc 00
    @ManyToOne
    private SystemInfo systemInfo;

    public Email() {
    }

    public Email(Long id, String subject, String contenu, int type) {
        this.id = id;
        this.subject = subject;
        this.contenu = contenu;
        this.type = type;
    }

    public Email(String subject, String contenu, int type) {
        this.subject = subject;
        this.contenu = contenu;
        this.type = type;
    }

    public Email(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SystemInfo getSystemInfo() {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo systemInfo) {
        this.systemInfo = systemInfo;
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
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Email{" + "id=" + id + ", subject=" + subject + ", contenu=" + contenu + ", type=" + type + '}';
    }

}
