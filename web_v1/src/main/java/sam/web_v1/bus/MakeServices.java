/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.bus;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import sam.web_v1.ent.Make;
import sam.web_v1.pers.MakeFacade;

/**
 *
 * @author sjp20
 */
@Stateless
public class MakeServices {
    
   @EJB
    private MakeFacade mf;    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Make createNewAppointment(Make m) {
        mf.create(m);
        return m;
    }
    
    public List<Make> fetchAllAppointments(){
        return mf.findAll();
    }
  

   
    
   
}
