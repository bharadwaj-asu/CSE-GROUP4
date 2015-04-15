package practicegui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Clinton
 */
public class CreateAppointment extends javax.swing.JFrame implements ActionListener {
    
    String patient = "";
    String doctor = "";
    String[] patients = {""};
    String[] patientIDs = {""};
    String[] doctors = {""};
    String[] doctorIDs = {""};

    public CreateAppointment(User u) {
        final User profile = u;
        //JFrame frm = new JFrame("Healthcare - Create Appointment");
        this.setMinimumSize(new Dimension(800, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.setResizable(false);
        
        try{
            String myDriver = "org.gjt.mm.mysql.Driver";
            Connection con;
            Statement st;
            Class.forName(myDriver);
            String url=null,url1=null,userID=null,password=null;
            String dbFileName=null;
            String dbFileName1=null;
            String sql=null;
            String user = "root";
            String pass = "";

            url = "jdbc:mysql://localhost:3306/test";
            con=DriverManager.getConnection(url, user, pass);
            st=con.createStatement();
            
            System.out.println();
            
            if(profile.isDoctor()) {
                ResultSet rs=st.executeQuery("SELECT fullname,userName FROM users WHERE userType='0'");
                doctor = profile.getUserName();
                int i = 0;
                rs.last();
                int numPat = rs.getRow();
                System.out.println(numPat);
                rs.beforeFirst();
                patients = new String[numPat];
                patientIDs = new String[numPat];
                while(rs.next()) {
                    patients[i] = rs.getString("fullname");
                    patientIDs[i] = rs.getString("userName");
                    System.out.println(patients[i]);
                    i++;
                }
            }
            else if(profile.isPatient()) {
                ResultSet rs=st.executeQuery("SELECT doctor FROM patientdoctorjoin WHERE patient='"+profile.getUserName()+"'");
                rs.next();
                doctor = rs.getString("doctor");
                patient = profile.getUserName();
            }
            else if (profile.isNurse()) {
                ResultSet rs=st.executeQuery("SELECT userName,fullname FROM users WHERE userType='0'");
                int i = 0;
                rs.last();
                int numPat = rs.getRow();
                System.out.println(numPat);
                rs.beforeFirst();
                patients = new String[numPat];
                patientIDs = new String[numPat];
                while(rs.next()) {
                    patients[i] = rs.getString("fullname");
                    patientIDs[i] = rs.getString("userName");
                    System.out.println(patients[i]);
                    i++;
                }
                rs=st.executeQuery("SELECT userName,fullname FROM users WHERE userType='1'");
                int j = 0;
                rs.last();
                int numDoc = rs.getRow();
                System.out.println(numPat);
                rs.beforeFirst();
                doctors = new String[numDoc];
                doctorIDs = new String[numDoc];
                while(rs.next()) {
                    doctors[j] = rs.getString("fullname");
                    doctorIDs[j] = rs.getString("userName");
                    System.out.println(doctors[j]);
                    j++;
                }
            }
            else { // receptionist
                ResultSet rs=st.executeQuery("SELECT fullname FROM users WHERE userName='"+profile.getUserName()+"'");
                rs.next();
            }
        }catch(Exception e){
            System.out.println(e);
            dispose();
        }

        JPanel upperPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        this.getContentPane().add(upperPanel, "North");
        this.getContentPane().add(lowerPanel, "South");
        JLabel dateLabel = new JLabel("Date: ");
        String[] months = {"January","February","March","April","May","June","July","August","Septempber","October","November","December"};
        final JComboBox c0 = new JComboBox(months);
        int[] days = {1,2,3,4,5,6,7,8,9,10,11,12,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        String a=Arrays.toString(days); //toString the List or Vector
        String ar[]=a.substring(1,a.length()-1).split(", ");
        final JComboBox c1 = new JComboBox(ar);
        String[] years = {"2015","2016","2017","2018","2019","2020","2021"};
        /*String a2=Arrays.toString(years); //toString the List or Vector
        String ar2[]=a2.substring(1,a.length()-1).split(", ");*/
        final JComboBox c2 = new JComboBox(years);
        JLabel timeLabel = new JLabel("Time: ");
        int[] hours = {1,2,3,4,5,6,7,8,9,10,11,12};
        String a3=Arrays.toString(hours); //toString the List or Vector
        final String ar3[]=a3.substring(1,a3.length()-1).split(", ");
        final JComboBox c3 = new JComboBox(ar3);
        String[] minutes = {"00","15","30","45"};
        final JComboBox c4 = new JComboBox(minutes);
        String[] ampm = {"am","pm"};
        final JComboBox c5 = new JComboBox(ampm);
        JLabel doctorLabel = new JLabel("Doctor: ");
        JLabel patientLabel = new JLabel("Patient: ");
        final JComboBox c6 = new JComboBox(patients);
        final JComboBox c7 = new JComboBox(doctors);
        
        
        //if (profile.isPatient()) {
            ResultSet rs;
            try{
                    String myDriver = "org.gjt.mm.mysql.Driver";
                    Connection con;
                    Statement st;
                    Class.forName(myDriver);
                    String url=null;
                    String user = "root";
                    String pass = "";

                    url = "jdbc:mysql://localhost:3306/test";
                    con=DriverManager.getConnection(url, user, pass);
                    st=con.createStatement();
                    //rs=st.executeQuery("SELECT fullname FROM users WHERE userName='"+profile.getUserName()+"'");
                    //rs.next();
            }catch(Exception e){
                System.out.println(e);
                dispose();
            }
            JLabel staticDoctor = new JLabel();
            //JLabel staticPatient = new JLabel(rs.getString("fullname"));
        //}
            
        //if (profile.isDoctor()) {
            //ResultSet rs;
            try{
                    String myDriver = "org.gjt.mm.mysql.Driver";
                    Connection con;
                    Statement st;
                    Class.forName(myDriver);
                    String url=null;
                    String user = "root";
                    String pass = "";

                    url = "jdbc:mysql://localhost:3306/test";
                    con=DriverManager.getConnection(url, user, pass);
                    st=con.createStatement();
                    //rs=st.executeQuery("SELECT fullname FROM users WHERE userName='"+profile.getUserName()+"'");
                    //rs.next();
            }catch(Exception e){
                System.out.println(e);
                dispose();
            }
            //JLabel staticDoctor = new JLabel("");
            //JComboBox patientList = new JComboBox();
        //}
        JLabel label = new JLabel("Create Appointment");
        
        upperPanel.add(label);
        lowerPanel.add(dateLabel);
        lowerPanel.add(c0);
        lowerPanel.add(c1);
        lowerPanel.add(c2);
        lowerPanel.add(timeLabel);
        lowerPanel.add(c3);
        lowerPanel.add(c4);
        lowerPanel.add(c5);
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener()
        {
          
          public void actionPerformed(ActionEvent e)
          {
            Date now = Calendar.getInstance().getTime();
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String nowStr = df.format(now);
            String mes = String.format("%02d", c0.getSelectedIndex()+1);
            String dia = String.format("%02d", c1.getSelectedIndex()+1);
            String fullApptDate = "2015" + mes + dia;
            int amPmOffset;
            if (c5.getSelectedIndex()==0) amPmOffset = 1; 
            else amPmOffset = 13;
            int horaInt = c3.getSelectedIndex() + amPmOffset;
            if (c3.getSelectedIndex()==12&&c5.getSelectedIndex()==0) horaInt = 0;
            if (c3.getSelectedIndex()==12&&c5.getSelectedIndex()==1) horaInt = 12;
            String hora = String.format("%02d", horaInt);
            fullApptDate = fullApptDate + hora + c4.getSelectedItem().toString() + "00";
            int patIndex = c6.getSelectedIndex();
            int docIndex = c7.getSelectedIndex();
            if (!profile.isPatient()) patient = patientIDs[patIndex];
            else patient = profile.getUserName();
            if (!profile.isDoctor() && !profile.isPatient()) doctor = doctorIDs[docIndex];
            System.out.println(patient);
            System.out.println(doctor);
            try{
                    String myDriver = "org.gjt.mm.mysql.Driver";
                    Connection con;
                    Statement st;
                    Class.forName(myDriver);
                    String url=null;
                    String user = "root";
                    String pass = "";

                    url = "jdbc:mysql://localhost:3306/test";
                    con=DriverManager.getConnection(url, user, pass);
                    st=con.createStatement();
                    st.executeUpdate("INSERT INTO appointments (patient, doctor, date, dateMade) VALUES ('"+patient+"', '"+doctor+"', '"+fullApptDate+"', '"+nowStr+"')");
                    
            }catch(Exception e2){
                System.out.println(e2);
                dispose();
            }
            JFrame ca = new HomeScreen(profile);
            ca.show();
          }
        });
        lowerPanel.add(submit);
        
        if (!profile.isPatient() && !profile.isDoctor()) {
            lowerPanel.add(doctorLabel);
            lowerPanel.add(staticDoctor);
        }
        
        if (!profile.isPatient()) {

        
            lowerPanel.add(patientLabel);
            lowerPanel.add(c6);
        }
        
        this.setSize(800,500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        dispose();
        this.show();
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}