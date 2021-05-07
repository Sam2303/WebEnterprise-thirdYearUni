/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.bus;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import sam.web_v1.ent.Make;
import sam.web_v1.ent.Person;
import sam.web_v1.pers.PersonFacade;

/**
 *
 * @author sjp20
 */
@Stateless
public class PersonServices {

    private Person user;
    public String username = "";
    private String logOutput = "Login";
    private List<Make> appointments = new ArrayList<>();
    private List<Person> allPersonsSearched = new ArrayList<>();

    @EJB
    private PersonFacade pf;

    public Person updateUserDetails(Person p) {
        p = pf.edit(p);
        return p;
    }

    public List<Person> findSearchedUsers(String searchBarText) {
        allPersonsSearched.clear();
        allPersonsSearched = pf.findAll();
        if (!searchBarText.equals("")) {
            List<Person> searchedUsers = new ArrayList<>();
            allPersonsSearched.stream().filter(p -> (p.getUserName().contains(searchBarText))).forEachOrdered(p -> {
                searchedUsers.add(p);
            });
            allPersonsSearched = searchedUsers;
        }
        return allPersonsSearched;
    }

    public String createNewPerson(Person p) {
        List<Person> allUsers = pf.findAll();

        for (Person pers : allUsers) {
            if (!pers.getUserName().equals(p.getUserName())) {
                pf.create(p);
                return "Account created";
            } else {
                return "Username already taken";
            }
        }
        return null;
    }

    public Person login(Person p) {
        try {
            Person person = pf.fetchLoginUser(p);
            user = person;
            username = person.getUserName();
            logOutput = "Logout";
            return person;
        } catch (Exception ex) {
            System.out.print(ex);
        }
        return null;
    }

    public Person getLoggedInUser() {
        return getUser();
    }

    public List<Person> fetchAllPersons() {
        List<Person> allPersons = pf.findAll();
        return allPersons;
    }

    public List<Person> getAllPersonsSearched() {
        return allPersonsSearched;
    }

    public void setAllPersonsSearched(List<Person> allPersonsSearched) {
        this.allPersonsSearched = allPersonsSearched;
    }

    public Person getPersonInfo(Person p) {
        return p;
    }

    public Person findPerson(Long id) {
        return pf.find(id);
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogOutput() {
        return logOutput;
    }

    public void setLogOutput(String logOutput) {
        this.logOutput = logOutput;
    }

    public List<Make> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Make> appointments) {
        this.appointments = appointments;
    }

}
