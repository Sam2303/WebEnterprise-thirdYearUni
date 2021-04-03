/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.webv2.ctrl;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sam.webv2.bus.PersonService;
import sam.webv2.ent.Address;

/**
 *
 * @author sjp20
 */
@Named(value = "addressCtrl")
@RequestScoped
public class AddressCtrl {

    /**
     * Creates a new instance of AddressCtrl
     */
    public AddressCtrl() {
    }
    @EJB
    private PersonService ps;
    private Address a = new Address();
    private List<Address> allAddresses = new ArrayList<>();

    public Address getA() {
        return a;
    }

    public void setA(Address a) {
        this.a = a;
    }
    
     public String doCreateAddress(){
        ps.createNewAddress(a);
        return "";
    }

    public List<Address> getAllAddresses() {
        allAddresses = ps.fetchAllAddresses();
        return allAddresses;
    }

    public void setAllAddresses(List<Address> allAddresses) {
        this.allAddresses = allAddresses;
    }
     
 
    
}
