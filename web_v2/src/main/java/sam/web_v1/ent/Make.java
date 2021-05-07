/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.ent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;

/**
 *
 * @author sjp20
 */
@Entity
public class Make implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long id;
    private String AppointmentTitle;
    @Temporal(javax.persistence.TemporalType.DATE) //timestamp
    private Date dateSet;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timeSet; // do not use date
    
    @ManyToMany
    private List<Person> personList = new ArrayList<>();

    //@ManyToMany
    //private List<Person> hostList = new ArrayList<>();

//    public List<Person> getHostList() {
//        return hostList;
//    }
//
//    public void setHostList(List<Person> hostList) {
//        this.hostList = hostList;
//    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
    
    public Date getDateSet() {
        return dateSet;
    }

    public void setDateSet(Date dateSet) {
        this.dateSet = dateSet;
    }

    public Date getTimeSet() {
        return timeSet;
    }

    public void setTimeSet(Date timeSet) {
        this.timeSet = timeSet;
    }

    public String getAppointmentTitle() {
        return AppointmentTitle;
    }

    public void setAppointmentTitle(String AppointmentTitle) {
        this.AppointmentTitle = AppointmentTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Make)) {
            return false;
        }
        Make other = (Make) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return AppointmentTitle;
    }
    
}
