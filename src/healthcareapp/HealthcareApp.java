/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package healthcareapp;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class HealthcareApp {

public static User userprofile;

    public static void main(String[] args) {
        
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/test";
        String user = "testuser";
        String password = "test623";
        
        Scanner reader;

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }
            
            rs = st.executeQuery("SELECT name FROM testTable WHERE pKey = 0");
            
            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(HealthcareApp.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(HealthcareApp.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }

		String username, passHash;
		boolean quit = false;
		boolean loggedIn = false;
		reader = new Scanner(System.in);
                System.out.println("Welcome to Healthcare!");
		while (quit == false) {

			while (loggedIn == false/*profile == NULL*/) {
				System.out.print("Please enter your username: ");
				username = reader.nextLine();
				System.out.print("Please enter your password: ");
				passHash = reader.nextLine();
				loggedIn = authenticate(username, passHash);
			}
                        
                        System.out.println(userprofile.typeOfUser);
			
			menu();
			
		}

    }

	public static boolean authenticate(String userName, String passHash) {
		User found = searchUser(userName);
		passHash = hash(passHash);
		if (found != null) {
			if (passHash.equals(found.getPassHash())) {
                                userprofile = found;
				return true;
			}
		}
		else {
			System.out.println("Invalid username.");
		}
		
		return false;
		
	}
	
	public static String hash (String str) { // TO BE DONE
	
		return str;
	
	}
	
	public static void menu() {
            
                String input;
                Scanner sc = new Scanner(System.in);
                
                String thirdOption;
                
                if(userprofile.isPatient()) { thirdOption = "3 - Submit symptom report";}
                
                //if(userprofile.isDoctor()) { 
                else {
                    thirdOption = "3 - Make appointment";
                }
	
		if (userprofile.getType() == userType.patient) {
		
			System.out.println("1 - View reports\n2 - View appointments");
                        System.out.println(thirdOption);
			input = sc.nextLine().trim();
			
			if (input.equals("1")) {
				// show all reports
				viewReports();
				//System.out.println("1 - Submit report\n2 - Back");
				//input = sc.nextLine().trim();
				//if (input.equals("1")) submitReport();
				menu();
			}
			else if (input.trim().equals("2")) {
                            System.out.println("Why me");
				// show all appointments
				viewAppointments();
				menu();
			}
                        else if (input.trim().equals("3")) {
                            if (userprofile.isPatient()) {
                                submitReport();
                            }
                            else {
                                makeAppointment();
                            }
                        }
			else {
				System.out.println("Invalid input.");
				menu();
			}
		
		}
		else if (userprofile.getType() == userType.doctor) {
		
			System.out.println("1 - View reports\n2 - View appointments");
                        System.out.println(thirdOption);
			input = sc.nextLine().trim();
			
			if (input.equals("1")) {
				// show all reports
				viewReports();
				menu();
			}
			else if (input.equals("2")) {
				// show all appointments
				viewAppointments();
				//System.out.println("1 - Make appointment\n2 - Back");
				// input = 
				//if (input.equals("1")) makeAppointment();
				menu();
			}
			else if (input.equals("3")){
				makeAppointment();
				menu();
			}
                        else {
                            
                                System.out.println("Invalid input.");
                                menu();
                            
                        }
		
		}
	
	}
        
        public static void viewAppointments() {
            
            try
            {
              // create our mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = "jdbc:mysql://localhost:3306/test";
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, "root", "");

              // our SQL SELECT query. 
              // if you only need a few columns, specify them by name instead of using "*"
              String query;
              // System.out.println(userprofile.isPatient());
              System.out.println(userprofile.getUserName());
              if (userprofile.isPatient()) {
                  query = "SELECT * FROM appointments WHERE patient='"+userprofile.getUserName()+"'";
                  System.out.println(userprofile.getUserName());
                  //System.out.println("Test...PatientOK");
              }
              else query = "SELECT * FROM appointments WHERE doctor='"+userprofile.getUserName()+"'";
              
              // System.out.println(query);

              // create the java statement
              Statement st = conn.createStatement();

              // execute the query, and get a java resultset
              ResultSet rs = st.executeQuery(query);
              
              if (userprofile.isDoctor()) {

                // iterate through the java resultset
                while (rs.next())
                {
                  Date date = rs.getDate("date");
                  String patKey = rs.getString("patient");
                  // doctor = SELECT fullname FROM users WHERE userName='docKey'
                  query = "SELECT fullname FROM users WHERE userName='"+patKey+"'";
                  Statement st2 = conn.createStatement();
                  ResultSet rs2 = st2.executeQuery(query);
                  rs2.next();
                  String patient = rs2.getString("fullname");
                  st2.close();
                  // print the results
                  System.out.format("%s, with patient %s\n", date, patient);
                }
              }
              else {
                  while (rs.next())
                  {
                      Date date = rs.getDate("date");
                      String docKey = rs.getString("patient");
                      // doctor = SELECT fullname FROM users WHERE userName='docKey'
                      query = "SELECT fullname FROM users WHERE userName='"+docKey+"'";
                      Statement st2 = conn.createStatement();
                      ResultSet rs2 = st2.executeQuery(query);
                      rs2.next();
                      String doctor = rs2.getString("fullname");
                      // print the results
                      System.out.format("%s, with Doctor %s\n", date, doctor);
                  }
              }
              
              st.close();
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());
            }
            
        }
        
        public static void makeAppointment () {
            
            Scanner reader = new Scanner(System.in);
            
            System.out.println("Enter the patient's full name: ");
            String patient = reader.nextLine();
            
            try
            {
              // create mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = "jdbc:mysql://localhost:3306/test";
              String user = "testuser";
              String password = "test623";
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, user, password);
              
              //DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
              Date date = new Date();
              
              //String date = df.format(d);
              SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddhhmmss");
              
              String query = "SELECT userName FROM users WHERE fullname='"+patient+"'";

              Statement st = conn.createStatement();
              ResultSet rs = st.executeQuery(query);
              rs.next();
              patient = rs.getString("userName");
              st.close();
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());

            }

            System.out.println("Enter the time of the appointment, in the form hhmmss : ");
            String time = reader.nextLine();
            System.out.println("Enter the date of the appointment, in the form yyyyMMdd : ");
            String date = reader.nextLine();
            date = date + time;
            
            try
            {
              // create mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = "jdbc:mysql://localhost:3306/test";
              String user = "testuser";
              String password = "test623";
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, user, password);
              
              String query = "INSERT INTO appointments (patient, doctor, date) VALUES ('"+patient+"','"+userprofile.getUserName()+"',"+date+")";

              Statement st = conn.createStatement();
              st.executeUpdate(query);
              st.close();
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());

            }
            
        }
        
        public static User searchUser(String userName) {
            
            User found = null;
            
            try
            {
              // create our mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = "jdbc:mysql://localhost:3306/test";
              String user = "testuser";
              String password = "test623";
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
                else uType = userType.doctor;
                
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
        
        public static void viewReports() {
            
            try
            {
              // create our mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = "jdbc:mysql://localhost:3306/test";
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, "root", "");

              // our SQL SELECT query. 
              // if you only need a few columns, specify them by name instead of using "*"
              String query;
              // create the java statement
              Statement st = conn.createStatement();

              // execute the query, and get a java resultset
              ResultSet rs;
              // System.out.println(userprofile.isPatient());
              System.out.println(userprofile.getUserName());
              Date date;
              int pain, drowsiness, nausea, anxiety, depression;
              String comments;
              if (userprofile.isPatient()) {
                  query = "SELECT * FROM reports WHERE patient='"+userprofile.getUserName()+"' ORDER BY dateTime ASC";
                  System.out.println(userprofile.getUserName());
                  //System.out.println("Test...PatientOK");
                  rs = st.executeQuery(query);
                  while (rs.next())
                    {
                      date = rs.getDate("dateTime");
                      pain = rs.getInt("pain");
                      drowsiness = rs.getInt("drowsiness");
                      nausea = rs.getInt("nausea");
                      anxiety = rs.getInt("anxiety");
                      depression = rs.getInt("depression");
                      comments = rs.getString("comments");
                      System.out.format("%s, pain: %d, drowsiness: %d, nausea: %d, anxiety: %d, depression: %d, Comments: %s\n", date, pain, drowsiness, nausea, anxiety, depression, comments);
                    }
              }
              else {
                  
                  query = "SELECT * FROM patientdoctorjoin WHERE doctor='"+userprofile.getUserName()+"'";
                  System.out.println(query);
                  rs = st.executeQuery(query);
                  ResultSet rs2;
                  String patient, patientName;
                  while (rs.next()) {
                      patient = rs.getString("patient");
                      query = "SELECT fullname FROM users WHERE userName='"+patient+"'";
                      System.out.println(query);
                      rs2 = st.executeQuery(query);
                      rs2.next();
                      patientName = rs2.getString("fullname");
                      query = "SELECT * FROM reports WHERE patient='"+patient+"' ORDER BY dateTime ASC";
                      System.out.println(query);
                      rs2 = st.executeQuery(query);
                      while (rs2.next()) {
                          date = rs2.getDate("dateTime");
                          pain = rs2.getInt("pain");
                          drowsiness = rs2.getInt("drowsiness");
                          nausea = rs2.getInt("nausea");
                          anxiety = rs2.getInt("anxiety");
                          depression = rs2.getInt("depression");
                          comments = rs2.getString("comments");
                          System.out.format("%s, %s, pain: %d, drowsiness: %d, nausea: %d, anxiety: %d, depression: %d, Comments: %s\n", date, patientName, pain, drowsiness, nausea, anxiety, depression, comments);
                      }
                  }
              }
              st.close();
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());
            }
            
        }
        
        public static void submitReport() {
            
            int pain, drowsiness, nausea, anxiety, depression;
            
            System.out.println("Enter the severity of your symptoms from 1-10.");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter severity of Pain: ");
            pain = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter severity of Drowsiness: ");
            drowsiness = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter severity of Nausea: ");
            nausea = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter severity of Anxiety: ");
            anxiety = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter severity of Depression: ");
            depression = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter any further comments.");
            String comments = sc.nextLine();
            
            try
            {
              // create mysql database connection
              String myDriver = "org.gjt.mm.mysql.Driver";
              String myUrl = "jdbc:mysql://localhost:3306/test";
              String user = "testuser";
              String password = "test623";
              Class.forName(myDriver);
              Connection conn = DriverManager.getConnection(myUrl, user, password);
              
              //DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
              Date date = new Date();
              
              //String date = df.format(d);
              SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddhhmmss");
              
              // SQL INSERT query. 

              String query = "INSERT INTO reports (patient, dateTime, pain, drowsiness, nausea, anxiety, depression, comments) VALUES ('"+userprofile.getUserName()+"',"+dt.format(date)+","+pain+","+drowsiness+","+nausea+","+anxiety+","+depression+",'"+comments+"')";

              Statement st = conn.createStatement();
              st.executeUpdate(query);
              st.close();
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());
            }
            
            // INSERT INTO reports (pain, drowsiness, nausea, anxiety, depression) VALUES ("+pain+","+drowsiness+","+nausea+","+anxiety+","+depression+","+comments)
            
        }
        
}