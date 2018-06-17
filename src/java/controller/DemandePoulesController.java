package controller;

import bean.Client;
import bean.DemandePoules;
import bean.LivraisonPoules;
import bean.Paiement;
import bean.PaiementDemandePoules;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import java.io.IOException;
import service.DemandePoulesFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.jar.JarException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.mail.MessagingException;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import service.ClientFacade;
import service.LivraisonPoulesFacade;

@Named("demandePoulesController")
@SessionScoped
public class DemandePoulesController implements Serializable {

    @EJB
    private service.DemandePoulesFacade ejbFacade;
    private List<DemandePoules> items = null;
    @EJB
    private LivraisonPoulesFacade livraisonPoulesFacade;
    @EJB
    private ClientFacade clientFacade;

    private DemandePoules selected;
    private DemandePoules selected2;
    private DemandePoules ligne;
    private LivraisonPoules livraisonPoules;
    private Client client;
    private List<DemandePoules> demandes = new ArrayList<>();
    private List<DemandePoules> demandesPaiement = new ArrayList<>();
    private List<DemandePoules> demandesLivraison = new ArrayList<>();
    private List<DemandePoules> demandesEncaissement = new ArrayList<>();
    private List<DemandePoules> demandes2 = new ArrayList<>();
    private List<DemandePoules> demandesDelai = new ArrayList<>();
    private List<LivraisonPoules> livraisons = new ArrayList<>();
    private List<LivraisonPoules> AddLivraisons = null;
    private List<PaiementDemandePoules> paiementDemandePoules = null;
    private List<PaiementDemandePoules> paiementDemandePoules2 = null;
    private int annee ;

    private List<Paiement> paiement = new ArrayList<>();
   
    private List<DemandePoules> cloneDemmandes = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();

