/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.pers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sam.web_v1.ent.Make;

/**
 *
 * @author sjp20
 */
@Stateless
public class MakeFacade extends AbstractFacade<Make> {

    @PersistenceContext(unitName = "sam_web_v1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MakeFacade() {
        super(Make.class);
    }

    
}
