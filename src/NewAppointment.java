import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class NewAppointment {

	private JFrame frame;
	private JLabel patientID = new JLabel("Patient ID: ");
	private JLabel patientName = new JLabel("Patient Name: ");
	private JLabel doctorName = new JLabel("Doctor Name: ");
	private JLabel date = new JLabel("Date: ");
	private JLabel title = new JLabel("New Appointment");
	private JTextField boxPatientID = new JTextField(10);
	private JTextField boxPatientName = new JTextField(10);
	private JComboBox comboDoctor = new JComboBox();
	private JTextField boxDate = new JTextField(10);
	private JTextField boxTime = new JTextField(10);
	private JLabel time = new JLabel("Time: ");
	private JButton submit = new JButton("Submit");
	private JLabel fim = new JLabel(" ");
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewAppointment window = new NewAppointment();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public NewAppointment() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 400);
		title.setBounds(180, 5, 120, 35);
		patientID.setBounds(15, 50, 60, 15);
	    boxPatientID.setBounds(100, 50, 90, 15);
	    patientName.setBounds(15, 80, 120, 15);
	    boxPatientName.setBounds(100, 80, 90, 15);
	    doctorName.setBounds(15, 110, 120, 15);
	    comboDoctor.setBounds(100, 110, 90, 15);
	    time.setBounds(15, 140, 50, 15);
	    boxTime.setBounds(100, 140, 50, 15);
	    date.setBounds(220, 140, 50, 15);
	    boxDate.setBounds(260, 140, 50, 15);
	    submit.setBounds(180, 170, 80, 15);
		frame.add(title);
		frame.add(patientID);
		frame.add(boxPatientID);
		frame.add(patientName);
		frame.add(boxPatientName);
		frame.add(doctorName);
		frame.add(comboDoctor);
		frame.add(time);
		frame.add(boxTime);
		frame.add(date);
		frame.add(boxDate);
		frame.add(submit);
		frame.add(fim);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
