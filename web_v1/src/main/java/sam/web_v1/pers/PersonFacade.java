/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.pers;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sam.web_v1.ent.Person;

/**
 *
 * @author sjp20
 */
@Stateless
public class PersonFacade extends AbstractFacade<Person> {

    @PersistenceContext(unitName = "sam_web_v1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonFacade() {
        super(Person.class);
    }

    public Person fetchLoginUser(Person p) {

        try {
            Query q = em.createQuery("SELECT p FROM Person p WHERE p.userName = :username AND p.password = :password");
            q.setParameter("username", p.getUserName());
            q.setParameter("password", p.getPassword());
            Person pl = (Person) q.getSingleResult();
//            System.out.print(pl);
            return pl;
        } catch (Exception ex) {
            System.out.print("ERROR BAAAD: "+ex);
        }
        return p;
    }
}
