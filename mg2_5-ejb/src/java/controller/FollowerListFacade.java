package controller;

import entities.FollowerList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FollowerListFacade extends AbstractFacade<FollowerList> {
    @PersistenceContext(unitName = "mg2_5-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FollowerListFacade() {
        super(FollowerList.class);
    }
    
}
