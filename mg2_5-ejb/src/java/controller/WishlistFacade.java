package controller;

import entities.Wishlist;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class WishlistFacade extends AbstractFacade<Wishlist> {
    @PersistenceContext(unitName = "mg2_5-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WishlistFacade() {
        super(Wishlist.class);
    }
    
}
