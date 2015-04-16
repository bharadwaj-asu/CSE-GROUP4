import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ViewAppointments {

	private JFrame frame;
	private JLabel title = new JLabel("View Appointments");
	private JLabel fim = new JLabel(" ");
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAppointments window = new ViewAppointments();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ViewAppointments() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 400);
		title.setBounds(180, 5, 120, 35);
		frame.add(title);
		frame.add(fim);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
