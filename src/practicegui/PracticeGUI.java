package practicegui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class PracticeGUI extends JFrame implements ActionListener{
    int n = 0;
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    JLabel topLabel = new JLabel ("Please enter your name and password:");
    JLabel l1,l2;
    JButton b1,b2;
    JTextField t1;
    JPasswordField pf;
    
    PracticeGUI(){
        Toolkit tk=Toolkit.getDefaultToolkit();
    JPanel p;
    p=(JPanel)getContentPane();
        // this.getContentPane().setBackground(new Color(243,67,226));
        setVisible(true);
        setLocation(225,50);
        setSize(450,250); 
        setTitle("Login");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        l1=new JLabel("User Name: ");   
        l2=new JLabel("Password: ");
        t1=new JTextField(10);
        AbstractAction action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() instanceof JButton){
            JButton button = (JButton) e.getSource();
            button.doClick();        
            } else if(e.getSource() instanceof JComponent){
                JComponent component = (JComponent) e.getSource();
                component.transferFocus();
            }
        }
        };

        t1.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "TransferFocus");
        t1.getActionMap().put("TransferFocus", action);

        pf=new JPasswordField(10);
        pf.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "TransferFocus");
        pf.getActionMap().put("TransferFocus", action);
        ImageIcon ic=new ImageIcon("C:/key.gif");
        b1=new JButton("Login");
        b1.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "TransferFocus");
        b1.getActionMap().put("TransferFocus", action);
        ImageIcon ic1=new ImageIcon("C:/cancel.jpg");
        b2=new JButton("Exit");          
        b2.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "TransferFocus");
        b2.getActionMap().put("TransferFocus", action);
        b1.addActionListener(this);
        b2.addActionListener(this);
        JPanel p0=new JPanel();
        JPanel p1=new JPanel();   
        JPanel p2=new JPanel();      
        JPanel p3=new JPanel();  
        JPanel p4=new JPanel();   
        // p1.setBackground(new Color(243,67,226));
        // p2.setBackground(new Color(0,67,150));
        // p3.setBackground(new Color(243,0,0)); 
        // p4.setBackground(new Color(0,0,226));
        //topLabel.setBounds(105, 100, 250, 35);
        p0.add(topLabel);
        p1.add(l1);
        p1.add(t1);
        p2.add(l2);
        p2.add(pf);
        p3.add(b1);
        p3.add(b2);
        p4.setLayout(new GridLayout(6,1));
        p0.setBounds(50, 100, 250, 25);
        p4.add(p0);
        p4.add(p1);    
        p4.add(p2);
        p4.add(p3);
        add(p4);
        t1.requestFocus();
        //b1.requestFocus();
        //b1.requestFocusInWindow();
        validate();

    }  //eof cons.
    public void actionPerformed(ActionEvent ae){
         if(ae.getSource()==b1){
            try {
                try{
                    String myDriver = "org.gjt.mm.mysql.Driver";
                    Class.forName(myDriver);
                    String url=null;
                    String user = "root";
                    String pass = "";

                    url = "jdbc:mysql://localhost:3306/test";
                    con=DriverManager.getConnection(url, user, pass);
                    st=con.createStatement();
                }catch(Exception e){
                    System.out.println(e);
                    dispose();
                }
                if(t1.getText().trim().length()==0){
                    JOptionPane.showMessageDialog(this,"Enter User Name");
                    return;
                }
                if(pf.getText().trim().length()==0){
                    JOptionPane.showMessageDialog(this,"Enter Password");
                    return;
                }

                ResultSet rs=st.executeQuery("select passHash, usertype, lastLogin, salt from users where userName='"+t1.getText().trim()+"'");
                // String username = t1.getText().trim();

                if(rs.next()){
                    System.out.println(rs.getString("salt"));
                    System.out.println(User.hash(pf.getText(),rs.getString("salt")));
                    if(User.hash(pf.getText(),rs.getString("salt")).equals(rs.getString(1))){
                        // System.out.println(User.hash(rs.getString(1),rs.getString("salt")));
                        String un = t1.getText();
                        String ph = pf.getText();
                        userType ut;
                        if (rs.getInt(2)==1) {
                            ut = userType.doctor;
                        }
                        else if (rs.getInt(2)==0) {
                            ut = userType.patient;
                        }
                        else if (rs.getInt(2)==2) {
                            ut = userType.nurse;
                        }
                        else { // receptionist
                            ut = userType.receptionist;
                        }
                        //Date ll = rs.getDate("lastLogin");
                        java.util.Date utilDate = new java.util.Date();
                        java.sql.Date now = new java.sql.Date(utilDate.getTime());
                        Date ll = rs.getDate("lastLogin");
                        st.executeUpdate("UPDATE users SET lastLogin='"+now+"' WHERE userName='"+un+"'");
                        User profile = new User(un, ph, ut, ll);
                        HomeScreen hs = new HomeScreen(profile);
                        // dispose();
                        PracticeGUI.this.hide();
                        hs.show();
                    }else{
                        n++;
                        if (n==3) {
                            JOptionPane.showMessageDialog(this,"Too many login attempts.");
                            System.exit(0);
                        }
                        JOptionPane.showMessageDialog(this,"Invalid Password");
                    }

                }else{
                    JOptionPane.showMessageDialog(this,"Invalid User name");
                }

            } catch (SQLException ex) {
                Logger.getLogger(PracticeGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            }if(ae.getSource()==b2){
                dispose();
            }
    }
    public static void main(String args[]){
        new PracticeGUI();
    }
}
