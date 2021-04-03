/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.ctrl;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sam.web_v1.bus.MakeServices;
import sam.web_v1.bus.PersonServices;
import sam.web_v1.ent.Make;
import sam.web_v1.ent.Person;

/**
 *
 * @author sjp20
 */
@Named(value = "viewCtrl")
@RequestScoped
public class ViewCtrl {

    private Person p = new Person();
    private Make m = new Make();

    public Make getM() {
        return m;
    }

    public void setM(Make m) {
        this.m = m;
    }

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }

    public String getInfo(Person p) {
        System.out.print("The User Clicked : " + p);
        return p.getUserName();
    }
    
    
    @EJB
    private PersonServices ps;

    public List<Person> findAllPersons() {
        return ps.fetchAllPersons();
    }

    @EJB
    private MakeServices ms;
    public List<Make> findAllAppointments(){
        return ms.fetchAllAppointments();
    }
}
