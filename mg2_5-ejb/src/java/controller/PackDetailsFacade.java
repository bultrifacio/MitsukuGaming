package controller;

import entities.PackDetails;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PackDetailsFacade extends AbstractFacade<PackDetails> {
    @PersistenceContext(unitName = "mg2_5-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PackDetailsFacade() {
        super(PackDetails.class);
    }
    
}
