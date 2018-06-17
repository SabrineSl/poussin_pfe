/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.SuivisPoussinParGender;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sabrine
 */
@Stateless
public class SuivisPoussinParGenderFacade extends AbstractFacade<SuivisPoussinParGender> {

    @PersistenceContext(unitName = "e_poussinsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SuivisPoussinParGenderFacade() {
        super(SuivisPoussinParGender.class);
    }
    
}
