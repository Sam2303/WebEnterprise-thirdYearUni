/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.ctrl;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sam.web_v1.bus.MakeServices;
import sam.web_v1.ent.Make;

/**
 *
 * @author sjp20
 */
@Named(value = "makeCtrl")
@RequestScoped
public class MakeCtrl {
    
    @EJB
    private MakeServices ms;
    
    private Make m = new Make();
    private String intro = "Enter all the correct details to create an appointment and notify all other users involved";
    
    
    public MakeCtrl() {
    }

    public Make getM() {
        return m;
    }

    public void setM(Make m) {
        this.m = m;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
 
    public String createAppointment(){
        ms.createNewAppointment(m);
        return "";
    }

}
