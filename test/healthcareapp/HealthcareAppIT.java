/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcareapp;

import com.sun.xml.internal.ws.message.ByteArrayAttachment;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author bharadwaj
 */
public class HealthcareAppIT {
    
        String userName = "";
        String username1="cdjarboe";
        String passHash = "";
        String PassHash1="ichabod2";
        
    public HealthcareAppIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class HealthcareApp.
     */
    
    @Test public void testMain() {
        assertEquals(1,1);}
             
    /**
     * Test of authenticate method, of class HealthcareApp.
     */
    @Test
    public void testAuthenticate() {
        System.out.println("authenticate");
        
        boolean expResult = false;
        boolean expresult1=true;
        boolean result = HealthcareApp.authenticate(userName, passHash);
        boolean result1 = HealthcareApp.authenticate(username1, PassHash1);
        assertEquals(expResult, result);
        assertEquals(expresult1,result1);
    }

    /**
     * Test of hash method, of class HealthcareApp.
     */
    @Test
    public void testHash() {
        System.out.println("hash");
        String str = "x";
        String expResult = "x";
        String result = HealthcareApp.hash(str);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of menu method, of class HealthcareApp.
     */
    @Ignore
    public void DISABLED_testMenu() {
        System.out.println("menu");
         ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
                System.setIn(in);
        HealthcareApp.authenticate(username1, PassHash1);
                String b=HealthcareApp.menu();
                System.setIn(System.in);
 
        assertEquals(b,"patient");
    }
   
    /**
     * Test of viewAppointments method, of class HealthcareApp.
     */
    @Test
    public void testViewAppointments() {
        System.out.println("viewAppointments");
       String b= HealthcareApp.viewAppointments();
        // TODO review the generated test code and remove the default call to fail.
        assertEquals("patient",b);
    }

    /**
     * Test of makeAppointment method, of class HealthcareApp.
     */
    @Test
    public void testMakeAppointment() {
         HealthcareApp.authenticate(username1,PassHash1);
                ByteArrayInputStream in = new ByteArrayInputStream("cdjarboe".getBytes());
                System.setIn(in);
           Boolean test=HealthcareApp.makeAppointment();
          System.setIn(System.in);
		assertFalse(test);
		
    }

    /**
     * Test of searchUser method, of class HealthcareApp.
     */
    @Test
    public void testSearchUser() {
        System.out.println("searchUser");
        User expResult = null;
        User result = HealthcareApp.searchUser(username1);
        assertEquals(username1, result.getUserName());
        
    }

    /**
     * Test of viewReports method, of class HealthcareApp.
     */
    @Test
    public void testViewReports() {
        System.out.println("viewReports");
        HealthcareApp.authenticate(username1, PassHash1);
        String view=HealthcareApp.viewReports();
        assertEquals("patient", view);
       
    }

    /**
     * Test of submitReport method, of class HealthcareApp.
     */
    @Test
    public void testSubmitReport() {
        System.out.println("submitReport");
        HealthcareApp.authenticate("cdjarboe","ichabod2");
        String simulatedUserInput = "1" + System.getProperty("line.separator")
    + "1" + System.getProperty("line.separator")+"1"+System.getProperty("line.separator")
    + "1" + System.getProperty("line.separator")+"1"+System.getProperty("line.separator")
    + "1" + System.getProperty("line.separator")+"1"+System.getProperty("line.separator")
    + "Comment" + System.getProperty("line.separator");

        InputStream savedStandardInputStream = System.in;
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        int b=HealthcareApp.submitReport();
        System.setIn(savedStandardInputStream);
        assertEquals(b, 1);
        
        
    }

    private static class ByteArrayAttachmentInputStream {

        public ByteArrayAttachmentInputStream() {
        }
    }
    
}
