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
    private Person personClicked;
    private String searchBarText = "";
    private List<Person> allUsersPrinted;

    @EJB
    private PersonServices ps;
    @EJB
    private MakeServices ms;

    public void onLoad() {
        findAllPersons();
        ps.login(ps.getUser());
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

}
