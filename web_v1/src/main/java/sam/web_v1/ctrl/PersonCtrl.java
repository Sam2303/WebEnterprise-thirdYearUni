/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.ctrl;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sam.web_v1.bus.PersonServices;
import sam.web_v1.ent.Person;

/**
 *
 * @author sjp20
 */
@Named(value = "personCtrl")
@RequestScoped
public class PersonCtrl {
    
    private Person p = new Person();

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }
    
    
    public PersonCtrl() {
    }
    
    @EJB
    private PersonServices ps;
    public String doCreatePerson() {
        ps.createNewPerson(p);
        return "";
    }
    
 
}
