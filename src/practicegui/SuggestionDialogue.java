/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practicegui;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JOptionPane;
import javax.swing.JFrame;



/**
 *
 * @author Clinton
 */
public class SuggestionDialogue {
    JFrame frame;
  public SuggestionDialogue(User u, int rptid)
  {
    // a jframe here isn't strictly necessary, but it makes the example a little more real
    frame = new JFrame("Suggest treatment: ");

    // prompt the user to enter their name
    //String name = JOptionPane.showInputDialog(frame, "Suggest treatment for the patient's report: ");

    // get the user's input. note that if they press Cancel, 'name' will be null
    //System.out.printf("The user's name is '%s'.\n", name);

  }
  
  public String getInput() {
          // prompt the user to enter their name
    String suggestion = JOptionPane.showInputDialog(frame, "Suggest treatment for the patient's report: ");

    // get the user's input. note that if they press Cancel, 'name' will be null
    return suggestion;
  }


}