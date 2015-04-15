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
        ButtonGroup bg1 = new ButtonGroup();
        ButtonGroup bg2 = new ButtonGroup();
        final ButtonGroup bg3 = new ButtonGroup();
        ButtonGroup bg4 = new ButtonGroup();
        
        JRadioButton b00 = new JRadioButton("1");
        JRadioButton b01 = new JRadioButton("2");
        JRadioButton b02 = new JRadioButton("3");
        JRadioButton b03 = new JRadioButton("4");
        JRadioButton b04 = new JRadioButton("5");
        JRadioButton b05 = new JRadioButton("6");
        JRadioButton b06 = new JRadioButton("7");
        JRadioButton b07 = new JRadioButton("8");
        JRadioButton b08 = new JRadioButton("9");
        JRadioButton b09 = new JRadioButton("10");
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
        
        
        
        
        JRadioButton b10 = new JRadioButton("1");
        JRadioButton b11 = new JRadioButton("2");
        JRadioButton b12 = new JRadioButton("3");
        JRadioButton b13 = new JRadioButton("4");
        JRadioButton b14 = new JRadioButton("5");
        JRadioButton b15 = new JRadioButton("6");
        JRadioButton b16 = new JRadioButton("7");
        JRadioButton b17 = new JRadioButton("8");
        JRadioButton b18 = new JRadioButton("9");
        JRadioButton b19 = new JRadioButton("10");
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
        
        JRadioButton b20 = new JRadioButton("1");
        JRadioButton b21 = new JRadioButton("2");
        JRadioButton b22 = new JRadioButton("3");
        JRadioButton b23 = new JRadioButton("4");
        JRadioButton b24 = new JRadioButton("5");
        JRadioButton b25 = new JRadioButton("6");
        JRadioButton b26 = new JRadioButton("7");
        JRadioButton b27 = new JRadioButton("8");
        JRadioButton b28 = new JRadioButton("9");
        JRadioButton b29 = new JRadioButton("10");
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
        
        JRadioButton b30 = new JRadioButton("1");
        JRadioButton b31 = new JRadioButton("2");
        JRadioButton b32 = new JRadioButton("3");
        JRadioButton b33 = new JRadioButton("4");
        JRadioButton b34 = new JRadioButton("5");
        JRadioButton b35 = new JRadioButton("6");
        JRadioButton b36 = new JRadioButton("7");
        JRadioButton b37 = new JRadioButton("8");
        JRadioButton b38 = new JRadioButton("9");
        JRadioButton b39 = new JRadioButton("10");
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
        
        JRadioButton b40 = new JRadioButton("1");
        JRadioButton b41 = new JRadioButton("2");
        JRadioButton b42 = new JRadioButton("3");
        JRadioButton b43 = new JRadioButton("4");
        JRadioButton b44 = new JRadioButton("5");
        JRadioButton b45 = new JRadioButton("6");
        JRadioButton b46 = new JRadioButton("7");
        JRadioButton b47 = new JRadioButton("8");
        JRadioButton b48 = new JRadioButton("9");
        JRadioButton b49 = new JRadioButton("10");
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
                    if(bg3.getSelection().getActionCommand()!=null) depression = Integer.parseInt(bg3.getSelection().getActionCommand());

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