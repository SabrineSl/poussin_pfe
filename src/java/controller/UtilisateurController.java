package controller;

import bean.Utilisateur;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import service.UtilisateurFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

@Named("utilisateurController")
@SessionScoped
public class UtilisateurController implements Serializable {

    @EJB
    private service.UtilisateurFacade ejbFacade;
    private List<Utilisateur> items = null;
    private List<Utilisateur> utilisateurItems = new ArrayList<>();
    private Utilisateur selected;
    
       public Utilisateur init() {
        Utilisateur ut = (Utilisateur) SessionUtil.getAttribute("u");
        return ut;
    }
    
           public String connexion() {
        int res = ejbFacade.connexion1(selected);
        if (res > 0) {
            SessionUtil.setAttribute("u", selected);
//            selected=null;
//            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO",""+selected.getLogin()));
            return "/utilisateur/test";
             
        } else { 
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,"ERROR",""+selected.getLogin()));
            return "null";
        }
    }
    
    
     public void addView() {
        Utilisateur cloneUser = ejbFacade.clone(selected);
        if (cloneUser != null) {
            utilisateurItems.add(cloneUser);
        }
    }

    public void addBd() {
        ejbFacade.addItems(utilisateurItems);
    }

    public UtilisateurFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(UtilisateurFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Utilisateur> getUtilisateurItems() {
        return utilisateurItems;
    }

    public void setUtilisateurItems(List<Utilisateur> utilisateurItems) {
        this.utilisateurItems = utilisateurItems;
    }
    
    

    public UtilisateurController() {
    }

    public Utilisateur getSelected() {
        if(selected==null){
            selected=new Utilisateur();
        }
        return selected;
    }

    public void setSelected(Utilisateur selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UtilisateurFacade getFacade() {
        return ejbFacade;
    }

    public Utilisateur prepareCreate() {
        selected = new Utilisateur();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UtilisateurCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UtilisateurUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UtilisateurDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Utilisateur> getItems() {
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

    public Utilisateur getUtilisateur(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Utilisateur> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Utilisateur> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Utilisateur.class)
    public static class UtilisateurControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UtilisateurController controller = (UtilisateurController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "utilisateurController");
            return controller.getUtilisateur(getKey(value));
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
            if (object instanceof Utilisateur) {
                Utilisateur o = (Utilisateur) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Utilisateur.class.getName()});
                return null;
            }
        }

    }

}
