/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.bus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
    List<Make> appointments = new ArrayList<>();

    
    public Make createNewAppointment(Make m, Long Pid, List<Long> list) {
        Person host = ps.getUser();
        m.getPersonList().add(host);
        host.getAppointments().add(m);
        for (Long id : list) {
            Person pers = pf.find(id);
            m.getPersonList().add(pers);
            pers.getAppointments().add(m);
        }
        appointments = host.getAppointments();
        appointments.add(m);
        mf.create(m);
        ps.loginUser(host);
        ps.getUser().setAppointments(appointments);
        return m;
    }
    public Long compareTo(Make apps) {
        Long ids=((Make)apps).getId();
        /* For Ascending order*/
        return ids;
    }

    public List<Make> fetchAllAppointments() {
        return mf.findAll();
    }

    public Make fetchSelectedAppointment(Long id) {
        return mf.find(id);
    }
    
    public List<Make> getLoginApps(Person user){
        return user.getAppointments();
    }

    public List<Make> filterAppointments(LocalDateTime timeStart, LocalDateTime timeEnd) {
        System.out.print("START: " + timeStart + "   END: " + timeEnd);
        List<Make> ap = mf.findAll();
        List<Make> appointments = new ArrayList<>();
        for (Make m : ap) {
            LocalDateTime aStart = m.getTimeStart();
            LocalDateTime aEnd = m.getTimeEnd();
            System.out.print("START: " + aStart + "   END: " + aEnd);

            Boolean s = aStart.isAfter(timeStart);
            Boolean e = aEnd.isBefore(timeEnd);
            System.out.print("START: " + s + "   END: " + e);
            if (s && e) {
                appointments.add(m);
            }
        };

        return appointments;
    }

}
