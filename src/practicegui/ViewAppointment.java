package practicegui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Clinton
 */
public class ViewAppointment extends javax.swing.JFrame implements ActionListener {
    
    String patient = "cdjarboe";
    String doctor = "cjarbo2";
    User profile = null;

    public ViewAppointment(User u, final int apptid) {
        profile = u;
        //JFrame frm = new JFrame("Healthcare - Create Appointment");
        this.setMinimumSize(new Dimension(800, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.setResizable(false);
        
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JPanel upperPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        this.getContentPane().add(upperPanel);
        this.getContentPane().add(middlePanel);
        this.getContentPane().add(lowerPanel);
        this.getContentPane().add(bottomPanel);
        
        lowerPanel.setLayout(new BoxLayout(lowerPanel,BoxLayout.LINE_AXIS));
        
        JLabel label = new JLabel("View Appointment");
        JLabel madeLabel = new JLabel("Appointment made: ");
        JLabel timeLabel = new JLabel("  Time: ");
        JLabel dateLabel = new JLabel("  Date: ");
        
        Date date = new Date();
        Date dateMade = new Date();
        String patientID = "";
        String doctorID = "";
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
                ResultSet rs = st.executeQuery("SELECT patient FROM appointments WHERE pkey='"+apptid+"'");
                
                rs.next();
                
                
                //System.out.println(rs.getString("doctor"));
                patientID = rs.getString(1);
                rs = st.executeQuery("SELECT patient,doctor,date,dateMade FROM appointments WHERE pkey='"+apptid+"'");
                rs.next();
                doctorID = rs.getString("doctor");
                date = rs.getDate("date");
                dateMade = rs.getDate("dateMade");
                Format formatter = new SimpleDateFormat("dd MMMM yyyy");
                String dm = formatter.format(dateMade);
                madeLabel.setText(madeLabel.getText()+" "+dm);
                //patient = rs.getString("patient");
               //doctor = rs.getString("doctor");
                rs = st.executeQuery("SELECT fullname FROM users WHERE userName='"+patientID+"'");
                rs.next();
                patient = rs.getString("fullname");
                rs = st.executeQuery("SELECT fullname FROM users WHERE userName='"+doctorID+"'");
                rs.next();
                doctor = rs.getString("fullname");
                
                // System.out.println(rs.getString("patient") + " " + rs.getString("doctor"));
        }catch(Exception e2){
            System.out.println(e2);
            dispose();
        }
        Format formatter = new SimpleDateFormat("MMMM");
        String selectMonth = formatter.format(date);
        upperPanel.add(label);
        formatter = new SimpleDateFormat("d");
        String selectDay = formatter.format(date);
        formatter = new SimpleDateFormat("yyyy");
        String selectYear = formatter.format(date);
        formatter = new SimpleDateFormat("h");
        String selectHour = formatter.format(date);
        formatter = new SimpleDateFormat("mm");
        String selectMin = formatter.format(date);
        formatter = new SimpleDateFormat("a");
        String selectAmpm = formatter.format(date);
        selectAmpm = selectAmpm.toLowerCase();
        
        middlePanel.add(madeLabel);

        String[] months = {"January","February","March","April","May","June","July","August","Septempber","October","November","December"};
        final JComboBox c0 = new JComboBox(months);
        c0.setSelectedItem(selectMonth);
        int[] days = {1,2,3,4,5,6,7,8,9,10,11,12,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        String a=Arrays.toString(days); //toString the List or Vector
        String ar[]=a.substring(1,a.length()-1).split(", ");
        final JComboBox c1 = new JComboBox(ar);
        c1.setSelectedItem(selectDay);
        String[] years = {"2015","2016","2017","2018","2019","2020","2021"};
        /*String a2=Arrays.toString(years); //toString the List or Vector
        String ar2[]=a2.substring(1,a.length()-1).split(", ");*/
        final JComboBox c2 = new JComboBox(years);
        c2.setSelectedItem(selectYear);
        int[] hours = {1,2,3,4,5,6,7,8,9,10,11,12};
        String a3=Arrays.toString(hours); //toString the List or Vector
        final String ar3[]=a3.substring(1,a3.length()-1).split(", ");
        final JComboBox c3 = new JComboBox(ar3);
        c3.setSelectedItem(selectHour);
        String[] minutes = {"00","15","30","45"};
        final JComboBox c4 = new JComboBox(minutes);
        c4.setSelectedItem(selectMin);
        String[] ampm = {"am","pm"};
        final JComboBox c5 = new JComboBox(ampm);
        c5.setSelectedItem(selectAmpm);
        JLabel doctorLabel = new JLabel("Doctor: "+doctor);
        JLabel patientLabel = new JLabel("Patient: "+patient);
        middlePanel.add(patientLabel);
        middlePanel.add(doctorLabel);
        
        lowerPanel.add(timeLabel);
        lowerPanel.add(c0);
        lowerPanel.add(c1);
        lowerPanel.add(c2);
        lowerPanel.add(dateLabel);
        lowerPanel.add(c3);
        lowerPanel.add(c4);
        lowerPanel.add(c5);
        
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener()
        {
          
          public void actionPerformed(ActionEvent e)
          {
            /*Date now = Calendar.getInstance().getTime();
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String nowStr = df.format(now);*/
            
            String mes = String.format("%02d", c0.getSelectedIndex()+1);
            String dia = String.format("%02d", c1.getSelectedIndex()+1);
            String fullApptDate = "2015" + mes + dia;
            int amPmOffset;
            if (c5.getSelectedIndex()==0) amPmOffset = 1; 
            else amPmOffset = 13;
            int horaInt = c3.getSelectedIndex() + amPmOffset;
            String hora = String.format("%02d", horaInt);
            fullApptDate = fullApptDate + hora + c4.getSelectedItem().toString() + "00";

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
                    st.executeUpdate("UPDATE appointments SET date='"+fullApptDate+"' WHERE pkey='"+apptid+"'");
                    
            }catch(Exception e2){
                System.out.println(e2);
                dispose();
            }

            JFrame hs = new HomeScreen(profile);
            
            hs.show();
          }
        });
        bottomPanel.add(submit);
        
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