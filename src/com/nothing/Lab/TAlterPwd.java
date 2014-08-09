package com.nothing.Lab;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.nothing.main.LoginIN;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class TAlterPwd extends JFrame {

	private JPanel panel;
	private JTextField IDTf;
	private JPasswordField orgPwdF;
	private JPasswordField newPwdF;
	private JPasswordField rePwdF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TAlterPwd frame = new TAlterPwd();
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
	public TAlterPwd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 285, 211);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(29, 24, 54, 15);
		panel.add(lblId);
		
		IDTf = new JTextField();
		IDTf.setBounds(107, 21, 135, 21);
		panel.add(IDTf);
		IDTf.setColumns(10);
		
		JLabel label_1 = new JLabel("\u539F\u5BC6\u7801\uFF1A");
		label_1.setBounds(29, 52, 54, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		label_2.setBounds(29, 80, 54, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u91CD\u590D\uFF1A");
		label_3.setBounds(29, 108, 54, 15);
		panel.add(label_3);
		
		JButton sureBtn = new JButton("\u786E\u5B9A");
		sureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sureBtn.setBounds(29, 147, 86, 23);
		panel.add(sureBtn);
		
		JButton cancelBtn = new JButton("\u53D6\u6D88");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginIN li = new LoginIN();
				li.setLocationRelativeTo(null);
				li.setVisible(true);
			}
		});
		cancelBtn.setBounds(149, 147, 86, 23);
		panel.add(cancelBtn);
		
		orgPwdF = new JPasswordField();
		orgPwdF.setBounds(107, 49, 135, 21);
		panel.add(orgPwdF);
		
		newPwdF = new JPasswordField();
		newPwdF.setBounds(107, 77, 135, 21);
		panel.add(newPwdF);
		
		rePwdF = new JPasswordField();
		rePwdF.setBounds(107, 105, 135, 21);
		panel.add(rePwdF);
	}

}
