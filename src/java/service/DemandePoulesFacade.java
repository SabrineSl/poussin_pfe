/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Client;
import bean.DemandePoules;
import bean.LivraisonPoules;
import bean.Paiement;
import bean.PaiementDemandePoules;
import controller.util.EmailUtil;
import controller.util.PdfUtil;
import controller.util.SearchUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author sabrine
 */
@Stateless
public class DemandePoulesFacade extends AbstractFacade<DemandePoules> {

    @PersistenceContext(unitName = "e_poussinsPU")
    private EntityManager em;
    @EJB
    LivraisonPoulesFacade livraisonPoulesFacade;

    public void demmander(DemandePoules demmande) {
        DemandePoules d = new DemandePoules();
        int annee = new Date().getYear() + 1900;
        d.setId(generate("DemandePoules", "id"));
        d.setReference("D-" + annee + "-" + d.getId());
        d.setDateDemande(demmande.getDateDemande());
        d.setQteMale(demmande.getQteMale());
        d.setQteFemale(demmande.getQteFemale());
        d.setClient(demmande.getClient());
        d.setMois(getMonthInt(demmande.getDateDemande()));
        d.setAnnee(getYearInt(demmande.getDateDemande()));
        d.setQteTotal(demmande.getQteFemale().add(demmande.getQteMale()));
        BigDecimal prixMale = demmande.getQteMale().multiply(new BigDecimal(15));
        BigDecimal prixFemal = demmande.getQteFemale().multiply(new BigDecimal(13));
        d.setMontantTotal(prixMale.add(prixFemal));

        create(d);

    }

    public int convert(DemandePoules demandePoules) {
        int mois = getMonthInt(demandePoules.getDateDemande());
        return mois;
    }
// public void generateCode(DemandePoules d) {
//        int annee = new Date().getYear() + 1900;
//        commande.setIndice(generate("Commande", "indice"));
//        commande.setReference("C-" + annee + "-" + commande.getIndice());
//        System.out.println("hhh==>" + commande.getReference());
//        System.out.println("hhh==>" + commande.getIndice());
//    }

    public DemandePoules cloneDemmande(DemandePoules demmande) {
        DemandePoules d = new DemandePoules();

        d.setDateDemande(demmande.getDateDemande());
        d.setQteMale(demmande.getQteMale());
        d.setQteFemale(demmande.getQteFemale());
        d.setClient(demmande.getClient());
        d.setQteTotal(demmande.getQteFemale().add(demmande.getQteMale()));
        BigDecimal prixMale = demmande.getQteMale().multiply(new BigDecimal(15));
        BigDecimal prixFemal = demmande.getQteFemale().multiply(new BigDecimal(13));
        d.setMontantTotal(prixMale.add(prixFemal));
        return d;

    }
    
    

    public int livraison(LivraisonPoules livraison, DemandePoules demandePoules) {

        DemandePoules demandePoules1 = find(demandePoules.getId());
        if (demandePoules1.getQteFemaleLivree() == null || demandePoules1.getQteMaleLivree() == null) {
            demandePoules1.setQteFemaleLivree(livraison.getQteFemale());
            demandePoules1.setQteMaleLivree(livraison.getQteMale());

        } else {

            BigDecimal sum = livraison.getQteFemale().add(demandePoules1.getQteFemaleLivree());
            demandePoules1.setQteFemaleLivree(sum);
            BigDecimal sum2 = demandePoules1.getQteMaleLivree().add(livraison.getQteMale());
            demandePoules1.setQteMaleLivree(sum2);
        }
        BigDecimal qteTotalLivre = demandePoules1.getQteFemaleLivree().add(demandePoules1.getQteMaleLivree());
        if (demandePoules.getQteTotal().compareTo(qteTotalLivre) > 0) {
            demandePoules1.setEtatLivraison(2);
        } else if (demandePoules.getQteTotal().compareTo(qteTotalLivre) == 0) {
            demandePoules1.setEtatLivraison(1);
        } else {
            demandePoules1.setEtatLivraison(3);
        }
        edit(demandePoules1);

        LivraisonPoules l = new LivraisonPoules();
        l.setQteMale(livraison.getQteMale());
        l.setQteFemale(livraison.getQteFemale());
        l.setQteFemale(livraison.getQteFemale());
        l.setDateLivraison(livraison.getDateLivraison());
        l.setTotal(livraison.getQteFemale().add(livraison.getQteMale()));
        l.setDemandePoules(demandePoules1);
        livraisonPoulesFacade.create(l);

        return 1;

    }

