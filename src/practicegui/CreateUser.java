package practicegui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Clinton
 */
public class CreateUser extends javax.swing.JFrame implements ActionListener {
    
    User profile = null;

    public CreateUser(User u) {
        profile = u;
        
        final Date date = new Date();
        this.setMinimumSize(new Dimension(800, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.setResizable(false);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JPanel upperPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel lowerMiddlePanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        this.getContentPane().add(upperPanel);
        this.getContentPane().add(middlePanel);
        this.getContentPane().add(lowerMiddlePanel);
        this.getContentPane().add(lowerPanel);
        String[] userTypes = {"Patient","Doctor","Nurse","Receptionist"};
        final JComboBox c0 = new JComboBox(userTypes);
        JLabel label = new JLabel("Create New User Account");
        upperPanel.add(label);
        JLabel userTypeLabel = new JLabel("User type: ");
        JLabel userNameLabel = new JLabel("Username: ");
        JLabel passWordLabel = new JLabel("Password: ");
        JLabel fullNameLabel = new JLabel("Full name: ");
        final JTextField b0 = new JTextField("",8);
        final JTextField b1 = new JTextField("",8);
        final JTextField b2 = new JTextField("",12);
        middlePanel.add(userTypeLabel);
        middlePanel.add(c0);
        middlePanel.add(fullNameLabel);
        middlePanel.add(b2);
        lowerMiddlePanel.add(userNameLabel);
        lowerMiddlePanel.add(b0);
        lowerMiddlePanel.add(passWordLabel);
        lowerMiddlePanel.add(b1);
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener()
        {
          
          public void actionPerformed(ActionEvent e)
          {
            
            // read from the various user input interfaces combobox for usertype, text input for un, pw, fn, random generated string for salt
            
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
                int newUt = c0.getSelectedIndex()-1;
                //generate newSalt
                String dateStr;
                Format formatter = new SimpleDateFormat("yyyyMMddhhmmss");
                dateStr = formatter.format(date);
                String newSalt = "d9Fd7A";
                String newUn = b0.getText();
                String newPh = b1.getText();
                String newFn = b2.getText();
                ResultSet rs = st.executeQuery("SELECT fullname FROM users WHERE userName='"+newUn+"'");
                if(rs.next()) throw new Exception("User already exists: "+rs.getString("fullname"));
                st.executeUpdate("INSERT INTO users (userName, passHash, userType, lastLogin, fullname, salt) VALUES ('"+newUn+"', '"+newPh+"', '"+newUt+"', '"+dateStr+"', '"+newFn+"', '"+newSalt+"')");
            }catch(Exception e2){
                System.out.println(e2);
                dispose();
            }

            JFrame hs = new HomeScreen(profile);
            
            hs.show();
          }
        });
        
        
        
        lowerPanel.add(submit);
        
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