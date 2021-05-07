/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.bus;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import sam.web_v1.ent.Make;
import sam.web_v1.ent.Person;
import sam.web_v1.pers.MakeFacade;
import sam.web_v1.pers.PersonFacade;

/**
 *
 * @author sjp20
 */
@Stateless
public class MakeServices {

    @EJB
    private MakeFacade mf;
    @EJB
    private PersonFacade pf;
    @EJB
    private PersonServices ps;

    private Person host;
    
    public Make createNewAppointment(Make m, List<Long> list) {

        System.out.print("THIS IS THE HOST: " + host);
        m.getPersonList().add(host);
        host.getAppointments().add(m);

        for (Long id : list) {
            Person pers = pf.find(id);
            m.getPersonList().add(pers);
            pers.getAppointments().add(m);
        }
        mf.create(m);
        ps.login(host);
        return m;
    }

    public Make create(Make m) {
        mf.create(m);
        return m;
    }

    public List<Make> fetchAllAppointments() {
        return mf.findAll();
    }

    public Person getHost() {
        return host;
    }

    public void setHost(Person host) {
        this.host = host;
    }

}
