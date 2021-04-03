package sam.web_v1.bus;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import sam.web_v1.pers.PersonFacade;

@Stateless
public class ViewServices {
    
   @EJB
    private PersonFacade pf;    
    
     
    
   
}
