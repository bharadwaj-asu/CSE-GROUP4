/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcareapp;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import healthcareapp.*;
import static healthcareapp.userType.*;
import java.util.GregorianCalendar;

/**
 *
 * @author bharadwaj
 */
public class UserIT {
    
    public UserIT() {
    }
    Date date = new Date(10102015);
    User instance = new User("dummy","dummy",doctor,date);
    User instance1= new User("dummy","dummy",patient,date);  
        
    
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
     * Test of isPatient method, of class User.
     */
    @Test
    public void testIsPatient() {
        System.out.println("isPatient");
       boolean usertype = instance1.isPatient();
        assertEquals(usertype,true);
    }
    /**
     * Test of isDoctor method, of class User.
     */
    @Test
    public void testIsDoctor() {
      System.out.println("isDoctor");
        
       boolean usertype = instance.isDoctor();
        assertEquals(usertype,true); 
    }

    /**
     * Test of getType method, of class User.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        
       userType user=instance.getType();
        assertEquals(doctor,user);
        
    }

    /**
     * Test of getPassHash method, of class User.
     */
    @Test
    public void testGetPassHash() {
        System.out.println("getPassHash");
       
        String result = instance.getPassHash();
        assertEquals("dummy", result);
        
    }

    /**
     * Test of getUserName method, of class User.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
      
        String result = instance.getUserName();
        assertEquals("dummy", result);
       
    }

    /**
     * Test of getLastLogin method, of class User.
     */
    @Test
    public void testGetLastLogin() {
        System.out.println("getLastLogin");
        Date result = instance.getLastLogin();
        assertEquals(date, result);
       
    }
    
}
