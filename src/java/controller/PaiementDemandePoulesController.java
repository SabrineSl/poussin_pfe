package controller;

import bean.Client;
import bean.DemandePoules;
import bean.Paiement;
import bean.PaiementDemandePoules;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.PaiementDemandePoulesFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
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
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import service.DemandePoulesFacade;
import service.PaiementFacade;

@Named("paiementDemandePoulesController")
@SessionScoped
public class PaiementDemandePoulesController implements Serializable {

    @EJB
    private service.PaiementDemandePoulesFacade ejbFacade;
    private List<PaiementDemandePoules> items = null;
    private List<PaiementDemandePoules> paiementDemandes = null;
    private List<Paiement> itemsPaiement = null;
    private List<DemandePoules> demandes = null;
    private List<DemandePoules> demandesChart = null;
    private PaiementDemandePoules selected;
    private PaiementDemandePoules ligne;
    private BigDecimal total;
    private int type;
    private Date datePaiement;
    private Date dateEncaissement;
    @EJB
    private PaiementFacade paiementFacade;
    @EJB
    private DemandePoulesFacade demandePoulesFacade;
    private LineChartModel lineModel2;
    private Client client ;
    private int annee;
    private int etatPaiement;

    @PostConstruct
    public void init() {
        createLineModels();
    }

    private void createLineModels() {

        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Quantité 'male' & 'female' vendu par mois :");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Mois"));
       Axis yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Quantité");
        yAxis.setMin(0);
        yAxis.setMax(2000);
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();

        ChartSeries male = new ChartSeries();
       
           male.setLabel("male");
        demandesChart=ejbFacade.findByCriteriaForChart(getClient(),annee,etatPaiement);
        for (int i = 0; i < demandesChart.size(); i++) {
            BigDecimal montant=demandesChart.get(i).getQteMale();
            male.set(demandesChart.get(i).getMois(), montant);
        }

        ChartSeries female = new ChartSeries();
               female.setLabel("female");
        demandesChart=ejbFacade.findByCriteriaForChart(getClient(),annee,etatPaiement);
        for (int i = 0; i < demandesChart.size(); i++) {
            BigDecimal montant=demandesChart.get(i).getQteFemale();
            female.set(demandesChart.get(i).getMois(), montant);
        }

        model.addSeries(male);
        model.addSeries(female);

        return model;
    }
       public void findByCriteriaForChart2(){
          init();
     RequestContext context = RequestContext.getCurrentInstance();
     context.update("chartForm");
     }

    public LineChartModel getLineModel1() {
        return lineModel2;
    }

    public void rowSelection(PaiementDemandePoules d){
    ligne=d;
    System.out.println(d.toString());
}
    
    public void paiementDemande(DemandePoules d) {
        ejbFacade.PaiementDemande(selected, d, total, type, dateEncaissement);

    }

    public void paiementDemande() {
        ejbFacade.PaiementDemande(selected, selected.getDemandePoules(), total, type, dateEncaissement);
        items = getFacade().findAll();
            FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("info ", "Paiement enregistré avec succès"));
  
    }
        public void reffresh(){
          paiementDemande();
     RequestContext context = RequestContext.getCurrentInstance();
     context.update("form1");
     }

    public void findByDemande() {
        demandes = demandePoulesFacade.findById(selected.getDemandePoules());

    }

    public void encaisser(PaiementDemandePoules p) {
        ejbFacade.encaisser(p, total);
        items = getFacade().findAll();
    }

    public void encaisser() {
        ejbFacade.encaisser(selected, total);
        items = getFacade().findAll();

    }

    public List<DemandePoules> getDemandesChart() {
        return demandesChart;
    }

    public void setDemandesChart(List<DemandePoules> demandesChart) {
        this.demandesChart = demandesChart;
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

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getEtatPaiement() {
        return etatPaiement;
    }

    public void setEtatPaiement(int etatPaiement) {
        this.etatPaiement = etatPaiement;
    }

    public PaiementDemandePoules getLigne() {
        if(ligne==null){
            ligne=new PaiementDemandePoules();
        }
        return ligne;
    }

    public void setLigne(PaiementDemandePoules ligne) {
        this.ligne = ligne;
    }
    

    public DemandePoulesFacade getDemandePoulesFacade() {
        return demandePoulesFacade;
    }

    public LineChartModel getLineModel2() {
        return lineModel2;
    }

    public void setLineModel2(LineChartModel lineModel2) {
        this.lineModel2 = lineModel2;
    }
    

    public void setDemandePoulesFacade(DemandePoulesFacade demandePoulesFacade) {
        this.demandePoulesFacade = demandePoulesFacade;
    }

    public PaiementFacade getPaiementFacade() {
        return paiementFacade;
    }

    public void setPaiementFacade(PaiementFacade paiementFacade) {
        this.paiementFacade = paiementFacade;
    }

    public List<DemandePoules> getDemandes() {
        return demandes;
    }

    public void setDemandes(List<DemandePoules> demandes) {
        this.demandes = demandes;
    }

    public List<Paiement> getItemsPaiement() {
        return itemsPaiement;
    }

    public void setItemsPaiement(List<Paiement> itemsPaiement) {
        this.itemsPaiement = itemsPaiement;
    }

    public List<PaiementDemandePoules> getPaiementDemandes() {
        return paiementDemandes;
    }

    public void setPaiementDemandes(List<PaiementDemandePoules> paiementDemandes) {
        this.paiementDemandes = paiementDemandes;
    }

    public PaiementDemandePoulesController() {
    }

    public PaiementDemandePoules getSelected() {
        if (selected == null) {
            selected = new PaiementDemandePoules();
        }
        return selected;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public Date getDateEncaissement() {
        return dateEncaissement;
    }

    public void setDateEncaissement(Date dateEncaissement) {
        this.dateEncaissement = dateEncaissement;
    }

    public PaiementDemandePoulesFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PaiementDemandePoulesFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public void setSelected(PaiementDemandePoules selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PaiementDemandePoulesFacade getFacade() {
        return ejbFacade;
    }

    public PaiementDemandePoules prepareCreate() {
        selected = new PaiementDemandePoules();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PaiementDemandePoulesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PaiementDemandePoulesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PaiementDemandePoulesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PaiementDemandePoules> getItems() {
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

    public PaiementDemandePoules getPaiementDemandePoules(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<PaiementDemandePoules> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PaiementDemandePoules> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PaiementDemandePoules.class)
    public static class PaiementDemandePoulesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PaiementDemandePoulesController controller = (PaiementDemandePoulesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "paiementDemandePoulesController");
            return controller.getPaiementDemandePoules(getKey(value));
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
            if (object instanceof PaiementDemandePoules) {
                PaiementDemandePoules o = (PaiementDemandePoules) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PaiementDemandePoules.class.getName()});
                return null;
            }
        }

    }

}
