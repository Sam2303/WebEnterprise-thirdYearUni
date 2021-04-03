/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.webv2.ctrl;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sam.webv2.bus.PersonService;
import sam.webv2.ent.Person;

/**
 *
 * @author sjp20
 */
@Named(value = "personCtrl")
@RequestScoped
public class PersonCtrl {

    private Person p = new Person();
    private List<Person> allPersons = new ArrayList<>();
    private Long residenceId = new Long(0);

    public Long getResidenceId() {
        return residenceId;
    }

    public void setResidenceId(Long residenceId) {
        this.residenceId = residenceId;
    }

    public List<Person> getAllPersons() {
//        if (allPersons.isEmpty()){
            allPersons = ps.fetchAllPersons();
//        }
        return allPersons;
    }

    public void setAllPersons(List<Person> allPersons) {
        this.allPersons = allPersons;
    }

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }
    /**
     * Creates a new instance of PersonCtrl
     */
    public PersonCtrl() {
    }
    
    @EJB
    private PersonService ps;
    
    public String doCreatePerson(){
        ps.createNewPerson(p, residenceId);
        return "";
    }
    
    
    
    
    
}
