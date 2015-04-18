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
import java.util.Calendar;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

/**
 *
 * @author Clinton
 */
public class CreateReport extends javax.swing.JFrame implements ActionListener {
    
    final String patient;
    final String doctor;
    User profile = null;
    static JRadioButton b00,b01,b02,b03,b04,b05,b06,b07,b08,b09;
    static JRadioButton b10,b11,b12,b13,b14,b15,b16,b17,b18,b19;
    static JRadioButton b20,b21,b22,b23,b24,b25,b26,b27,b28,b29;
    static JRadioButton b30,b31,b32,b33,b34,b35,b36,b37,b38,b39;
    static JRadioButton b40,b41,b42,b43,b44,b45,b46,b47,b48,b49;
    
    public static int getPain() {
        // System.out.println("getting pain");
        int pain = 0;
        if (b00.isSelected()) pain = 1;
        else if (b01.isSelected()) pain = 2;
        else if (b02.isSelected()) pain = 3;
        else if (b03.isSelected()) pain = 4;
        else if (b04.isSelected()) pain = 5;
        else if (b05.isSelected()) pain = 6;
        else if (b06.isSelected()) pain = 7;
        else if (b07.isSelected()) pain = 8;
        else if (b08.isSelected()) pain = 9;
        else if (b09.isSelected()) pain = 10;
        // System.out.println("pain got");
        return pain;
    }
    public static int getDrowsiness() {
        int drowsiness = 0;
        if (b10.isSelected()) drowsiness = 1;
        else if (b11.isSelected()) drowsiness = 2;
        else if (b12.isSelected()) drowsiness = 3;
        else if (b13.isSelected()) drowsiness = 4;
        else if (b14.isSelected()) drowsiness = 5;
        else if (b15.isSelected()) drowsiness = 6;
        else if (b16.isSelected()) drowsiness = 7;
        else if (b17.isSelected()) drowsiness = 8;
        else if (b18.isSelected()) drowsiness = 9;
        else if (b19.isSelected()) drowsiness = 10;
        // System.out.println("drwos got");
        return drowsiness;
    }
    public static int getNausea() {
        int nausea = 0;
        if (b20.isSelected()) nausea = 1;
        else if (b21.isSelected()) nausea = 2;
        else if (b22.isSelected()) nausea = 3;
        else if (b23.isSelected()) nausea = 4;
        else if (b24.isSelected()) nausea = 5;
        else if (b25.isSelected()) nausea = 6;
        else if (b26.isSelected()) nausea = 7;
        else if (b27.isSelected()) nausea = 8;
        else if (b28.isSelected()) nausea = 9;
        else if (b29.isSelected()) nausea = 10;
        // System.out.println("nausea");
        return nausea;
    }
    public static int getAnxiety() {
        int anxiety = 0;
        if (b30.isSelected()) anxiety = 1;
        else if (b31.isSelected()) anxiety = 2;
        else if (b32.isSelected()) anxiety = 3;
        else if (b33.isSelected()) anxiety = 4;
        else if (b34.isSelected()) anxiety = 5;
        else if (b35.isSelected()) anxiety = 6;
        else if (b36.isSelected()) anxiety = 7;
        else if (b37.isSelected()) anxiety = 8;
        else if (b38.isSelected()) anxiety = 9;
        else if (b39.isSelected()) anxiety = 10;
        // System.out.println("anxietty");
        return anxiety;
    }
    public static int getDepression() {
        int depression = 0;
        if (b40.isSelected()) depression = 1;
        else if (b41.isSelected()) depression = 2;
        else if (b42.isSelected()) depression = 3;
        else if (b43.isSelected()) depression = 4;
        else if (b44.isSelected()) depression = 5;
        else if (b45.isSelected()) depression = 6;
        else if (b46.isSelected()) depression = 7;
        else if (b47.isSelected()) depression = 8;
        else if (b48.isSelected()) depression = 9;
        else if (b49.isSelected()) depression = 10;
        // System.out.println("Depression got");
        return depression;
    }

    public CreateReport(User u) {
        profile = u;
        String doctor2;
        patient = profile.getUserName();
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
                ResultSet rs = st.executeQuery("SELECT doctor FROM patientdoctorjoin WHERE patient = '"+profile.getUserName()+"'");
                rs.next();
                doctor2 = rs.getString("doctor");
        }catch(Exception e2){
            System.out.println(e2);
            dispose();
            doctor2 = "";
        }
        
