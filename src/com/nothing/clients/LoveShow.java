package com.nothing.clients;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.nothing.form.base.MinCloseForm;

public class LoveShow extends MinCloseForm {

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoveShow frame = new LoveShow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public LoveShow(String words) {
		setBounds(100, 100, 270, 277);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(c);
		getContentPane().add(panel, BorderLayout.CENTER);
		super.btnExit.setLocation(this.getWidth()-27, 0);
		super.btnMini.setLocation(this.getWidth()-50, 0);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		this.setLocationRelativeTo(null);

		JLabel lblJ = new JLabel();
		lblJ.setIcon(new ImageIcon(Nothing.class.getResource("/images/show/love.gif")));
		lblJ.setBounds(10, 10, 250, 182);
		panel.add(lblJ);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(10, 202, 231, 37);
		label_1.setText("<html>"+words+"</html>");
		panel.add(label_1);
	}

}
