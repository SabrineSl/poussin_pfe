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
 * @author asus
 */
@Entity
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String deviceCategorie;
    private String Browser;
    private String operatingSystem;
    private String macAdress;
    private String ipAdress;
    @ManyToOne
    private Utilisateur utilisateur;

    public Device() {
    }

    public Device(Long id) {
        this.id = id;
    }

    public Device(Long id, String deviceCategorie, String Browser, String operatingSystem, String macAdress, String ipAdress) {
        this.id = id;
        this.deviceCategorie = deviceCategorie;
        this.Browser = Browser;
        this.operatingSystem = operatingSystem;
        this.macAdress = macAdress;
        this.ipAdress = ipAdress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceCategorie() {
        return deviceCategorie;
    }

    public void setDeviceCategorie(String deviceCategorie) {
        this.deviceCategorie = deviceCategorie;
    }

    public String getBrowser() {
        return Browser;
    }

    public void setBrowser(String Browser) {
        this.Browser = Browser;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public Utilisateur getUtilisateur() {
        if (utilisateur == null) {
            utilisateur = new Utilisateur();
        }
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
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
        if (!(object instanceof Device)) {
            return false;
        }
        Device other = (Device) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Device{" + "id=" + id + ", deviceCategorie=" + deviceCategorie + ", Browser=" + Browser + ", operatingSystem=" + operatingSystem + ", macAdress=" + macAdress + ", ipAdress=" + ipAdress + '}';
    }

}