        doctor = doctor2;
        //JFrame frm = new JFrame("Healthcare - Create Appointment");
        this.setMinimumSize(new Dimension(800, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.setResizable(false);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        JPanel topPanel = new JPanel();
        JPanel upperPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        this.getContentPane().add(topPanel);
        this.getContentPane().add(upperPanel);
        this.getContentPane().add(middlePanel);
        this.getContentPane().add(lowerPanel);
        this.getContentPane().add(bottomPanel);
        //JPanel lowerPanel2 = new JPanel();
        //this.getContentPane().add(upperPanel);
        //this.getContentPane().add(lowerPanel2, "South");
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.PAGE_AXIS));
        
        JLabel label = new JLabel("Create Report");
        
        topPanel.add(label);
        
        JLabel s0 = new JLabel("Pain: ");
        JLabel s1 = new JLabel("Nausea: ");
        JLabel s2 = new JLabel("Drowsiness: ");
        JLabel s3 = new JLabel("Depression: ");
        JLabel s4 = new JLabel("Anxiety: ");
        
        final ButtonGroup bg0 = new ButtonGroup();
        final ButtonGroup bg1 = new ButtonGroup();
        final ButtonGroup bg2 = new ButtonGroup();
        final ButtonGroup bg3 = new ButtonGroup();
        final ButtonGroup bg4 = new ButtonGroup();
        
        b00 = new JRadioButton("1");
        b01 = new JRadioButton("2");
        b02 = new JRadioButton("3");
        b03 = new JRadioButton("4");
        b04 = new JRadioButton("5");
        b05 = new JRadioButton("6");
        b06 = new JRadioButton("7");
        b07 = new JRadioButton("8");
        b08 = new JRadioButton("9");
        b09 = new JRadioButton("10");
        bg0.add(b00);
        bg0.add(b01);
        bg0.add(b02);
        bg0.add(b03);
        bg0.add(b04);
        bg0.add(b05);
        bg0.add(b06);
        bg0.add(b07);
        bg0.add(b08);
        bg0.add(b09);
        upperPanel.add(s0);
        upperPanel.add(b00);
        upperPanel.add(b01);
        upperPanel.add(b02);
        upperPanel.add(b03);
        upperPanel.add(b04);
        upperPanel.add(b05);
        upperPanel.add(b06);
        upperPanel.add(b07);
        upperPanel.add(b08);
        upperPanel.add(b09);
        
        
        
        
        b10 = new JRadioButton("1");
        b11 = new JRadioButton("2");
        b12 = new JRadioButton("3");
        b13 = new JRadioButton("4");
        b14 = new JRadioButton("5");
        b15 = new JRadioButton("6");
        b16 = new JRadioButton("7");
        b17 = new JRadioButton("8");
        b18 = new JRadioButton("9");
        b19 = new JRadioButton("10");
        bg1.add(b10);
        bg1.add(b11);
        bg1.add(b12);
        bg1.add(b13);
        bg1.add(b14);
        bg1.add(b15);
        bg1.add(b16);
        bg1.add(b17);
        bg1.add(b18);
        bg1.add(b19);
        
        b20 = new JRadioButton("1");
        b21 = new JRadioButton("2");
        b22 = new JRadioButton("3");
        b23 = new JRadioButton("4");
        b24 = new JRadioButton("5");
        b25 = new JRadioButton("6");
        b26 = new JRadioButton("7");
        b27 = new JRadioButton("8");
        b28 = new JRadioButton("9");
        b29 = new JRadioButton("10");
        bg2.add(b20);
        bg2.add(b21);
        bg2.add(b22);
        bg2.add(b23);
        bg2.add(b24);
        bg2.add(b25);
        bg2.add(b26);
        bg2.add(b27);
        bg2.add(b28);
        bg2.add(b29);
        
        b30 = new JRadioButton("1");
        b31 = new JRadioButton("2");
        b32 = new JRadioButton("3");
        b33 = new JRadioButton("4");
        b34 = new JRadioButton("5");
        b35 = new JRadioButton("6");
        b36 = new JRadioButton("7");
        b37 = new JRadioButton("8");
        b38 = new JRadioButton("9");
        b39 = new JRadioButton("10");
        bg3.add(b30);
        bg3.add(b31);
        bg3.add(b32);
        bg3.add(b33);
        bg3.add(b34);
        bg3.add(b35);
        bg3.add(b36);
        bg3.add(b37);
        bg3.add(b38);
        bg3.add(b39);
        
