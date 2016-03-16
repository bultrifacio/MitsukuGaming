/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.util.ArrayList;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author alumno
 */
@Stateful
public class ShoppingCart implements ShoppingCartLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public ArrayList<String> Cart = new ArrayList<>();
    
    @Override
    public void addProduct(String id){
        Cart.add(id);
    }
    
    @Override
    public void removeProduct (String id){
        Cart.remove(id);
    }
    
    @Override
    public ArrayList<String> getContents() {
        return Cart;
    }
    
    /**
     *
     */
    @Remove
    public void remove(){
        Cart = null;
    }
}
