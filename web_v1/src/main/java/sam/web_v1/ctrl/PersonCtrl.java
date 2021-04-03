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
import sam.web_v1.bus.PersonServices;
import sam.web_v1.ent.Person;

/**
 *
 * @author sjp20
 */
@Named(value = "personCtrl")
@RequestScoped
public class PersonCtrl {    
    private List<Long> appointmentId = new ArrayList<>();
    @EJB
    private PersonServices ps;
    Person person;
    String username = "";
    String logOutput = "Login";

    public String getLogOutput() {
        return logOutput;
    }

    public void setLogOutput(String logOutput) {
        this.logOutput = logOutput;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    public List<Long> getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(List<Long> appointmentId) {
        this.appointmentId = appointmentId;
    }

    private Person p = new Person();
    private String errorUserName = "Username";
    private String errorPassword = "Password";

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }

    public String getErrorUserName() {
        return errorUserName;
    }

    public void setErrorUserName(String errorUserName) {
        this.errorUserName = errorUserName;
    }

    public String getErrorPassword() {
        return errorPassword;
    }

    public void setErrorPassword(String errorPassword) {
        this.errorPassword = errorPassword;
    }

    public PersonCtrl() {
    }

    public String getInfo() {
        return p.getUserName();
    }

    public List<Person> findAllPersons() {
        return ps.fetchAllPersons();
    }

    public String doCreatePerson() {
        ps.createNewPerson(p);
        return "";
    }

    public String checkUser() {        
        if(ps.loginUser(p).getUserName().equals(p.getUserName())
                &&
                   ps.loginUser(p).getPassword().equals(p.getPassword())){
            
            System.out.print(ps.loginUser(p));
            
            ps.setUser(ps.loginUser(p));
            ps.setLogOutput("Logout");
            ps.setUsername(p.getUserName());
            username = ps.getUsername();
            logOutput = ps.getLogOutput();
            return "index.xhtml";
            
        } else {
            errorUserName = "Username or Password is incorrect!";
            errorPassword = "Username or Password is incorrect!";
            p.setUserName("");
            p.setPassword("");
            return null;
        }
    }
    
    public String loginPerson(){
        return "login.xhtml";
    }
    
    public void onLoad(){
        System.out.print("PAGE LOADED");
        username = ps.getUsername();
        logOutput = ps.getLogOutput();
    }
}
