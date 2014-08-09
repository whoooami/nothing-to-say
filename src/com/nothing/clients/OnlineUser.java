package com.nothing.clients;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.nothing.form.base.MinCloseForm;

public class OnlineUser extends MinCloseForm {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OnlineUser frame = new OnlineUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OnlineUser() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

}
