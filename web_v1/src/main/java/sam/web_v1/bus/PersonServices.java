/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.bus;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import sam.web_v1.ent.Person;
import sam.web_v1.pers.PersonFacade;

/**
 *
 * @author sjp20
 */
@Stateless
public class PersonServices {
    
    @EJB
    private PersonFacade pf;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Person createNewPerson(Person p){
        pf.create(p);
        return p;
    }
   
}
