package com.nothing.Lab;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TFindPwd extends JFrame {

	private JPanel panel;
	private JTextField IDTf;
	private JTextField mailTf;
	private JButton findPwdBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TFindPwd frame = new TFindPwd();
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
	public TFindPwd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 239, 145);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblidem = new JLabel("\u8BF7\u8F93\u5165\u4F60\u7684ID\u7684\u6CE8\u518C\u65F6\u7684E-mail\uFF1A");
		lblidem.setBounds(10, 10, 198, 15);
		panel.add(lblidem);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 35, 54, 15);
		panel.add(lblId);
		
		IDTf = new JTextField();
		IDTf.setBounds(76, 32, 132, 18);
		panel.add(IDTf);
		IDTf.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 63, 54, 15);
		panel.add(lblEmail);
		
		mailTf = new JTextField();
		mailTf.setColumns(10);
		mailTf.setBounds(76, 60, 132, 18);
		panel.add(mailTf);
		
		findPwdBtn = new JButton("\u627E\u56DE\u5BC6\u7801");
		findPwdBtn.setBounds(69, 88, 93, 23);
		panel.add(findPwdBtn);
	}
}