         private BarChartModel model;
   @PostConstruct
    public void init() {
        createAnimatedModels();
    }
     public void createAnimatedModels() {
        model = chartsDemandeByClient();
        model.setTitle("consommation par mois ");
        model.setAnimate(true);
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10000);
    }

    
  
    public BarChartModel chartsDemandeByClient() {
        BarChartModel barChartModel = new BarChartModel();
        ChartSeries c = new ChartSeries();
        c.setLabel("client");
        demandes=ejbFacade.findByCriteriaForChart(getClient(),annee);
        for (int i = 0; i < demandes.size(); i++) {
            BigDecimal montant=demandes.get(i).getMontantTotal();
            c.set(demandes.get(i).getMois(), montant);
        }
        ChartSeries clt = new ChartSeries();
        clt.setLabel("client");
        demandes2=ejbFacade.findByCriteriaForChart2(getClient(),annee);
        for (int i = 0; i < demandes2.size(); i++) {
            BigDecimal montant=demandes2.get(i).getTotalPaye();
            clt.set(demandes2.get(i).getMois(), montant);
        }
      
        barChartModel.addSeries(c);
        barChartModel.addSeries(clt);
        return barChartModel;
    }

     public void findByCriteriaForChart2(){
          init();
     RequestContext context = RequestContext.getCurrentInstance();
     context.update("chartForm");
     }
     
     public void delai(){
         demandesDelai=ejbFacade.delaiAdmin();
         
     }

    public BarChartModel getModel() {
         if (model == null) {
            model = new BarChartModel();
        }
        return model;
    }

    public void setModel(BarChartModel model) {
        this.model = model;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
    
    
    
    
    


  

        
        
        
    
    
    
    
    
    
    
    public void livraison(){
        ejbFacade.livraison(livraisonPoules, selected);
        AddLivraisons=getLivraisonPoulesFacade().findAll();

    }
    public void detail(DemandePoules demandePoules){
       livraisons= ejbFacade.findLivraison(demandePoules);
         paiement= ejbFacade.findPaiement(demandePoules);
    }
  
    
    private Date dateMin;
    private Date dateMax;
    
    private int paye;
    private int nonPaye;
    private int enCours;

    private BigDecimal montantRestant;
    private BigDecimal qteNonLivré;
    private BigDecimal qteMale;
    private BigDecimal qteFemale;

    private String from;
    private String password;
    private String message;
    private String to;
    private String subject;
    private String fileAttachment;

    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private PieChartModel pieModel3;




//    private void createPieModels() {
//        createPieModel1();
//        createPieModel2();
//     
//    }
//    
//    
//
//    private void createPieModel1() {
//        pieModel1 = new PieChartModel();
//
//        pieModel1.set("male", qteMale);
//        pieModel1.set("female", qteFemale);
//
//        pieModel1.setTitle("demande par genre");
//        pieModel1.setLegendPosition("w");
//
//    }
//    
//
//    private void createPieModel2() {
//        pieModel2 = new PieChartModel();
//
//        pieModel2.set("paye", paye);
//        pieModel2.set("nonPaye", nonPaye);
//        pieModel2.set("en cours de paiement", nonPaye);
//
//        pieModel2.setTitle("etat de paiement des demmandes");
//        pieModel2.setLegendPosition("e");
//        pieModel2.setShowDataLabels(true);
//    }
//    
//
//
//    public void qtMale() {
//        qteMale = ejbFacade.sumMale(client);
//        qteFemale = ejbFacade.sumFemale(client);
//           paye=ejbFacade.paye(client);
//        nonPaye=ejbFacade.nonPaye(client);
//        enCours=ejbFacade.enCours(client);
//       
//    }
    public void cloneDemmande(){
        DemandePoules cloneDemmande =ejbFacade.cloneDemmande(selected);
        if(cloneDemmande!=null){
             cloneDemmandes.add(cloneDemmande);  
        }
     
    }
    public void addBd(){
        ejbFacade.addItems(cloneDemmandes);
    }




    public void annuler(DemandePoules demande) {
        ejbFacade.remove(demande);
        items.remove(items.indexOf(demande));
    }

    public List<DemandePoules> getDemandesDelai() {
        return demandesDelai;
    }

    public void setDemandesDelai(List<DemandePoules> demandesDelai) {
        this.demandesDelai = demandesDelai;
    }

public void rowSelection(DemandePoules d){
    ligne=d;
    System.out.println(d.toString());
}

    public void demmander() {
        ejbFacade.demmander(selected);
        items=getFacade().findAll();
          FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("info ", "demande enregistré avec succès"));
       
          
    }
        
      public void searchPaiement(){
          paiementDemandePoules=ejbFacade.findPaiementDemande(selected);
      }
        
  
    
    public void findClient(DemandePoules dp){
        clients =ejbFacade.findClientBydemande(dp);
    }
    
    
    

    public void calculRestant(DemandePoules demande) {
        montantRestant = ejbFacade.calcul(demande);

    }
    public void qteNonLivré(DemandePoules demande) {
        qteNonLivré = ejbFacade.calculNonLivré(demande);

    }

    public void search() {
        demandes = ejbFacade.findByCriteria(client, dateMin, dateMax,  selected.getEtatPaiement());
    }
    
    public List<DemandePoules> searchId(){
       return  ejbFacade.findById2(selected.getReference());
     
    }
    public List<PaiementDemandePoules> findPaiementDemande(){
   return ejbFacade.findPaiementDemandePoules(selected);
    
    }
    public void up(){
        demandes=getFacade().findById(selected);
    }

    public void generate(DemandePoules demande) throws JarException, IOException, JRException {

        ejbFacade.generatePdf(demande.getId());
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generatePaiement(PaiementDemandePoules Paiementdemande) throws JarException, IOException, JRException {

        ejbFacade.generatePdfPaiement(Paiementdemande.getDemandePoules());
        FacesContext.getCurrentInstance().responseComplete();

    }


    

    public void sendMail() throws MessagingException {
        System.out.println("hhhhhhhhh");
        ejbFacade.sendMail(from, password, message, to, subject, fileAttachment);
    }

    public LivraisonPoules getLivraisonPoules() {
        if(livraisonPoules==null){
            livraisonPoules=new  LivraisonPoules();
        }
        return livraisonPoules;
    }

    public void setLivraisonPoules(LivraisonPoules livraisonPoules) {
        this.livraisonPoules = livraisonPoules;
    }
    

    public List<DemandePoules> getCloneDemmandes() {
        return cloneDemmandes;
    }

    public void setCloneDemmandes(List<DemandePoules> cloneDemmandes) {
        this.cloneDemmandes = cloneDemmandes;
    }
    

    public List<Paiement> getPaiement() {
        return paiement;
    }

    public void setPaiement(List<Paiement> paiement) {
        this.paiement = paiement;
    }

    public List<PaiementDemandePoules> getPaiementDemandePoules() {
        return paiementDemandePoules;
    }

    public void setPaiementDemandePoules(List<PaiementDemandePoules> paiementDemandePoules) {
        this.paiementDemandePoules = paiementDemandePoules;
    }

    public DemandePoules getLigne() {
        if(ligne==null){
            ligne=new DemandePoules();
        }
        return ligne;
    }

    public void setLigne(DemandePoules ligne) {
        this.ligne = ligne;
    }

    public List<PaiementDemandePoules> getPaiementDemandePoules2() {
        return paiementDemandePoules2;
    }

    public void setPaiementDemandePoules2(List<PaiementDemandePoules> paiementDemandePoules2) {
        this.paiementDemandePoules2 = paiementDemandePoules2;
    }
    
    
    
    
    
    

  
    public BigDecimal getQteFemale() {
        return qteFemale;
    }

    public void setQteFemale(BigDecimal qteFemale) {
        this.qteFemale = qteFemale;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    public Date getDateMin() {
        return dateMin;
    }

    public void setDateMin(Date dateMin) {
        this.dateMin = dateMin;
    }

    public Date getDateMax() {
        return dateMax;
    }

    public List<LivraisonPoules> getAddLivraisons() {
         if (AddLivraisons == null) {
            AddLivraisons = getLivraisonPoulesFacade().findAll();
        }
        return AddLivraisons;
    }

    public void setAddLivraisons(List<LivraisonPoules> AddLivraisons) {
        this.AddLivraisons = AddLivraisons;
    }

    public BigDecimal getQteNonLivré() {
        return qteNonLivré;
    }

    public ClientFacade getClientFacade() {
        return clientFacade;
    }

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    public void setQteNonLivré(BigDecimal qteNonLivré) {
        this.qteNonLivré = qteNonLivré;
    }
    

    public void setDateMax(Date dateMax) {
        this.dateMax = dateMax;
    }

    public int getPaye() {
        return paye;
    }

    public void setPaye(int paye) {
        this.paye = paye;
    }

    public int getNonPaye() {
        return nonPaye;
    }

    public LivraisonPoulesFacade getLivraisonPoulesFacade() {
        return livraisonPoulesFacade;
    }

    public void setLivraisonPoulesFacade(LivraisonPoulesFacade livraisonPoulesFacade) {
        this.livraisonPoulesFacade = livraisonPoulesFacade;
    }
    

    public void setNonPaye(int nonPaye) {
        this.nonPaye = nonPaye;
    }

    public int getEnCours() {
        return enCours;
    }

    public void setEnCours(int enCours) {
        this.enCours = enCours;
    }

    public List<LivraisonPoules> getLivraisons() {
        return livraisons;
    }

    public void setLivraisons(List<LivraisonPoules> livraisons) {
        this.livraisons = livraisons;
    }
    
    

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    
    


    public BigDecimal getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(BigDecimal montantRestant) {
        this.montantRestant = montantRestant;
    }



    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<DemandePoules> getDemandes2() {
        return demandes2;
    }

    public void setDemandes2(List<DemandePoules> demandes2) {
        this.demandes2 = demandes2;
    }
    

    public String getSubject() {
        return subject;
    }
    

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFileAttachment() {
        return fileAttachment;
    }

    public void setFileAttachment(String fileAttachment) {
        this.fileAttachment = fileAttachment;
    }

    public DemandePoulesController() {
    }

    public DemandePoules getSelected2() {
           if (selected2 == null) {
            selected2 = new DemandePoules();
        }
        return selected2;
    }

    public void setSelected2(DemandePoules selected2) {
        this.selected2 = selected2;
    }

    public DemandePoules getSelected() {
        if (selected == null) {
            selected = new DemandePoules();
        }
        return selected;
    }

    public DemandePoulesFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(DemandePoulesFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<DemandePoules> getDemandes() {
        return demandes;
    }

    public void setDemandes(List<DemandePoules> demandes) {
        this.demandes = demandes;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public void setPieModel2(PieChartModel pieModel2) {
        this.pieModel2 = pieModel2;
    }

    public PieChartModel getPieModel3() {
        return pieModel3;
    }

    public void setPieModel3(PieChartModel pieModel3) {
        this.pieModel3 = pieModel3;
    }
    
    

    public Client getClient() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getQteMale() {
        return qteMale;
    }

    public void setQteMale(BigDecimal qteMale) {
        this.qteMale = qteMale;
    }

    public List<DemandePoules> getDemandesPaiement() {
        return demandesPaiement;
    }

    public void setDemandesPaiement(List<DemandePoules> demandesPaiement) {
        this.demandesPaiement = demandesPaiement;
    }

    public List<DemandePoules> getDemandesLivraison() {
        return demandesLivraison;
    }

    public void setDemandesLivraison(List<DemandePoules> demandesLivraison) {
        this.demandesLivraison = demandesLivraison;
    }

    public List<DemandePoules> getDemandesEncaissement() {
        return demandesEncaissement;
    }

    public void setDemandesEncaissement(List<DemandePoules> demandesEncaissement) {
        this.demandesEncaissement = demandesEncaissement;
    }
    

    public void setSelected(DemandePoules selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandePoulesFacade getFacade() {
        return ejbFacade;
    }

    public DemandePoules prepareCreate() {
        selected = new DemandePoules();
        initializeEmbeddableKey();
        return selected;
    }
    

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandePoulesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandePoulesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandePoulesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DemandePoules> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public DemandePoules getDemandePoules(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DemandePoules> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DemandePoules> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DemandePoules.class)
    public static class DemandePoulesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandePoulesController controller = (DemandePoulesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandePoulesController");
            return controller.getDemandePoules(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DemandePoules) {
                DemandePoules o = (DemandePoules) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DemandePoules.class.getName()});
                return null;
            }
        }

    }

}
