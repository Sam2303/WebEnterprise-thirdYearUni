package sam.web_v1.ctrl;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sam.web_v1.bus.MakeServices;
import sam.web_v1.bus.PersonServices;
import sam.web_v1.ent.Person;

@Named(value = "personCtrl")
@RequestScoped
public class PersonCtrl {

    @EJB
    private PersonServices ps;
    Person person;
    String username = "";
    String firstName = "";
    String logOutput = "Login";
    private Person p = new Person();
    private String errorUserName = "Username";
    private String errorPassword = "Password";

    String userNameText = "Username";
    String addressFL;
    String postCode;

    private String usernameErrMsg = "";
    private String addressFlErrorMsg = "";
    private String postCodeErrorMsg = "";
    private String numberErrorMsg = "";
    private String emailErrorMsg = "";

    public void onLoad() {
        System.out.print("PAGE LOADED");
        person = ps.updateMakeList(person);
    }

    public String doCreatePerson() {
        System.out.print("REGISTER CLICKED");
        String address = addressFL + ", " + postCode;
        p.setAddress(address);
        Person user = ps.createNewPerson(p, postCode);
        System.out.print(user);
        if (user != null) {
            return "login.xhtml";
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage("username", new FacesMessage(ps.getUsernameErrMsg()));

        usernameErrMsg = ps.getUsernameErrMsg();
        addressFlErrorMsg = ps.getAddressFlErrorMsg();
        postCodeErrorMsg = ps.getPostCodeErrorMsg();
        numberErrorMsg = ps.getNumberErrorMsg();
        emailErrorMsg = ps.getEmailErrorMsg();
        return "register.xhtml";

    }

    public String checkUser() {
        Person personRet = ps.loginUser(p);
        if (personRet != null) {
            System.out.print(personRet);
            ps.setUser(personRet);
            ps.setLogOutput("Logout");
            ps.setUsername(personRet.getUserName());
            username = ps.getUsername();
            logOutput = ps.getLogOutput();
            person = personRet;
            return "index.xhtml";

        } else {
            errorUserName = "Username or Password is incorrect!";
            errorPassword = "Username or Password is incorrect!";
            p.setUserName("");
            p.setPassword("");
            return null;
        }
    }

    public String loginPerson() {
        return "login.xhtml";
    }

    public String checkUserForEdit() {
        Person personRet = ps.loginUser(p);
        if (personRet != null) {

            System.out.print(ps.loginUser(p));

            ps.setUser(ps.loginUser(p));
            ps.setLogOutput("Logout");
            ps.setUsername(p.getUserName());
            username = ps.getUsername();
            logOutput = ps.getLogOutput();

            Person user = ps.getUser();

            p.setFirstName(user.getFirstName());
            p.setLastName(user.getLastName());
            p.setPassword(user.getPassword());
            p.setAddress(user.getAddress());
            p.setEmailAddress(user.getEmailAddress());
            p.setPhoneNumber(user.getPhoneNumber());

            return "edit.xhtml";

        } else {
            errorUserName = "Username or Password is incorrect!";
            errorPassword = "Username or Password is incorrect!";
            p.setUserName("");
            p.setPassword("");
            return null;
        }
    }

    public String checkLoggedIn(String page) {
        if (ps.getUser() != null) {
            if (page.equals("make")) {
                return "make.xhtml";
            }
            if (page.equals("view")) {
                return "view.xhtml";
            }
            if (page.equals("range")) {
                return "range.xhtml";
            }
        }
        return "login.xhtml";
    }

// Edit User button runs this    
    public String editUser() {
        p.setAddress(addressFL + ", " + postCode);
        if (ps.isDetailsValid(p, postCode)) {
            Person retS = ps.updateUserDetails(p);
            ps.loginUser(retS);
            ps.setUser(retS);
            ps.setLogOutput("Logout");
            ps.setUsername(retS.getUserName());
            username = ps.getUsername();
            logOutput = ps.getLogOutput();

            return "index.xhtml";
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage("username", new FacesMessage(ps.getUsernameErrMsg()));

        usernameErrMsg = ps.getUsernameErrMsg();
        addressFlErrorMsg = ps.getAddressFlErrorMsg();
        postCodeErrorMsg = ps.getPostCodeErrorMsg();
        numberErrorMsg = ps.getNumberErrorMsg();
        emailErrorMsg = ps.getEmailErrorMsg();
        return "";
    }

    public String loginUserName() {
        return ps.getUsername();
    }

    public String logOutputFunction() {
        return ps.getLogOutput();
    }

    @EJB
    MakeServices ms;

    public Person getLogin() {
        return ps.getUser();
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

    public String getAddressFL() {
        return addressFL;
    }

    public void setAddressFL(String addressFL) {
        this.addressFL = addressFL;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getUsernameErrMsg() {
        return usernameErrMsg;
    }

    public void setUsernameErrMsg(String usernameErrMsg) {
        this.usernameErrMsg = usernameErrMsg;
    }

    public String getAddressFlErrorMsg() {
        return addressFlErrorMsg;
    }

    public void setAddressFlErrorMsg(String addressFlErrorMsg) {
        this.addressFlErrorMsg = addressFlErrorMsg;
    }

    public String getPostCodeErrorMsg() {
        return postCodeErrorMsg;
    }

    public void setPostCodeErrorMsg(String postCodeErrorMsg) {
        this.postCodeErrorMsg = postCodeErrorMsg;
    }

    public String getNumberErrorMsg() {
        return numberErrorMsg;
    }

    public void setNumberErrorMsg(String numberErrorMsg) {
        this.numberErrorMsg = numberErrorMsg;
    }

    public String getEmailErrorMsg() {
        return emailErrorMsg;
    }

    public void setEmailErrorMsg(String emailErrorMsg) {
        this.emailErrorMsg = emailErrorMsg;
    }

}
