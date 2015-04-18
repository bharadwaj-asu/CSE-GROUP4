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
    public static String hash(String pw, String salt) {
		
		int m=0;
		int charInt;
		char d;
		String passHash="";
		for (int n = 0; n < pw.length(); n++) {
			charInt = 97 + pw.charAt(n)%26;
			d = (char)charInt;
			m++;
			passHash = passHash + d;
			if ((m*n)%5 == 0) {
				passHash = passHash + (((int)pw.charAt(m%pw.length()))%10);
				//str = str + pw.charAt(m)%10;
			}
			if (m==salt.length()) m = 0;
		}
		
		return passHash.trim();

    }
    public Date getLastLogin () {
        
        return lastLogin;
        
    }

}