package controller;

import entities.PackContent;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PackContentFacade extends AbstractFacade<PackContent> {
    @PersistenceContext(unitName = "mg2_5-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PackContentFacade() {
        super(PackContent.class);
    }
    
}
