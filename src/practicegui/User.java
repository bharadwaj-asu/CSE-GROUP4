package practicegui;
import java.util.*;

/**
 *
 * @author Clinton
 */
public class User {
    
    private String userName;
    private String passHash;
    public userType typeOfUser;
    private Date lastLogin;
    
    public User (String un, String ph, userType ut, Date ll) {
        
        userName = un;
        passHash = ph;
        typeOfUser = ut;
        lastLogin = ll;
        // System.out.println(un+" "+ph+" "+ut+" "+ll);
        
    }

    public boolean isPatient() {
    
        return(typeOfUser.equals(userType.patient));
        
    }
    public boolean isDoctor() {
        return(typeOfUser.equals(userType.doctor));
        
        
    }
    
    public boolean isNurse() {
        
        return(typeOfUser.equals(userType.nurse));
        
    }
    
    public boolean isReceptionist() {
        
        return(typeOfUser.equals(userType.receptionist));
        
    }
    
    public userType getType() {
        
        return typeOfUser;
        
    }
    public String getPassHash() {
        
        return passHash;
        
    }
    public String getUserName () {
        
        return userName;
        
    }
    public Date getLastLogin () {
        
        return lastLogin;
        
    }

}