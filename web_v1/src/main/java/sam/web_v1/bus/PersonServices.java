/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sam.web_v1.bus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import sam.web_v1.ent.Make;
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
    private List<Make> appointments = new ArrayList<>();
    private List<Person> allPersonsSearched = new ArrayList<>();

    private String usernameErrMsg = "";
    private String addressFlErrorMsg = "";
    private String postCodeErrorMsg = "";
    private String numberErrorMsg = "";
    private String emailErrorMsg = "";

    @EJB
    private PersonFacade pf;

    public Person updateUserDetails(Person p) {
        p.setId(user.getId());
        p = pf.edit(p);
        p.setAppointments(user.getAppointments());
        return p;
    }

    public Person updateMakeList(Person p) {
        if (user == null) {
            return user;
        } else {
            System.out.print("THIS IS THE UPDATEMAKELIST USER: " + user);
            user = pf.find(p.getId());
            return user;
        }
    }

    public List<Person> findSearchedUsers(String searchBarText) {
        allPersonsSearched.clear();
        allPersonsSearched = pf.findAll();
        if (!searchBarText.equals("")) {
            List<Person> searchedUsers = new ArrayList<>();
            allPersonsSearched.stream().filter(p -> (p.getUserName().contains(searchBarText))).forEachOrdered(p -> {
                searchedUsers.add(p);
            });
            allPersonsSearched = searchedUsers;
        }
        return allPersonsSearched;
    }

    public Person createNewPerson(Person p, String postCode) {
        if (isDetailsValid(p, postCode)) {
            pf.create(p);
            return p;
        } else {
            return null;
        }
    }

    public Boolean isDetailsValid(Person p, String postCode) {
        // True is no user has that username
        Boolean dupUser = pf.isDuplicateName(p);
        // True if the address is valid
        Boolean isAddress = checkAddress(p, postCode);
        // True if phone number is valid
        Boolean isNumber = checkNumber(p);
        // True if email is valid
        Boolean isEmail = checkEmail(p);

        // Must all return true to return true
        if (dupUser && isAddress && isNumber && isEmail) {
            return true;
        } else {
            if (dupUser == false) {
                System.out.print("USERNAME TAKEN");
                usernameErrMsg = "This Username is already taken";
                System.out.print(p.getUserName());
            }
            if (isAddress == false) {
                System.out.print("ADDRESS NOT VALID");
                postCodeErrorMsg = "This is not a valid address. Please make sure that all letter are capitals";
            }
            if (isNumber == false) {
                System.out.print("NUMBER NOT VALID");
                numberErrorMsg = "This is not a valid phone number";
            }
            if (isEmail == false) {
                System.out.print("EMAIL NOT VALID");
                emailErrorMsg = "This is not a valid email address";
            }
            return false;
        }
    }

    public Boolean checkAddress(Person p, String postCode) {

        String regex = "^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(postCode);
        System.out.print("ADDRESS: " + matcher.matches());
        if (matcher.matches()) {
            postCodeErrorMsg = "";
        }
        return matcher.matches();
    }

    public Boolean checkNumber(Person p) {
        String regex = "^\\(?(?:(?:0(?:0|11)\\)?[\\s-]?\\(?|\\+)44\\)?[\\s-]?\\(?(?:0\\)?[\\s-]?\\(?)?|0)(?:\\d{2}\\)?[\\s-]?\\d{4}[\\s-]?\\d{4}|\\d{3}\\)?[\\s-]"
                + "?\\d{3}[\\s-]?\\d{3,4}|\\d{4}\\)?[\\s-]?(?:\\d{5}|\\d{3}[\\s-]?\\d{3})|\\d{5}\\)?[\\s-]?\\d{4,5}|8(?:00[\\s-]?11[\\s-]?11|45[\\s-]?46[\\s-]?4\\d))"
                + "(?:(?:[\\s-]?(?:x|ext\\.?\\s?|\\#)\\d+)?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(p.getPhoneNumber());
        System.out.print("NUMBER" + matcher.matches());
        if (matcher.matches()) {
            numberErrorMsg = "";
        }
        return matcher.matches();

    }

    public Boolean checkEmail(Person p) {
        String email = p.getEmailAddress();
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            System.out.print("EMAIL TRUE");
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }

    //isNameDuplicated - make function to check duplicate name  -  Boolean
    public Person loginUser(Person p) {
        Person person = pf.fetchLoginUser(p);
        if (person != null) {
            user = person;
            username = person.getUserName();
            appointments = person.getAppointments();
            return person;
        } else {
            return null;
        }
    }

    public List<Person> fetchAllPersons() {
        List<Person> allPersons = pf.findAll();
        return allPersons;
    }

    public List<Person> getAllPersonsSearched() {
        return allPersonsSearched;
    }

    public void setAllPersonsSearched(List<Person> allPersonsSearched) {
        this.allPersonsSearched = allPersonsSearched;
    }

    public Person getPersonInfo(Person p) {
        return p;
    }

    public Person findPerson(Long id) {
        return pf.find(id);
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

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

    public List<Make> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Make> appointments) {
        this.appointments = appointments;
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
