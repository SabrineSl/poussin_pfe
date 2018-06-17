/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Client;
import bean.Utilisateur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sabrine
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> {

    @PersistenceContext(unitName = "e_poussinsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
        public Utilisateur clone(Utilisateur utilisateur) {
        Utilisateur cloneUtilisateur = new Utilisateur();
        cloneUtilisateur.setTel(utilisateur.getTel());
        cloneUtilisateur.setNom(utilisateur.getNom());
        cloneUtilisateur.setEmail(utilisateur.getEmail());
        cloneUtilisateur.setLogin(utilisateur.getLogin());
        cloneUtilisateur.setPassword(utilisateur.getPassword());
        cloneUtilisateur.setPrenom(utilisateur.getPrenom());
        return cloneUtilisateur;
    }
       public void addItems(List<Utilisateur> utilisateurs) {
        for (Utilisateur utilisateur : utilisateurs) {
            create(utilisateur);

        }
    }
       public Utilisateur findByLogin(Utilisateur utilisateur) {
        List<Utilisateur> utilisateurs = getEntityManager().createQuery("SELECT u FROM Utilisateur u WHERE u.login='" + utilisateur.getLogin() + "'").getResultList();
           System.out.println(utilisateurs);
        if (utilisateurs.isEmpty() || utilisateurs == null) {
            return null;
        } else {
            return utilisateurs.get(0);
        }
    }
    public int connexion1(Utilisateur utilisateur){
        Utilisateur  utilisateur1 =findByLogin(utilisateur);
        if(utilisateur1!=null){
        if(!utilisateur1.getPassword().equals(utilisateur.getPassword())){
            return -1;
        }else {
            return 1;
        }
        }
        else {
               return -2;  
                }
       
                   
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }
    
}
