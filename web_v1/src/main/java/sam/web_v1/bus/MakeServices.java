/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.bus;

import java.time.LocalTime;
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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Make createNewAppointment(Make m, Long Pid, List<Long> list, String selectDur) {
        LocalTime duration = LocalTime.parse(selectDur);
        Person host = ps.getUser();
        m.getPersonList().add(host);
        host.getAppointments().add(m);
        m.setTimeEnd(m.getTimeStart().plusHours(duration.getHour()).plusMinutes(duration.getMinute()));
        for (Long id : list) {
            Person pers = pf.find(id);
            m.getPersonList().add(pers);
            pers.getAppointments().add(m);
        }
        mf.create(m);
        ps.loginUser(host);
        return m;
    }

    public List<Make> fetchAllAppointments() {
        return mf.findAll();
    }

}
