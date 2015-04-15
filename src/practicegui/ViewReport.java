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
public class ViewReport extends javax.swing.JFrame implements ActionListener {
    
    String patient = "cdjarboe";
    String doctor = "cjarbo2";
    User profile = null;

    public ViewReport(User u, final int rptid) {
        profile = u;
        //JFrame frm = new JFrame("Healthcare - Create Report");
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
        
        lowerPanel.setLayout(new BoxLayout(lowerPanel,BoxLayout.LINE_AXIS));
        
        JLabel label = new JLabel("View Report");
        JLabel madeLabel = new JLabel("Report submitted: ");
        final String pain, drowsiness, nausea, depression, anxiety;
        Date dateMade = new Date();
        String patientID = "";
        String doctorID = "";
        
        JLabel suggestionLabel = new JLabel("");
        JLabel painLabel = new JLabel("");
        JLabel drowsinessLabel = new JLabel("");
        JLabel nauseaLabel = new JLabel("");
        JLabel depressionLabel = new JLabel("");
        JLabel anxietyLabel = new JLabel("");
        JLabel commentLabel = new JLabel("");
        
        String comments;
        final String oldSug;
        
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
                ResultSet rs = st.executeQuery("SELECT patient FROM reports WHERE pkey='"+rptid+"'");
                
                rs.next();
                
                
                
                //System.out.println(rs.getString("doctor"));
                patientID = rs.getString(1);
                rs = st.executeQuery("SELECT * FROM reports WHERE pkey='"+rptid+"'");
                rs.next();
                doctorID = rs.getString("doctor");
                dateMade = rs.getDate("dateTime");
                pain = ""+rs.getInt("pain");
                drowsiness = ""+rs.getInt("drowsiness");
                nausea = ""+rs.getInt("nausea");
                depression = ""+rs.getInt("depression");
                anxiety = ""+rs.getInt("anxiety");
                comments = rs.getString("comments");
                oldSug = rs.getString("doctorSuggestion");
                String sugStr = "             Provider suggestions: "+oldSug;
                if (sugStr.equals("             Provider suggestions: ")) sugStr = sugStr+"None submitted yet.";
                suggestionLabel.setText(sugStr);
                
                painLabel.setText("Pain: "+pain);
                drowsinessLabel.setText("Drowsiness: "+drowsiness);
                nauseaLabel.setText("Nausea: "+nausea);
                depressionLabel.setText("Depression: "+depression);
                anxietyLabel.setText("Anxiety: "+anxiety);
                commentLabel.setText("Comments: "+comments);
                Format formatter = new SimpleDateFormat("dd MMMM yyyy");
                String dm = formatter.format(dateMade);
                madeLabel.setText(madeLabel.getText()+" "+dm);
                rs = st.executeQuery("SELECT fullname FROM users WHERE userName='"+patientID+"'");
                rs.next();
                patient = rs.getString("fullname");
                rs = st.executeQuery("SELECT fullname FROM users WHERE userName='"+doctorID+"'");
                rs.next();
                doctor = rs.getString("fullname");
                
                JButton suggest = new JButton("Suggest treatment");
        suggest.addActionListener(new ActionListener()
        {
          
          public void actionPerformed(ActionEvent e)
          {

            SuggestionDialogue sd = new SuggestionDialogue(profile, rptid);
            String suggestion = sd.getInput();
            
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
                ResultSet rs = st.executeQuery("SELECT fullname FROM users WHERE userName='"+profile.getUserName()+"'");
                rs.next();
                String title;
                if (profile.isDoctor()) title = "Dr.";
                else title = "Nurse";
                suggestion = suggestion+" -"+title+" "+rs.getString("fullname");
                if (oldSug!="null" && oldSug!=null && !oldSug.trim().equals("")) st.executeUpdate("UPDATE reports SET doctorSuggestion='"+oldSug+"; " +suggestion+"' WHERE pkey='"+rptid+"'");
                else st.executeUpdate("UPDATE reports SET doctorSuggestion='"+suggestion+"' WHERE pkey='"+rptid+"'");
        }catch(Exception e2){
            System.out.println(e2);
            dispose();
        }
            
            System.out.println(suggestion);
            //sd.show();
          }
        });
        if (profile.isDoctor() || profile.isNurse()) bottomPanel.add(suggest);
                
        }catch(Exception e2){
            System.out.println(e2);
            dispose();
        }
        topPanel.add(label);

        
        
        
        upperPanel.add(painLabel);
        upperPanel.add(drowsinessLabel);
        upperPanel.add(nauseaLabel);
        upperPanel.add(depressionLabel);
        upperPanel.add(anxietyLabel);
        
        middlePanel.add(madeLabel);
        
        lowerPanel.add(commentLabel);
        lowerPanel.add(suggestionLabel);
        
        JButton submit = new JButton("Return to home...");
        submit.addActionListener(new ActionListener()
        {
          
          public void actionPerformed(ActionEvent e)
          {

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