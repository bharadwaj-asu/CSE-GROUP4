/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practicegui;

import static java.lang.Math.sqrt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
// import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Clinton
 */
public class DatabaseController {
    
    private String url = "jdbc:mysql://localhost:3306/test";
    private String un = "root";
    private String pw = "";
    
    public DatabaseController() {
        
    }
    
    public int evaluateReport (Report entry) {
        
        try
            {
              // create mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = url;
              String user = un;
              String password = pw;
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, user, password);
              ResultSet rs;
              double count = 0;
              double total = 0;
              double average = 0;
              double mean = 0;
              double sd = 0;
              double eTotal = 1.0*(entry.getPain()+entry.getDrowsiness()+entry.getNausea()+entry.getAnxiety()+entry.getDepression());
              double eMean = eTotal/5.0;
              double sumDif = 0;
              
              //DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
              //Date date = new Date();
              
              //String date = df.format(d);
              //SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddhhmmss");
              
              // SQL INSERT query.

              String query = "SELECT pain, drowsiness, nausea, anxiety, depression FROM reports";

              Statement st = conn.createStatement();
              rs = st.executeQuery(query);
              while(rs.next()) {
                  
                  total = total + entry.getPain()+entry.getDrowsiness()+entry.getNausea()+entry.getAnxiety()+entry.getDepression();
                  average = average + ((entry.getPain()+entry.getDrowsiness()+entry.getNausea()+entry.getAnxiety()+entry.getDepression())/5.0);
                  count++;
              }
              mean = average/count;
              sumDif = total - mean*count;
              sd = sqrt((sumDif*sumDif)/count);
              if (((eMean-mean)/sd)>2.0) {
                  // recommend ER service
              }
              else if (((eMean-mean)/sd)>1.0) {
                  // force request appointment
              }
              st.close();
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());
            }
        
        return -1;
        
    }
    
    public void update(Appointment appt) {
        
        String sqlstr = "UPDATE appointments SET patient='"+appt.getPatient()+"', doctor='"+appt.getDoctor()+"', date = '"+appt.getAppointmentTime()+"', dateMade = '"+appt.getTimeMade()+"', authorized='"+appt.getAuthorized()+"', active='"+appt.getActive()+"', notes='"+appt.getNotes()+"' WHERE pkey='" + appt.getId() + "'";   

        try
            {
              // create mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = url;
              String user = un;
              String password = pw;
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, user, password);
              Statement st = conn.createStatement();
              st.executeUpdate(sqlstr);
              st.close();
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());
            }
     
    }
    
    public void update(Report report) {
        
        String sqlstr = "UPDATE reports SET patient='" + report.getPatient() + "', dateTime='" + report.getDateTime() + "', pain='" + report.getPain() + "', drowsiness='" + report.getDrowsiness() + "', nausea='" + report.getNausea() + "', anxiety='" + report.getAnxiety() + "', depression='" + report.getDepression() + "', comments='" + report.getComments() + "', doctorSuggestion='" + report.getSuggestion() + "'  WHERE id='" + report.getId() + "'";
        
        try
            {
              // create mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = url;
              String user = un;
              String password = pw;
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, user, password);
              Statement st = conn.createStatement();
              st.executeUpdate(sqlstr);
              st.close();
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());
            }
        
    }
    
    public Appointment popAppts(User userP) {
        
        try
            {
              // create mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = url;
              String user = un;
              String password = pw;
              ResultSet rs = null;
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, user, password);
              String query = "SELECT * FROM appointments WHERE active='TRUE'";
              Appointment head = null;
              Appointment newest = null;
              
              if(userP.isPatient()) {
                    query = "SELECT * FROM appointments WHERE patient='"+userP.getUserName()+"' AND active='TRUE'";
              }
              else if (userP.isDoctor()) {
                  
                    query = "SELECT * FROM appointments WHERE doctor='" + userP.getUserName() + "' AND active='TRUE'";
                  
              }

              Statement st = conn.createStatement();
              rs = st.executeQuery(query);
              while (rs.next()) {
                  
                  newest = new Appointment(rs.getInt("pkey"), rs.getDate("date"), rs.getDate("dateMade"), rs.getString("patient"), rs.getString("doctor"), rs.getString("notes"), rs.getBoolean("authorized"), this);
                  newest.llNext = head;
                  head = newest;
                  
              }
              st.close();
              
              return head;
              
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());

            }
        
        return null;
        
    }
    
    public Report popRpts(User userP) {
        
        int[] symp = new int[5];
        
        try
            {
              // create mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = url;
              String user = un;
              String password = pw;
              ResultSet rs = null;
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, user, password);
              String query = "SELECT * FROM reports";
              Report head = null;
              Report newest = null;
              
              if(userP.isPatient()) {
                    query = "SELECT * FROM reports WHERE patient='"+userP.getUserName()+"'";
              }
              else if (userP.isDoctor()) {
                  
                    query = "SELECT * FROM reports WHERE doctor='" + userP.getUserName() + "'";
                  
              }

              Statement st = conn.createStatement();
              rs = st.executeQuery(query);
              while (rs.next()) {
                  
                  symp[0] = rs.getInt("pain");
                  symp[1] = rs.getInt("drowsiness");
                  symp[2] = rs.getInt("nausea");
                  symp[3] = rs.getInt("anxiety");
                  symp[4] = rs.getInt("depression");
                  
                  newest = new Report(rs.getInt("pkey"), rs.getDate("dateTime"), rs.getString("doctor"), rs.getString("patient"), rs.getString("comments"), symp, this);
                  newest.llNext = head;
                  head = newest;
                  
              }
              st.close();
              
              return head;
              
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());

            }
        
        return null;
        
    }
    
    public User authenticate(String userName, String passHash) {
            User userprofile;
            User found = searchUser(userName);
            passHash = hash(passHash);
            if (found != null) {
                    if (passHash.equals(found.getPassHash())) {
                            userprofile = found;
                    }
                    else {
                        System.out.println("Invalid password.");
                    }
            }
            else {
                    System.out.println("Invalid username.");
            }

            return null;
        
    }
    
    public User searchUser(String userName) {
            
            User found = null;
            
            try
            {
              // create our mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = url;
              String user = un;
              String password = pw;
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, user, password);

              // our SQL SELECT query. 
              // if you only need a few columns, specify them by name instead of using "*"
              String query = "SELECT * FROM users WHERE userName='"+userName+"'";

              // create the java statement
              Statement st = conn.createStatement();

              // execute the query, and get a java resultset
              ResultSet rs = st.executeQuery(query);

              // iterate through the java resultset
              while (rs.next())
              {

                String qUserName = rs.getString("userName");
                String qPassHash = rs.getString("passHash");
                int usertype = rs.getInt("userType");
                Date lastlogin = rs.getDate("lastLogin");
                userType uType;
                if (usertype == 0) uType = userType.patient;
                else if (usertype == 1) uType = userType.doctor;
                else if (usertype == 2) uType = userType.nurse;
                else uType = userType.receptionist;
                
                found = new User(qUserName, qPassHash, uType, lastlogin);

                // print the results
                System.out.format("%s, %s, %s, %s\n", qUserName, qPassHash, usertype, lastlogin);
              }
              st.close();
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());
            }
            
            return found;
            
        }
    
    public String hash(String pass) {
        
        return pass;
        
    }
    
    public void submit(User newUser, String ph, String fn) {
        int ut;
        // String salt = RandomStringUtils.randomAlphabetic(42);
        String salt = "sdgG6D5ssf5D";
        if (newUser.isPatient()) ut = 0;
        else if (newUser.isDoctor()) ut = 1;
        else if (newUser.isNurse()) ut = 2;
        else ut = 3;
        String sqlstr = "INSERT INTO users (userName, passHash, userType, fullname, salt) VALUES ('"+ newUser.getUserName() +"', '"+ph+"', '"+ut+"', '"+fn+"', '"+salt+"')";
        
        try
            {
              // create mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = url;
              String user = un;
              String password = pw;
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, user, password);
              
              Statement st = conn.createStatement();
              st.executeUpdate(sqlstr);
              st.close();
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());
            }
        
    }
    
    public int submit(Appointment appt) {
        
        String sqlstr = "INSERT INTO appointments (patient, doctor, dateMade, date, notes, authorized, active) VALUES ('" + appt.getPatient() + "', '" + appt.getDoctor() + "', '" + appt.getTimeMade() + "', '" + appt.getAppointmentTime() + "', '" + appt.getNotes() + "', '" + appt.getAuthorized() + "', '" + appt.getActive() + "')";
        
        try
            {
              // create mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = url;
              String user = un;
              String password = pw;
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, user, password);
              ResultSet rs;
              int ret = -1;
              
              // SQL INSERT query.
              Statement st = conn.createStatement();
              st.executeUpdate(sqlstr);
              // GET NEW PRIMARY KEY
              st = conn.createStatement();
              rs = st.executeQuery("SELECT pkey FROM appointments WHERE patient='"+appt.getPatient()+"', doctor='"+appt.getDoctor()+"', date='"+appt.getAppointmentTime()+"', notes='"+appt.getNotes()+"'");
              while(rs.next()) ret = rs.getInt("pkey");
              st.close();
              return ret;
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());
            }
            return -1;
    }
    
    public int submit(Report report) {
        
        String sqlstr = "INSERT INTO reports (patient, dateTime, pain, drowsiness, nausea, anxiety, depression, comments) VALUES ('" + report.getPatient() + "', '" + report.getDateTime() + "', '" + report.getPain() + "', '" + report.getDrowsiness() + "', '" + report.getNausea() + "', '" + report.getAnxiety() + "', '" + report.getDepression() + "', '" + report.getComments() + "')";
        
        try
            {
              // create mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = url;
              String user = un;
              String password = pw;
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, user, password);
              ResultSet rs;
              int ret = -1;
              
              // SQL INSERT query.


              Statement st = conn.createStatement();
              st.executeUpdate(sqlstr);
              st = conn.createStatement();
              rs = st.executeQuery("SELECT pkey FROM reports WHERE patient='"+report.getPatient()+"', doctor='"+report.getDoctor()+"', date='"+report.getDateTime()+"'");
              while(rs.next()) ret = rs.getInt("pkey");
              st.close();
              return ret;
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());
            }
        return -1;
        
    }
    
}