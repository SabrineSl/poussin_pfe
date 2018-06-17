package controller;

import bean.Client;
import bean.DemandePoules;
import bean.LivraisonPoules;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.LivraisonPoulesFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import service.DemandePoulesFacade;

@Named("livraisonPoulesController")
@SessionScoped
public class LivraisonPoulesController implements Serializable {

    @EJB
    private service.LivraisonPoulesFacade ejbFacade;
    private List<LivraisonPoules> items = null;
    private List<DemandePoules> demandes = new ArrayList<>();
    private List<DemandePoules> demandes2 = new ArrayList<>();
    private List<DemandePoules> demandesDelai = null;
    private LivraisonPoules selected;
  private DemandePoules demandePoules;
  private Client client;
   private int annee ;
  
  private DemandePoulesFacade demandePoulesFacade;

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
        yAxis.setMax(1000);
    }

    
  
    public BarChartModel chartsDemandeByClient() {
        BarChartModel barChartModel = new BarChartModel();
        ChartSeries c = new ChartSeries();
        c.setLabel("client");
        demandes=ejbFacade.findByCriteriaForChart(getClient(),annee);
        for (int i = 0; i < demandes.size(); i++) {
              BigDecimal montant=demandes.get(i).getQteFemaleLivree();
          
          
            c.set(demandes.get(i).getMois(), montant);
    
           
        }
        ChartSeries clt = new ChartSeries();
        clt.setLabel("client");
        demandes2=ejbFacade.findByCriteriaForChart2(getClient(),annee);
        for (int i = 0; i < demandes2.size(); i++) {
                     BigDecimal montant=demandes2.get(i).getQteMaleLivree();
        
   
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
  
 public void livraison(){
     
     demandePoulesFacade.livraison(selected, demandePoules);
     items=getFacade().findAll();
 }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
 

    public Client getClient() {
        if(client==null){
            client=new Client();
        }
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<DemandePoules> getDemandesDelai() {
        return demandesDelai;
    }

    public void setDemandesDelai(List<DemandePoules> demandesDelai) {
        this.demandesDelai = demandesDelai;
    }
 

    public List<DemandePoules> getDemandes2() {
        return demandes2;
    }

    public void setDemandes2(List<DemandePoules> demandes2) {
        this.demandes2 = demandes2;
    }

    public List<DemandePoules> getDemandes() {
        return demandes;
    }

    public void setDemandes(List<DemandePoules> demandes) {
        this.demandes = demandes;
    }

    public BarChartModel getModel() {
        if(model==null){
            model=new BarChartModel();
        }
        return model;
    }

    public void setModel(BarChartModel model) {
        this.model = model;
    }
 
    public LivraisonPoulesController() {
    }

    public LivraisonPoulesFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(LivraisonPoulesFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public DemandePoulesFacade getDemandePoulesFacade() {
        return demandePoulesFacade;
    }

    public void setDemandePoulesFacade(DemandePoulesFacade demandePoulesFacade) {
        this.demandePoulesFacade = demandePoulesFacade;
    }
    

    public DemandePoules getDemandePoules() {
        if(demandePoules==null){
            demandePoules=new DemandePoules();
        }
        return demandePoules;
    }

    public void setDemandePoules(DemandePoules demandePoules) {
        this.demandePoules = demandePoules;
    }
    

    public LivraisonPoules getSelected() {
        if(selected==null){
            selected=new LivraisonPoules();
        }
        return selected;
    }

    public void setSelected(LivraisonPoules selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LivraisonPoulesFacade getFacade() {
        return ejbFacade;
    }

    public LivraisonPoules prepareCreate() {
        selected = new LivraisonPoules();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LivraisonPoulesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LivraisonPoulesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LivraisonPoulesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<LivraisonPoules> getItems() {
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

    public LivraisonPoules getLivraisonPoules(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<LivraisonPoules> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<LivraisonPoules> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = LivraisonPoules.class)
    public static class LivraisonPoulesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LivraisonPoulesController controller = (LivraisonPoulesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "livraisonPoulesController");
            return controller.getLivraisonPoules(getKey(value));
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
            if (object instanceof LivraisonPoules) {
                LivraisonPoules o = (LivraisonPoules) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LivraisonPoules.class.getName()});
                return null;
            }
        }

    }

}
