package com.nothing.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.nothing.c.socket.Client;
import com.nothing.clients.AlterPwdForm;
import com.nothing.clients.FindPwdForm;
import com.nothing.clients.ManageImage;
import com.nothing.clients.Nothing;
import com.nothing.clients.RegForm;
import com.nothing.factory.DBFactory;
import com.nothing.form.base.MinCloseForm;
import com.nothing.global.Constants;
import com.nothing.object.LoginInfo;
import com.nothing.util.Tools;
import com.nothing.util.Tray;

public class LoginIN extends MinCloseForm {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserID;
	private JPasswordField passwordField;
	private JTextField txtIP;
	private JTextField txtPort;
	private String[] IPport = new String[2];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginIN frame = new LoginIN();
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
	public LoginIN() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 296, 226);
		this.setTitle("NOTHING TO SAY");
		
		JPanel panel = new JPanel();
		panel.setBackground(c);
		getContentPane().add(panel, BorderLayout.CENTER);
		super.btnExit.setLocation(this.getWidth()-27, 0);
		super.btnMini.setLocation(this.getWidth()-50, 0);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
//		setContentPane(contentPane);
		
		
		JLabel lblNothing = new JLabel("NOTHING \u7528\u6237\u767B\u9646");
		lblNothing.setFont(new Font("登陆", Font.BOLD, 14));
		lblNothing.setBounds(77, 0, 124, 16);
		panel.add(lblNothing);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setBounds(10, 97, 55, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		label_1.setBounds(10, 125, 55, 15);
		panel.add(label_1);
		
		txtUserID = new JTextField();
		txtUserID.setText("10000");
		txtUserID.setBounds(75, 94, 117, 21);
		panel.add(txtUserID);
		txtUserID.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setText("000000");
		passwordField.setBounds(75, 122, 117, 21);
		panel.add(passwordField);
		
		JButton btnLogin = new JButton("\u767B\u9646");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pwd = new String(passwordField.getPassword());
				if(txtUserID.getText().trim().equals("")){
					Tools.alert("Please input your ID...");
					return;
				}else if(pwd.trim().equals("")){
					Tools.alert("Please input your password...");
					return;
				}
				LoginInfo li = new LoginInfo();
				li.setUid(txtUserID.getText());
				li.setPwd(pwd);
				if(IPport[0] == null || IPport[0].equals("")){
					IPport[0] = "127.0.0.1";
				}
				if(IPport[1] == null || IPport[1].equals("")){
					IPport[1] = "7777";
				}
				Client c = new Client(IPport);
				Constants.Status = c.checkLoginStatu(li);
				if(Constants.Status == Constants.SUCCESS){
					dispose();
					ManageImage.loadAllHeadImage();
					final Nothing nothing = new Nothing(DBFactory.getUserByID(txtUserID.getText()));
					nothing.setVisible(true);
					nothing.setAlwaysOnTop(true);
					Tray tray = new Tray(nothing);
					tray.createTray(true);
					nothing.btnMini.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							nothing.setVisible(false);
						}
					});
				}else if(Constants.Status == Constants.FAIL){
					Tools.alert("Login failed!");
				}else if(Constants.Status == Constants.REPEAT){
					Tools.alert("ID:" + li.getUid() + "already loginIn.");
				}
			}
		});
		btnLogin.setBounds(28, 167, 64, 23);
		panel.add(btnLogin);
		
		final JPanel panSet = new JPanel();
//		panSet.setPreferredSize(new Dimension(0, 37));
//		getContentPane().add(panSet, BorderLayout.SOUTH);
		panSet.setLayout(null);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(10, 10, 34, 15);
		panSet.add(lblIp);
		
		txtIP = new JTextField();
		txtIP.setBounds(36, 8, 110, 18);
		txtIP.setText("127.0.0.1");
		panSet.add(txtIP);
		txtIP.setColumns(10);
		
		txtPort = new JTextField();
		txtPort.setColumns(10);
		txtPort.setText("7777");
		txtPort.setBounds(190, 8, 34, 18);
		panSet.add(txtPort);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSize(296, 226);
				IPport[0] = txtIP.getText();
				IPport[1] = txtPort.getText();
				getContentPane().remove(panSet);
			}
		});
		btnOk.setBounds(229, 6, 52, 21);
		panSet.add(btnOk);
		
		JLabel lblPort = new JLabel("PORT:");
		lblPort.setBounds(149, 10, 41, 15);
		panSet.add(lblPort);
//		panSet.setBackground(c);
		
		JButton btnSet = new JButton("\u8BBE\u7F6E");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSize(296, 266);
				panSet.setPreferredSize(new Dimension(0, 37));
				getContentPane().add(panSet, BorderLayout.SOUTH);
			}
		});
		btnSet.setBounds(176, 167, 64, 23);
		panel.add(btnSet);
		
		JButton btnReg = new JButton("\u6CE8\u518C");
		btnReg.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				RegForm rf = new RegForm();
				rf.setVisible(true);
			}
		});
		btnReg.setBounds(102, 167, 64, 23);
		panel.add(btnReg);
		
		JLabel label_2 = new JLabel();
		label_2.setIcon(new ImageIcon(LoginIN.class.getResource("/images/logintop.jpg")));
		label_2.setBounds(-39, 26, 384, 47);
		panel.add(label_2);
		
		JButton button = new JButton("\u5FD8\u8BB0\u5BC6\u7801");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FindPwdForm findPwd = new FindPwdForm();
				findPwd.setVisible(true);
			}
		});
//		button.setFont(new Font("����", Font.PLAIN, 9));
		button.setBounds(198, 121, 80, 22);
		panel.add(button);
		
		JButton button_1 = new JButton("\u4FEE\u6539\u5BC6\u7801");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AlterPwdForm ap = new AlterPwdForm();
				ap.setVisible(true);
			}
		});
//		button_1.setFont(new Font("����", Font.PLAIN, 9));
		button_1.setBounds(198, 93, 80, 22);
		panel.add(button_1);
		
	}
}
