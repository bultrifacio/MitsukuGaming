/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author alumno
 */
@Local
public interface ShoppingCartLocal {

    public void addProduct(Product product);

    public void removeProduct(Product product);

    public ArrayList<Product> getContents();

    public void setContents (ArrayList<Product> list);
    
    public void remove();
    
    public float getTotal();
    
}
