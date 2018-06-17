/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Client;
import bean.DemandePoules;
import bean.Paiement;
import bean.PaiementDemandePoules;
import controller.util.SearchUtil;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sabrine
 */
@Stateless
public class PaiementDemandePoulesFacade extends AbstractFacade<PaiementDemandePoules> {

    @PersistenceContext(unitName = "e_poussinsPU")
    private EntityManager em;
    @EJB
    PaiementFacade paiementFacade;
    @EJB
    DemandePoulesFacade demandePoulesFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<PaiementDemandePoules> findByDemande(DemandePoules demande) {

        String requete = "SELECT p FROM PaiementDemandePoules p WHERE p.demandePoules.id ='" + demande.getId() + "'";
        System.out.println("haa requeta" + requete);
        return em.createQuery(requete).getResultList();

    }

    public int PaiementDemande(PaiementDemandePoules paiementDemandePoules, DemandePoules demandePoules, BigDecimal total, int type, Date dateEncaissement) {
        Paiement p = new Paiement();
        p.setId(generate("Paiement", "id"));
        p.setType(type);
        p.setTotal(total);
        if (type==1) {//espese
            p.setDatePaiement(new Date());
            p.setDateEncaissement(null);

        } else {
            p.setDatePaiement(null);
            p.setDateEncaissement(null);
        }
        paiementFacade.create(p);

        DemandePoules demandePoules1 = demandePoulesFacade.find(demandePoules.getId());
        
            if(type==1){
       
        if (demandePoules1.getTotalPaye() == null) {
            demandePoules1.setTotalPaye(total);
        } else {
            BigDecimal totalPaiement = demandePoules1.getTotalPaye().add(total);
            demandePoules1.setTotalPaye(totalPaiement);
        }
          

        if (demandePoules1.getMontantTotal().compareTo(demandePoules1.getTotalPaye()) <= 0) {//payé
            demandePoules1.setEtatPaiement(1);
        } else if (demandePoules1.getMontantTotal().compareTo(demandePoules1.getTotalPaye()) > 0) {//en cours
            demandePoules1.setEtatPaiement(2);
        } else if (demandePoules1.getTotalPaye() == null) {
            demandePoules1.setEtatPaiement(3);//non payé
        }
     }else{
                   if (demandePoules1.getTotalNonEncaisse()== null) {
            demandePoules1.setTotalNonEncaisse(total);
        } else {
            BigDecimal totalPaiement = demandePoules1.getTotalNonEncaisse().add(total);
            demandePoules1.setTotalNonEncaisse(totalPaiement);
        }
                   demandePoules1.setEtatPaiement(3);
                   }
          

        demandePoulesFacade.edit(demandePoules1);
        paiementDemandePoules.setPaiement(p);
        edit(paiementDemandePoules);
        return 1;
    }


    public int encaisser(PaiementDemandePoules paiementDemandePoules , BigDecimal total){
        PaiementDemandePoules pd =find(paiementDemandePoules.getId());
        Paiement p = paiementFacade.find(pd.getPaiement().getId());
        if(p.getType()==1){
            return -1;
        }else{
            p.setDateEncaissement(new Date());
            paiementFacade.edit(p);
            DemandePoules d= demandePoulesFacade.find(pd.getDemandePoules().getId());
            if (d.getTotalPaye() == null) {
            d.setTotalPaye(total);
             d.setTotalNonEncaisse(d.getTotalNonEncaisse().subtract(total));
            }else{
            d.setTotalPaye(d.getTotalPaye().add(total));
            d.setTotalNonEncaisse(d.getTotalNonEncaisse().subtract(total));
            }
            if(d.getMontantTotal().compareTo(d.getTotalPaye()) <= 0){
                d.setEtatPaiement(1);
            }else if(d.getMontantTotal().compareTo(d.getTotalPaye()) > 0){
                 d.setEtatPaiement(2);
            }
            demandePoulesFacade.edit(d);
            
            pd.setDemandePoules(d);
            pd.setPaiement(p);
            edit(pd);
            return 1;
        }
      
    }
      public List<DemandePoules> findByCriteriaForChart(Client client, int annee ,int etatPaiement) {
        String requete = "SELECT d FROM DemandePoules d WHERE 1=1 ";
       

         if( annee!=0){
        requete += SearchUtil.addConstraint("d", "annee", "=", annee);
        }
         if( etatPaiement!=0){
        requete += SearchUtil.addConstraint("d", "etatPaiement", "=", etatPaiement);
        }
        if (client != null) {
            requete += SearchUtil.addConstraint("d", "client.id", "=", client.getId());
        }

        System.out.println("haa requeta" + requete);
        List<DemandePoules> list = em.createQuery(requete).getResultList();
        return list;
    }

    
    
    public PaiementDemandePoulesFacade() {
        super(PaiementDemandePoules.class);
    }

}
