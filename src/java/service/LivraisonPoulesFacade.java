/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Client;
import bean.DemandePoules;
import bean.LivraisonPoules;
import controller.util.SearchUtil;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sabrine
 */
@Stateless
public class LivraisonPoulesFacade extends AbstractFacade<LivraisonPoules> {

    @PersistenceContext(unitName = "e_poussinsPU")
    private EntityManager em;
  DemandePoulesFacade  demandePoulesFacade;
    
     public List<DemandePoules> findByCriteriaForChart(Client client, int annee) {
        String requete = "SELECT d FROM DemandePoules d WHERE 1=1 ";
         if( annee!=0){
        requete += SearchUtil.addConstraint("d", "annee", "=", annee);
        }
        if (client != null) {
            requete += SearchUtil.addConstraint("d", "client.id", "=", client.getId());
        }

        System.out.println("haa requeta" + requete);
        List<DemandePoules> list = em.createQuery(requete).getResultList();
        return list;
    }
       public List<DemandePoules> findByCriteriaForChart2(Client client, int annee) {
        String requete = "SELECT d FROM DemandePoules d WHERE 1=1 ";
        requete += SearchUtil.addConstraint("d", "annee", "=", annee);
        if (client != null) {
            requete += SearchUtil.addConstraint("d", "client.id", "=", client.getId());
        }
        System.out.println("haa requeta" + requete);
        List<DemandePoules> list = em.createQuery(requete).getResultList();
        return list;
    }

    
        public List<DemandePoules> findDemandeByClient(Client client) {
        String requete = "SELECT d FROM DemandePoules d WHERE d.client.id='" + client.getId() + "'";
        System.out.println("haa requeta" + requete);
        return em.createQuery(requete).getResultList();

    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LivraisonPoulesFacade() {
        super(LivraisonPoules.class);
    }
    
}
