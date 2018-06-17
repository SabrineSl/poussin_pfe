/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.EstimationOeufItem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sabrine
 */
@Stateless
public class EstimationOeufItemFacade extends AbstractFacade<EstimationOeufItem> {

    @PersistenceContext(unitName = "e_poussinsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstimationOeufItemFacade() {
        super(EstimationOeufItem.class);
    }
    
}
