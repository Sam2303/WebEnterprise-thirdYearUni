/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.ctrl;

import java.time.LocalDateTime;
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
@Named(value = "viewCtrl")
@RequestScoped
public class ViewCtrl {

    private Person p = new Person();
    private Make m = new Make();
    private Person personClicked;
    private String searchBarText = "";
    private List<Person> allUsersPrinted;
    private Person loggedIn;

    private List<Make> appointments = new ArrayList<>();
    private String errMsg;
   

    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;

    @EJB
    private PersonServices ps;
    @EJB
    private MakeServices ms;

    public void onLoad() {
        findAllPersons();
        ps.loginUser(ps.getUser());
    }

    public void rangeLoad() {
        appointments = ms.fetchAllAppointments();
        System.out.print(appointments);
    }
    
    
    public String filterAppointments(){
        List<Make> al = ms.filterAppointments(timeStart, timeEnd);
        if(al.isEmpty()){
            errMsg = "There is no appointments in the date range selected";
        }else{
            System.out.print(al);
            appointments = al;
        }
        return "range.xhtml";
    }
    

    public List<Person> findAllPersons() {
        allUsersPrinted = ps.fetchAllPersons();
        return allUsersPrinted;
    }

    public List<Person> findSearchedPersons() {
        allUsersPrinted = ps.findSearchedUsers(searchBarText);
        return allUsersPrinted;
    }

    public Person findClicked(Person p) {
        System.out.print(p);
        personClicked = ps.findPerson(p.getId());
        return ps.findPerson(p.getId());
    }

    public List<Make> findAllAppointments() {
        return ms.fetchAllAppointments();
    }

    public String getSearchBarText() {
        return searchBarText;
    }

    public void setSearchBarText(String searchBarText) {
        this.searchBarText = searchBarText;
    }

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

    public Person getPersonClicked() {
        return personClicked;
    }

    public void setPersonClicked(Person personClicked) {
        this.personClicked = personClicked;
    }

    public List<Person> getAllUsersPrinted() {
        return allUsersPrinted;
    }

    public void setAllUsersPrinted(List<Person> allUsersPrinted) {
        this.allUsersPrinted = allUsersPrinted;
    }

    public List<Make> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Make> appointments) {
        this.appointments = appointments;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
