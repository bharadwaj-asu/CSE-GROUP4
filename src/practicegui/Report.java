/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practicegui;

import static java.lang.Math.sqrt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author Clinton
 */
public class Report {
    
    int id;
    private Appointment appointment;
    private boolean viewed;
    private Date dateTime;
    private String doctor;
    private String patient;
    private int[] symptoms;
    private String comments;
    private String doctorSuggestion;
    public Report llNext;
    private DatabaseController dao;
    
    Report(String nDoctor, String nPatient, String nComments, int[] nSymptoms, DatabaseController ctrl) {
        
        Date newDate = new Date();
        dateTime = newDate;
        viewed = false;
        doctor = nDoctor;
        patient = nPatient;
        symptoms = nSymptoms;
        comments = nComments;
        id = dao.submit(this);
        dao = ctrl;
        
    }
    
    Report(int pkey, Date nDate, String nDoctor, String nPatient, String nComments, int[] nSymptoms, DatabaseController ctrl) {
        
        id = pkey;
        dateTime = nDate;
        viewed = false;
        doctor = nDoctor;
        patient = nPatient;
        symptoms = nSymptoms;
        comments = nComments;
        dao = ctrl;
        
    }
    
    public int getId() {
        
        return id;
        
    }
    
    public boolean isViewed() {
        
        return viewed;
        
    }
    
    public static double evaluate(int pa, int dr, int na, int de, int an) {
        double pain = 0;
        double drowsiness = 0;
        double nausea = 0;
        double depression = 0;
        double anxiety = 0;
        double sd = 0;
        double symptomComp = 0;
        double repSymptomComp = 0;
        //System.out.println("evaluating");
        try{
            
            String myDriver = "org.gjt.mm.mysql.Driver";
            Class.forName(myDriver);
            String url=null;
            String user = "root";
            String pass = "";

            url = "jdbc:mysql://localhost:3306/test";
            Connection con;
            Statement st;
            
            con=DriverManager.getConnection(url, user, pass);
            st=con.createStatement();
            ResultSet rs = st.executeQuery("SELECT pain, drowsiness, nausea, depression, anxiety FROM reports");
            int i = 0;
            //System.out.println("preparing to calculate mean");
            while(rs.next()) {
                pain = pain + rs.getInt("pain");
                drowsiness = drowsiness + rs.getInt("drowsiness");
                nausea = nausea + rs.getInt("nausea");
                depression = depression +  + rs.getInt("depression");
                anxiety = anxiety +  + rs.getInt("anxiety");
                i++;
            }
            
            symptomComp = pain + drowsiness + nausea + depression + anxiety;
            symptomComp = symptomComp/(5*i);
            rs = st.executeQuery("SELECT pain, drowsiness, nausea, depression, anxiety FROM reports");
            int repTot = 0;
            int sdSum = 0;
            while(rs.next()) {
                repTot = rs.getInt("pain") + rs.getInt("drowsiness") + rs.getInt("nausea") + rs.getInt("depression") + rs.getInt("anxiety");
                sdSum = sdSum + repTot;
            }
            repTot = repTot/i;
            sd = sqrt(repTot);
            // System.out.println("sd: " + sd);
        }
        catch(Exception e){
            System.out.println(e);
            //dispose();
        }
        double z = 0;
        repSymptomComp = (pa+dr+na+an+de)/5;
        if(sd!=0) z = (repSymptomComp - symptomComp) / sd;
        return z;
    }

    public Date getDateTime() {
        
        return dateTime;
        
    }
    
    public String getDoctor() {
        
        return doctor;
        
    }
    
    public String getPatient() {
        
        return patient;
        
    }
    
    public int getPain() {
        
        return symptoms[0];
        
    }
    
    public int getDrowsiness() {
        
        
        return symptoms[1];
    }
    
    public int getNausea() {
        
        return symptoms[2];
        
    }
    
    public int getAnxiety() {
        
        return symptoms[3];
        
    }
    
    public int getDepression() {
        
        return symptoms[4];
        
    }
    
    public String getComments() {
        
        return comments;
        
    }
    
    public String getSuggestion() {
        
        return doctorSuggestion;
        
    }
    
    public void setSuggestion(String suggestion) {
    
        doctorSuggestion = suggestion;
        update();
    
    }
    
    public void setAppointment(Appointment appt) {
        
        appointment = appt;
        update();
        
    }
    
    public void view() {
        
        viewed = true;
        
    }
    
    public void submit() {
        
        dao.submit(this);
        
    }
    
    private void update() {
        
        dao.update(this);
        
    }
    
}