    public void addItems(List<DemandePoules> demmandes) {
        for (DemandePoules demmande : demmandes) {
            create(demmande);

        }
    }

    public List<Client> findClientBydemande(DemandePoules demande) {
        return em.createQuery("SELECT c FROM Client c  WHERE c.id='" + demande.getClient().getId() + " '").getResultList();
    }

    public int annuler(DemandePoules demande) {
        return em.createQuery("DELETE FROM DemandePoules d WHERE d.id='" + demande.getId() + " '").executeUpdate();
    }

    public List<DemandePoules> findById(DemandePoules demande) {
        return em.createQuery("SELECT d FROM DemandePoules d WHERE d.id='" + demande.getId() + " '").getResultList();
    }
    public List<PaiementDemandePoules> findPaiementDemande(DemandePoules demande) {
        return em.createQuery("SELECT p FROM PaiementDemandePoules p WHERE p.demande.id='" + demande.getId() + " '").getResultList();
       
    }

    public List<DemandePoules> findByCriteria(Client client, Date dateMin, Date dateMax, int etatPaiement) {
        String requete = "SELECT d FROM DemandePoules d WHERE 1=1 ";
        requete += SearchUtil.addConstraintMinMaxDate("d", "dateDemande", dateMin, dateMax);
        requete += SearchUtil.addConstraint("d", "etatPaiement", "=", etatPaiement);
        if (client != null) {
            requete += SearchUtil.addConstraint("d", "client.id", "=", client.getId());
        }

        System.out.println("haa requeta" + requete);
        List<DemandePoules> list = em.createQuery(requete).getResultList();
        return list;
    }

    
    
