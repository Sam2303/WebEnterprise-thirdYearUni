/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.bus;

import java.util.List;
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
    private Person user;
    public String username = "";
    private String logOutput = "Login";

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
    
    @EJB
    private PersonFacade pf;

    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Person createNewPerson(Person p){
        pf.create(p);
        return p;
    }
    
    public Person getPersonInfo(Person p){
        return p;
    }
    
    
    public List<Person> fetchAllPersons(){
        return pf.findAll();
    }
    
//    public Boolean loginUser(Person p){
//        List<Person> list = (pf.findAll());
//       return list.stream().anyMatch(pers -> (pers.getUserName().equals(p.getUserName()) && pers.getPassword().equals(p.getPassword()) ));
//    }
    
    public Person loginUser(Person p){
        List<Person> list = (pf.findAll());
        for(Person person: list){
            if(person.getUserName().equals(p.getUserName()) 
                    && 
                        person.getPassword().equals(p.getPassword())){
                user = person;
                username = person.getUserName();
                return person;
            }
        }
        return null;
        
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }



   
    
   
}
