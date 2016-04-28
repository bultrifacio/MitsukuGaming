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
    public ArrayList<Product> Cart = new ArrayList<>();
    
    @Override
    public void addProduct(Product product){
        Cart.add(product);
    }
    
    @Override
    public void removeProduct (Product product){
        Cart.remove(product);
    }
    
    @Override
    public ArrayList<Product> getContents() {
        return Cart;
    }
    
    @Override
    public void setContents(ArrayList<Product> list) {
        Cart = list;
    }
    
    @Remove
    public void remove(){
        Cart = null;
    }
    
    @Override
    public float getTotal() {
        float total = 0;
        for (Product product : Cart) {
            if (product.getDiscount() > 0) {
                total += product.getPrice() - (product.getPrice() * (product.getDiscount() / 100.0));
            } else {
                total += product.getPrice();
            }
        }
        return total;
    }
}
