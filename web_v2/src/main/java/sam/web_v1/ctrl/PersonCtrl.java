package sam.web_v1.ctrl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sam.web_v1.bus.MakeServices;
import sam.web_v1.bus.PersonServices;
import sam.web_v1.ent.Make;
import sam.web_v1.ent.Person;

@Named(value = "personCtrl")
@RequestScoped
public class PersonCtrl {

    @EJB
    private PersonServices ps;
    @EJB
    private MakeServices ms;
    Person person;
    String username = "";
    String firstName = "";
    List<Make> appoinments = new ArrayList<>();

    String logOutput = "Login";
    private Person p = new Person();
    private String errorUserName = "Username";
    private String errorPassword = "Password";

    String userNameText = "Username";

    public String doCreatePerson() {
        userNameText = ps.createNewPerson(p);
        if (userNameText.equals("Account created")) {
            return "index.xhtml";
        } else {
            p.setUserName("");
            p.setFirstName("");
            p.setPassword("");
            p.setLastName("");
            p.setEmailAddress("");
            p.setPhoneNumber("");
            return "login.xhtml";
        }
    }

    public String loginPerson() {
        Person loggedIn = ps.login(p);
        if (loggedIn != null) {
            person = loggedIn;
            ms.setHost(person);
            username = person.getUserName();
            logOutput = ps.getLogOutput();
            return "index.xhtml";
        } else {
            errorUserName = "Username or Password is incorrect!";
            errorPassword = "Username or Password is incorrect!";
            p.setUserName("");
            p.setPassword("");
        }
        return null;
    }

        public String logoutButton(){
        if(ps.getLogOutput().equals( "Logout")){
            
            logOutput = "Login";
            username = "";
            ps.setLogOutput("Login");
            ps.setUser(null);
            ps.setUsername("");
            
            return "index.xhtml";
        }
        return "login.xhtml";
    }
    
//    public String checkUserForEdit() {
//        if (ps.loginUser(p) != null && ps.loginUser(p).getUserName().equals(p.getUserName())
//                && ps.loginUser(p).getPassword().equals(p.getPassword())) {
//
//            System.out.print(ps.loginUser(p));
//
//            ps.setUser(ps.loginUser(p));
//            ps.setLogOutput("Logout");
//            ps.setUsername(p.getUserName());
//            username = ps.getUsername();
//            logOutput = ps.getLogOutput();
//
//            p.setFirstName(ps.getUser().getFirstName());
//            p.setLastName(ps.getUser().getLastName());
//            p.setPassword(ps.getUser().getPassword());
//            p.setEmailAddress(ps.getUser().getEmailAddress());
//            p.setPhoneNumber(ps.getUser().getPhoneNumber());
//
//            return "edit.xhtml";
//
//        } else {
//            errorUserName = "Username or Password is incorrect!";
//            errorPassword = "Username or Password is incorrect!";
//            p.setUserName("");
//            p.setPassword("");
//            return null;
//        }
//    }
    public String loginBtn() {
        return "login.xhtml";
    }

    public String editUser() {
        ps.setUser(ps.updateUserDetails(p));

        return "index.xhtml";

    }

    public String loginUserName() {
        return ps.getUsername();
    }

    public String logOutputFunction() {
        return ps.getLogOutput();
    }
    
    public Person getLogin() {
        Person user = ps.getUser();
        if (user != null) {
            return user;
        } else {
            return ps.getUser();
        }
    }
    
    public void onLoad(){
        System.out.print("ONLOAD PERSONCTRL: " + ms.getHost());
    }
   
    public String getUserNameText() {
        return userNameText;
    }

    public void setUserNameText(String userNameText) {
        this.userNameText = userNameText;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Make> getAppoinments() {
        return appoinments;
    }

    public void setAppoinments(List<Make> appoinments) {
        this.appoinments = appoinments;
    }

}
