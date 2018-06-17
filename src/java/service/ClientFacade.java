/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Client;
import bean.DemandePoules;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sabrine
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceContext(unitName = "e_poussinsPU")
    private EntityManager em;

    public Client clone(Client client) {
        Client cloneClient = new Client();
        cloneClient.setTel(client.getTel());
        cloneClient.setNom(client.getNom());
        cloneClient.setEmail(client.getEmail());
        return cloneClient;
    }

    public void addItems(List<Client> clients) {
        for (Client client : clients) {
            create(client);

        }
    }

    public List<DemandePoules> findDemandeByClient(Client client) {
        return em.createQuery("SELECT demande FROM DemandePoules demande WHERE  demande.etatPaiement=2 AND demande.client.id='" + client.getId() + "'").getResultList();
    }

    public List<DemandePoules> findDemande() {
        return em.createQuery("SELECT demande FROM DemandePoules demande WHERE  demande.etatPaiement=2 ").getResultList();
    }

    public List<Client> delai() {
        List<Client> problemes = new ArrayList<>();
        List<DemandePoules> res = findDemande();
        for (int i = 0; i < res.size(); i++) {
            DemandePoules demande = res.get(i);
            int mois1 = getMonthInt(demande.getDateDemande());
            int mois2 = getMonthInt(new Date());
            if (mois2 == mois1 + 1) {
                problemes.add(demande.getClient());
            }

        }
        return problemes;
    }

    public static int getMonthInt(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        return Integer.parseInt(dateFormat.format(date));
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }

}
