package com.nothing.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nothing.form.base.MinCloseForm;
import com.nothing.global.Constants;
import com.nothing.s.ServerMonitor;

public class ServerStart extends MinCloseForm {
	private JTextField tfPort;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerStart frame = new ServerStart();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerStart() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 282, 200);
		JPanel panel = new JPanel();
		panel.setBackground(c);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		super.btnExit.setLocation(this.getWidth()-27, 0);
		super.btnMini.setLocation(this.getWidth()-50, 0);
		
		JLabel label = new JLabel("\u670D\u52A1\u5668\u542F\u52A8\u754C\u9762");
		label.setBounds(90, 28, 93, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u7AEF\u53E3\u53F7\uFF1A");
		label_1.setBounds(38, 79, 188, 15);
		panel.add(label_1);
		
		tfPort = new JTextField();
		tfPort.setBounds(95, 77, 131, 18);
		panel.add(tfPort);
		tfPort.setColumns(10);
		tfPort.setText(Constants.port+"");
		
		JButton btnStart = new JButton("\u542F\u52A8");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfPort.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Please input server port.");
				}else{
					dispose();
					ServerMonitor sm = new ServerMonitor(Integer.parseInt(tfPort.getText()));
					sm.setVisible(true);
					sm.setLocationRelativeTo(null);
					sm.setState(JFrame.ICONIFIED);
				}
			}
		});
		btnStart.setBounds(53, 125, 71, 23);
		panel.add(btnStart);
		
		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(142, 125, 71, 23);
		panel.add(btnExit);
	}
}