    public List<DemandePoules> findByCriteriaForChart(Client client, int annee) {
        String requete = "SELECT d FROM DemandePoules d WHERE 1=1 ";
       
        requete += SearchUtil.addConstraint("d", "etatPaiement", "=", 1);
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

    public void generateCode(DemandePoules demande) {
        int annee = new Date().getYear() + 1900;
        demande.setId(generate("DemandePoules", "id"));
        demande.setReference("D-" + annee + "-" + demande.getId());
        System.out.println("hhh==>" + demande.getReference());
        System.out.println("hhh==>" + demande.getId());
    }

    public List<DemandePoules> findByClient(Client client) {
        String requete = "SELECT d FROM DemandePoules d WHERE d.client.id='" + client.getId() + "'";
        System.out.println("haa requeta" + requete);
        return em.createQuery(requete).getResultList();

    }

    public List<DemandePoules> findByCriteriaForChart2(Client client, int annee) {
        String requete = "SELECT d FROM DemandePoules d WHERE 1=1 ";
        requete += SearchUtil.addConstraint("d", "etatPaiement", "=", 2);
        requete += SearchUtil.addConstraint("d", "annee", "=", annee);
        if (client != null) {
            requete += SearchUtil.addConstraint("d", "client.id", "=", client.getId());
        }
        System.out.println("haa requeta" + requete);
        List<DemandePoules> list = em.createQuery(requete).getResultList();
        return list;
    }

    public int findByClientChart(Client client) {
        String requete = "SELECT d FROM DemandePoules d WHERE d.client.id='" + client.getId() + "'";
        System.out.println("haa requeta" + requete);
        return em.createQuery(requete).getResultList().size();

    }

    public List<DemandePoules> findByClientChartEtat(Client client) {
        String requete = "SELECT d FROM DemandePoules d WHERE d.client.id='" + client.getId() + "' AND  d.etatPaiement=1";
        System.out.println("haa requeta" + requete);
        return em.createQuery(requete).getResultList();

    }

    public List<DemandePoules> findByClientChartEtat2(Client client) {
        String requete = "SELECT d FROM DemandePoules d WHERE d.client.id='" + client.getId() + "' AND  d.etatPaiement=2";
        System.out.println("haa requeta" + requete);
        return em.createQuery(requete).getResultList();

    }

    public List<DemandePoules> findListe(DemandePoules demande) {
        String requete = "SELECT d FROM DemandePoules d WHERE d.id='" + demande.getId() + "'";
        System.out.println("haa requeta" + requete);
        return em.createQuery(requete).getResultList();

    }
    
        public List<PaiementDemandePoules> findPaiementDemandePoules(DemandePoules demande) {
        String requete = "SELECT p FROM PaiementDemandePoules p WHERE p.demandePoules.id='" + demande.getId() + "' AND  p.paiement.type=2";
        System.out.println("haa requeta" + requete);
        return em.createQuery(requete).getResultList();

    }

    public List<DemandePoules> findById(Long id) {
        return em.createQuery("SELECT demande FROM DemandePoules demande WHERE demande.id='" + id + "'").getResultList();
    }
      public List<DemandePoules> findById2(String reference) {
        return em.createQuery("SELECT demande FROM DemandePoules demande WHERE demande.reference='" + reference + "'").getResultList();
    }
    public List<DemandePoules> findByD(DemandePoules demande) {
        return em.createQuery("SELECT d FROM DemandePoules d WHERE d.id='" + demande.getId() + "'").getResultList();
    }
    public List<DemandePoules> findById3(DemandePoules demande) {
        return em.createQuery("SELECT demande FROM DemandePoules demande WHERE demande.id='" + demande.getId() + "'").getResultList();
    }

    public DemandePoules findById2(Long id) {
        return (DemandePoules) em.createQuery("SELECT demande FROM DemandePoules demande WHERE demande.id='" + id + "'").getSingleResult();

    }

    public void generatePdf(Long idDemande) throws JarException, IOException, JRException {
        Map<String, Object> params = new HashMap<>();
        params.put("client", "SALSIL");
        PdfUtil.generatePdf(findById(idDemande), params, "recuDemande3", "/jasper/recuDemande.jasper");
    }

    public void generatePdfPaiement(DemandePoules demande) throws JarException, IOException, JRException {
        Map<String, Object> params = new HashMap<>();
        params.put("client", "SALSIL");
  
        params.put("total restant", "");
        PdfUtil.generatePdf(findById3(demande), params, "recuPaiement", "/jasper/recuPaiement.jasper");
    }

    public BigDecimal calcul(DemandePoules d) {
        BigDecimal total=d.getTotalPaye();
        if(total==null){
             BigDecimal montantRestant = d.getMontantTotal();
             return montantRestant;
        }
        BigDecimal montantRestant = d.getMontantTotal().subtract(total);
        return montantRestant;
    }

    public BigDecimal calculNonLivré(DemandePoules d) {
        BigDecimal totalLivré = d.getQteMaleLivree().add(d.getQteFemaleLivree());
        if(totalLivré==null){
             BigDecimal qteNonLivré = totalLivré;
              return qteNonLivré;
        }
        BigDecimal qteNonLivré = d.getQteTotal().subtract(totalLivré);
        return qteNonLivré;
    }

    public void generate(List<DemandePoules> demandePoules) throws JarException, IOException, JRException {
        Map<String, Object> params = new HashMap<>();
        params.put("client", "SALSIL");
        PdfUtil.generatePdf(demandePoules, params, "recuDemande6", "/jasper/recuDemande.jasper");
    }

    public void sendMail(String from, String password, String message, String to, String subject, String fileAttachment) throws MessagingException {
        EmailUtil.sendMail(from, password, message, to, subject, fileAttachment);
    }

    public List<DemandePoules> findDemandeByClient(Client client) {
        return em.createQuery("SELECT demande FROM DemandePoules demande WHERE  demande.etatPaiement=2 AND demande.client.id='" + client.getId() + "'").getResultList();
    }

    public List<DemandePoules> findDemande() {
        return em.createQuery("SELECT demande FROM DemandePoules demande WHERE  demande.etatPaiement=2 ").getResultList();
    }

    public List<LivraisonPoules> findLivraison(DemandePoules demandePoules) {
        return em.createQuery("SELECT l FROM LivraisonPoules l WHERE  l.demandePoules.id='" + demandePoules.getId() + "'").getResultList();
    }

    public List<Paiement> findPaiement(DemandePoules demandePoules) {
        String rqt = "SELECT p.paiement FROM PaiementDemandePoules p WHERE  p.demandePoules.id='" + demandePoules.getId() + "'";
        System.out.println(rqt);
        return em.createQuery(rqt).getResultList();
    }

    public BigDecimal sumMale(Client client) {
        String requete = "SELECT sum(d.qteMale) FROM DemandePoules d WHERE d.client.id='" + client.getId() + "'";
        System.out.println("haa requeta" + requete);
        return (BigDecimal) em.createQuery(requete).getSingleResult();
    }

    public BigDecimal sumFemale(Client client) {
        String requete = "SELECT sum(d.qteFemale) FROM DemandePoules d WHERE d.client.id='" + client.getId() + "'";
        System.out.println("haa requeta" + requete);
        return (BigDecimal) em.createQuery(requete).getSingleResult();
    }

    public int paye(Client client) {
        String requete = "SELECT d FROM DemandePoules d WHERE d.client.id='" + client.getId() + "' AND d.etatPaiement=1";
        System.out.println("haa requeta" + requete);
        return em.createQuery(requete).getResultList().size();
    }

    public int nonPaye(Client client) {
        String requete = "SELECT d FROM DemandePoules d WHERE d.client.id='" + client.getId() + "'AND d.etatPaiement=2";
        System.out.println("haa requeta" + requete);
        return em.createQuery(requete).getResultList().size();
    }

    public int enCours(Client client) {
        String requete = "SELECT d FROM DemandePoules d WHERE d.client.id='" + client.getId() + "'AND d.etatPaiement=3";
        System.out.println("haa requeta" + requete);
        return em.createQuery(requete).getResultList().size();
    }

    public List<DemandePoules> delai(Client client) {
        List<DemandePoules> problemes = new ArrayList<>();
        List<DemandePoules> res = findDemandeByClient(client);
        for (int i = 0; i < res.size(); i++) {
            DemandePoules demande = res.get(i);
            int mois1 = getMonthInt(demande.getDateDemande());
            int mois2 = getMonthInt(new Date());
            if (mois2 > mois1) {
                problemes.add(demande);
            }

        }
        return problemes;
    }

    public List<DemandePoules> delaiAdmin() {
        List<DemandePoules> problemes = new ArrayList<>();
        List<DemandePoules> res = findDemande();
        for (int i = 0; i < res.size(); i++) {
            DemandePoules demande = res.get(i);
            int mois1 = getMonthInt(demande.getDateDemande());
            int mois2 = getMonthInt(new Date());
            if (mois2 > mois1) {
                problemes.add(demande);
            }

        }
        return problemes;
    }

    public static int getMonthInt(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        return Integer.parseInt(dateFormat.format(date));
    }

    public static int getYearInt(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YY");
        return Integer.parseInt(dateFormat.format(date));
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandePoulesFacade() {
        super(DemandePoules.class);
    }

}
