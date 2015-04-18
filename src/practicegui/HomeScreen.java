package practicegui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Clinton
 */
public class HomeScreen extends javax.swing.JFrame implements ActionListener {
    JButton creAppt;
    JButton creRpt;
    JButton viewAppt;
    JButton viewRpt;
    JButton creUser;
    User profile = null;
    int apptids[] = {0};
    int rptids[] = {0};
    /**
     * Creates new form HomeScreen
     */
    public HomeScreen(User u) {
        
        profile = u;
        final JList list;
        final JList list2;

        this.setMinimumSize(new Dimension(800, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       

        JPanel upperPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel lowerPanel2 = new JPanel();
        this.getContentPane().add(upperPanel, "North");
        this.getContentPane().add(lowerPanel, "West");
        this.getContentPane().add(lowerPanel2, "East");

        upperPanel.add(new JLabel("Welcome to Healthcare!"));

        JLabel apptsLabel = new JLabel("Appointments");
        JLabel rptsLabel = new JLabel("Reports");
        String[] appointments = {""};
        String[] reports = {""};

        try{
            String myDriver = "org.gjt.mm.mysql.Driver";
            Class.forName(myDriver);
            String url=null;
            String user = "root";
            String pass = "";

            Connection con = null;
            Statement st = null;
            ResultSet rs;
            String username = profile.getUserName();
            url = "jdbc:mysql://localhost:3306/test";
            con=DriverManager.getConnection(url, user, pass);
            st=con.createStatement();
            Statement st2 = con.createStatement();
            if (profile.isDoctor()) rs=st.executeQuery("SELECT * FROM appointments WHERE doctor='"+username+"' ORDER BY date DESC");
            else if (profile.isPatient()) rs=st.executeQuery("SELECT * FROM appointments WHERE patient='"+username+"' ORDER BY date DESC");
            else rs=st.executeQuery("SELECT * FROM appointments ORDER BY date DESC");
            int rowCount;
            if(rs.last()) {
                rowCount = rs.getRow();
                rs.beforeFirst();
                appointments = new String[rowCount];
                apptids = new int[rowCount];
                int i = 0;
                while(rs.next()) {
                    ResultSet rs2 = st2.executeQuery("SELECT fullname FROM users WHERE username='"+rs.getString("patient")+"'");
                    rs2.next();
                    appointments[i] = rs2.getString("fullname") + " " + rs.getDate("date");
                    apptids[i] = rs.getInt("pkey");
                    i++;

                }
                
            }
            else rowCount = 0;
        }catch(Exception e){
            System.out.println(e);
            dispose();
        }
        
        list = new JList(appointments); //data has type Object[]
        
        try{
            String myDriver = "org.gjt.mm.mysql.Driver";
            Class.forName(myDriver);
            String url=null;
            String user = "root";
            String pass = "";

            Connection con = null;
            Statement st = null;
            ResultSet rs;
            String username = profile.getUserName();
            url = "jdbc:mysql://localhost:3306/test";
            con=DriverManager.getConnection(url, user, pass);
            st=con.createStatement();
            Statement st2 = con.createStatement();
            if (profile.isDoctor()) rs=st.executeQuery("SELECT * FROM reports WHERE doctor='"+username+"' ORDER BY dateTime DESC");
            else if (profile.isPatient()) rs=st.executeQuery("SELECT * FROM reports WHERE patient='"+username+"' ORDER BY dateTime DESC");
            else rs=st.executeQuery("SELECT * FROM reports ORDER BY dateTime DESC");
            int rowCount;
            if(rs.last()) {
                rowCount = rs.getRow();
                rs.beforeFirst();
                reports = new String[rowCount];
                rptids = new int[rowCount];
                int i = 0;

                while(rs.next()) {
                    ResultSet rs2 = st2.executeQuery("SELECT fullname FROM users WHERE username='"+rs.getString("patient")+"'");
                    rs2.next();
                    reports[i] = rs2.getString("fullname") + " " + rs.getDate("dateTime");
                    rptids[i] = rs.getInt("pkey");
                    i++;
                }
                //list2 = new JList(reports);
            }
            else rowCount = 0;
        }catch(Exception e){
            System.out.println(e);
            dispose();
        }

        list2 = new JList(reports);
        
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(-1);

        creAppt = new JButton("New Appt");
        creRpt = new JButton("New Report");
        creUser = new JButton("New User");
        viewRpt = new JButton("View...");
        viewAppt = new JButton("View...");
        
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

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        
        creAppt.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "TransferFocus");
        creAppt.getActionMap().put("TransferFocus", action);
        creAppt.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            JFrame ca = new CreateAppointment(profile);
            
            ca.show();
          }
        });
        
        viewAppt.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "TransferFocus");
        viewAppt.getActionMap().put("TransferFocus", action);
        viewAppt.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            JFrame va = new ViewAppointment(profile, apptids[list.getSelectedIndex()]);
            
            va.show();
          }
        });

        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.PAGE_AXIS));
        lowerPanel.add(apptsLabel);
        lowerPanel.add(listScroller);
        if(!profile.isPatient()) lowerPanel.add(creAppt);
        lowerPanel.add(viewAppt);

        list2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list2.setLayoutOrientation(JList.VERTICAL);
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list2.setVisibleRowCount(-1);
        
        creUser.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            JFrame cu = new CreateUser(profile);
            
            cu.show();
          }
        });
        
        viewRpt.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            JFrame vr = new ViewReport(profile, rptids[list2.getSelectedIndex()]);
            
            vr.show();
          }
        });
        
        creRpt.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            JFrame cr = new CreateReport(profile);
            
            cr.show();
          }
        });
        
        viewRpt.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            JFrame vr = new ViewReport(profile, rptids[list2.getSelectedIndex()]);
            
            vr.show();
          }
        });

        JScrollPane listScroller2 = new JScrollPane(list2);
        listScroller.setPreferredSize(new Dimension(250, 80));

        lowerPanel2.setLayout(new BoxLayout(lowerPanel2, BoxLayout.PAGE_AXIS));
        lowerPanel2.add(rptsLabel);
        lowerPanel2.add(listScroller2);
        if (profile.isPatient()) lowerPanel2.add(creRpt);
        else 
        if(profile.isReceptionist()) lowerPanel.add(creUser);
        lowerPanel2.add(viewRpt);


        this.pack();
        this.setVisible(true);


        this.setSize(800,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        dispose();
        this.show();
    }

    HomeScreen(JFrame frm) {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new HomeScreen(profile).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getSource() == creAppt) {
            JFrame ca = new CreateAppointment(profile);
            dispose();
            ca.show();
        }
        else if (e.getSource() == creRpt) {
            
        }
        else if (e.getSource() == viewRpt) {
            
        }
        else if (e.getSource() == viewAppt) {
            
        }
    }
}