        b40 = new JRadioButton("1");
        b41 = new JRadioButton("2");
        b42 = new JRadioButton("3");
        b43 = new JRadioButton("4");
        b44 = new JRadioButton("5");
        b45 = new JRadioButton("6");
        b46 = new JRadioButton("7");
        b47 = new JRadioButton("8");
        b48 = new JRadioButton("9");
        b49 = new JRadioButton("10");
        bg4.add(b40);
        bg4.add(b41);
        bg4.add(b42);
        bg4.add(b43);
        bg4.add(b44);
        bg4.add(b45);
        bg4.add(b46);
        bg4.add(b47);
        bg4.add(b48);
        bg4.add(b49);
        
        upperPanel.add(s1);
        upperPanel.add(b10);
        upperPanel.add(b11);
        upperPanel.add(b12);
        upperPanel.add(b13);
        upperPanel.add(b14);
        upperPanel.add(b15);
        upperPanel.add(b16);
        upperPanel.add(b17);
        upperPanel.add(b18);
        upperPanel.add(b19);
        
        middlePanel.add(s2);
        middlePanel.add(b20);
        middlePanel.add(b21);
        middlePanel.add(b22);
        middlePanel.add(b23);
        middlePanel.add(b24);
        middlePanel.add(b25);
        middlePanel.add(b26);
        middlePanel.add(b27);
        middlePanel.add(b28);
        middlePanel.add(b29);
        
        middlePanel.add(s3);
        middlePanel.add(b30);
        middlePanel.add(b31);
        middlePanel.add(b32);
        middlePanel.add(b33);
        middlePanel.add(b34);
        middlePanel.add(b35);
        middlePanel.add(b36);
        middlePanel.add(b37);
        middlePanel.add(b38);
        middlePanel.add(b39);
        
        lowerPanel.add(s4);
        lowerPanel.add(b40);
        lowerPanel.add(b41);
        lowerPanel.add(b42);
        lowerPanel.add(b43);
        lowerPanel.add(b44);
        lowerPanel.add(b45);
        lowerPanel.add(b46);
        lowerPanel.add(b47);
        lowerPanel.add(b48);
        lowerPanel.add(b49);
        
        final JTextArea commentBox = new JTextArea();
        
//        commentBox.setFont(new Font("Serif", Font.ITALIC, 16));
        commentBox.setLineWrap(true);
        commentBox.setWrapStyleWord(true);
        JLabel commentLabel = new JLabel("Comments: ");
        lowerPanel.add(commentLabel);
        lowerPanel.add(commentBox);
        
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener()
        {
          
          public void actionPerformed(ActionEvent e)
          {
            Date now = Calendar.getInstance().getTime();
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String nowStr = df.format(now);
            // System.out.println("clicked submit");
            try{
                    String myDriver = "org.gjt.mm.mysql.Driver";
                    Connection con;
                    Statement st;
                    Class.forName(myDriver);
                    String url=null;
                    String user = "root";
                    String pass = "";
                    int pain = 0;
                    int drowsiness = 0;
                    int nausea = 0;
                    int anxiety = 0;
                    int depression = 0;
                    String comments = commentBox.getText();
                    // System.out.println("about to get variables");
                    if (bg0.getSelection() != null) pain = getPain();
                    if (bg1.getSelection() != null) drowsiness = getDrowsiness();
                    if (bg2.getSelection() != null) nausea = getNausea();
                    if (bg3.getSelection() != null) anxiety = getAnxiety();
                    if (bg4.getSelection() != null) depression = getDepression();
                    // System.out.println("calling evaluate");
                    double result = Report.evaluate(pain, drowsiness, nausea, anxiety, depression);
                    // System.out.println("evaluation complete: " + result);
                    if (result > 1.645) {
                        JOptionPane.showMessageDialog(CreateReport.this, "Your symptoms are severe.  Please contact your doctor immediately, or visit an emergency room.");
                    }
                    else if (result > 0.253) {
                        JOptionPane.showMessageDialog(CreateReport.this, "Please make an appointment with your doctor.");
                        CreateAppointment ca = new CreateAppointment(profile);
                        ca.show();
                    }
                    url = "jdbc:mysql://localhost:3306/test";
                    con=DriverManager.getConnection(url, user, pass);
                    st=con.createStatement();
                    st.executeUpdate("INSERT INTO reports (patient, doctor, dateTime, pain, drowsiness, nausea, anxiety, depression, comments) VALUES ('" + patient + "', '" + doctor + "', '" + nowStr + "', '" + pain + "', '" + drowsiness + "', '" + nausea + "', '" + anxiety + "', '"+depression+"', '"+comments+"')");
                    
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}