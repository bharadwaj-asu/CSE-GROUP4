/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package healthcareapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;

/**
 *
 * @author Clinton
 */
public class Appointment {
    
    public static final String URL="jdbc:mysql://localhost:3306/test";
    public static final String USERNAME="root";
    public static final String PASSWORD="";
    private int id;
    private Date timeMade;
    private Date appointmentTime;
    private String patient;
    private String doctor;
    private Report patientReport;
    private Appointment followUp;
    private String notes = "";
    private boolean authorized;
    private boolean active;
    public Appointment llNext;
    DatabaseController dao;
    
    public Appointment(Date newTimeMade, Date newApptTime, String nPatient, String nDoctor, String nNotes, boolean auth, boolean act, DatabaseController ctrl) {
        
        timeMade = newTimeMade;
        appointmentTime = newApptTime;
        patient = nPatient;
        doctor = nDoctor;
        notes = nNotes;
        authorized = auth;
        active = act;
        dao = ctrl;
        
        id = dao.submit(this);
        
    }
    
    public Appointment(int newId, Date newTimeMade, Date newApptTime, String nPatient, String nDoctor, String nNotes, boolean auth, boolean act, DatabaseController ctrl) {
        
        id = newId;
        timeMade = newTimeMade;
        appointmentTime = newApptTime;
        patient = nPatient;
        doctor = nDoctor;
        notes = nNotes;
        authorized = auth;
        active = act;
        dao = ctrl;
        
    }
    
    public Appointment(int newId, Date newTimeMade, Date newApptTime, String nPatient, String nDoctor, String nNotes, boolean auth, DatabaseController ctrl) {
        
        id = newId;
        timeMade = newTimeMade;
        appointmentTime = newApptTime;
        patient = nPatient;
        doctor = nDoctor;
        notes = nNotes;
        authorized = auth;
        active = true;
        dao = ctrl;
        
    }
    
    public int getId() {
        
        return id;
        
    }
    
    public boolean submit() {
        
        Connection con = null;
        String url = URL;
        String user = USERNAME;
        String password = PASSWORD;

        try {
             con = DriverManager.getConnection(url, user, password);
             Statement st = (Statement) con.createStatement(); 

             st.executeUpdate("INSERT INTO appointments (patient, doctor, date, dateMade) VALUES (" + patient + ", " + doctor + ", " + appointmentTime + ", " + timeMade + ")");
             String str = "SELECT LAST_INSERT_ID()";
             ResultSet rs = st.executeQuery(str);
             id = rs.getInt("pkey");
             con.close();
             
             return true;
             
        }

        catch (SQLException ex) {
             System.out.println("Error");

        }
        
        return false;
        
    }
    
    public void setAppointmentTime(Date date) {
        
        appointmentTime = date;
        update();
        
    }
    
    public boolean authorize () {
        
        authorized = true;
        update();
        return authorized;
        
    }
    
    public void setActive (boolean nActive) {
        
        active = nActive;
        update();
        
    }

    public void setFollowUp(Appointment newFollowUp) {
        
        followUp = newFollowUp;
        update();
        
    }
    
    public void setNotes (String str) {
        
        Date thisDate = new Date();
        notes = notes + "New note on " + thisDate + ": " + str;
        
    }
    
    public Date getTimeMade() {
        
        return timeMade;
        
    }
    
    public Date getAppointmentTime() {
        
        return appointmentTime;
        
    }
    
    public String getPatient() {
        
        return patient;
        
    }
    
    public String getDoctor() {
        
        return doctor;
        
    }
    
    public Report getPatientReport() {
        
        return patientReport;
        
    }
    
    public Appointment getFollowUp() {
        
        return followUp;
        
    }
    
    public boolean getAuthorized() {
        
        return authorized;
        
    }
    
    public boolean getActive() {
        
        return active;
        
    }
    
    public String getNotes() {
        
        return notes;
        
    }
    
    private void update() {
        
        dao.update(this);
        
    }
    
}
