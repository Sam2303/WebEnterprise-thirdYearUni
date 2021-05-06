/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.ctrl;

import java.util.ArrayList;
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
@Named(value = "makeCtrl")
@RequestScoped
public class MakeCtrl {
    
    @EJB
    private MakeServices ms;
    @EJB
    private PersonServices ps;
    
    private Make m = new Make();
    private String intro = "Enter all the correct details to create an appointment and notify all other users involved";
    
    private Long personId = new Long(0);
    private List<Long> personIdList = new ArrayList<>();
    
    private Person host;
    
    public String createAppointment() {
        System.out.print(personIdList + "   Host: " + ms.getHost());
        ms.createNewAppointment(m, personIdList);
        return "";
    }
    
    public Long getPersonId() {
        return personId;
    }
    
    public void setPersonId(Long personId) {
        this.personId = personId;
    }
    
    public MakeCtrl() {
    }
    
    public Make getM() {
        return m;
    }
    
    public void setM(Make m) {
        this.m = m;
    }
    
    public String getIntro() {
        return intro;
    }
    
    public void setIntro(String intro) {
        this.intro = intro;
    }
    
    public List<Long> getPersonIdList() {
        return personIdList;
    }
    
    public void setPersonIdList(List<Long> personIdList) {
        this.personIdList = personIdList;
    }

    public Person getHost() {
        return host;
    }

    public void setHost(Person host) {
        this.host = host;
    }
    
    public void onLoad() {
        System.out.print("THIS RUNS ONLOAD SHOULD BE LOGGED IN PERSON: "+ps.getUser() + "USERNAME: " +ps.getUsername());
      }
}
