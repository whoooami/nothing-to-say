package com.nothing.clients;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.nothing.form.base.MinCloseForm;
import com.nothing.global.Var;
import com.nothing.object.Users;
import com.nothing.util.Tools;

public class UserInfoForm extends MinCloseForm {

	private JLabel lblHead, uIDTf;
	private JTextField nickNameTf;
	private JTextField countryTf;
	private JTextField provinceTf;
	private JTextField cityTf;
	private JTextField sexTf;
	private JTextField ageTf;
	private JTextField mailTf;
	private JTextField telTf;
	private JTextPane textPane;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfoForm frame = new UserInfoForm();
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
	public UserInfoForm() {
		setBounds(100, 100, 436, 318);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		super.lbTitle.setText("用户资料");
		
		panel = new JPanel();
		panel.setBackground(c);
		getContentPane().add(panel, BorderLayout.CENTER);
		super.btnExit.setLocation(this.getWidth()-27, 0);
		super.btnMini.setLocation(this.getWidth()-50, 0);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		this.setLocationRelativeTo(null);
		
		lblHead = new JLabel("head");
		lblHead.setBounds(22, 10, 40, 39);
		panel.add(lblHead);
		
		nickNameTf = new JTextField();
		nickNameTf.setBounds(72, 28, 152, 21);
		panel.add(nickNameTf);
		nickNameTf.setColumns(10);
		
		JLabel label = new JLabel("\u6635\u79F0\uFF1A");
		label.setBounds(72, 10, 72, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u4E3B\u663E\u8D26\u53F7\uFF1A");
		label_1.setBounds(254, 10, 72, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u4E2A\u6027\u7B7E\u540D\uFF1A");
		label_2.setBounds(72, 59, 72, 15);
		panel.add(label_2);
		
		textPane = new JTextPane();
		textPane.setBounds(72, 76, 334, 51);
		panel.add(textPane);
		
		uIDTf = new JLabel("New label");
		uIDTf.setBounds(254, 34, 152, 15);
		panel.add(uIDTf);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(72, 137, 334, 2);
		panel.add(separator);
		
		JLabel label_4 = new JLabel("\u6027\u522B\uFF1A");
		label_4.setBounds(72, 149, 53, 15);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("\u5E74\u9F84\uFF1A");
		label_5.setBounds(145, 149, 53, 15);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("\u7535\u8BDD\uFF1A");
		label_6.setBounds(205, 149, 53, 15);
		panel.add(label_6);
		
		JLabel lblEmail = new JLabel("E-mail\uFF1A");
		lblEmail.setBounds(285, 149, 64, 15);
		panel.add(lblEmail);
		
		JLabel label_8 = new JLabel("\u56FD\u5BB6/\u5730\u533A:");
		label_8.setBounds(72, 205, 84, 15);
		panel.add(label_8);
		
		countryTf = new JTextField();
		countryTf.setBounds(72, 230, 126, 21);
		panel.add(countryTf);
		countryTf.setColumns(10);
		
		JLabel label_9 = new JLabel("\u7701\u4EFD\uFF1A");
		label_9.setBounds(205, 205, 64, 15);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("\u57CE\u5E02\uFF1A");
		label_10.setBounds(320, 205, 64, 15);
		panel.add(label_10);
		
		provinceTf = new JTextField();
		provinceTf.setBounds(205, 230, 101, 21);
		panel.add(provinceTf);
		provinceTf.setColumns(10);
		
		cityTf = new JTextField();
		cityTf.setColumns(10);
		cityTf.setBounds(320, 230, 86, 21);
		panel.add(cityTf);
		
		sexTf = new JTextField();
		sexTf.setBounds(72, 174, 64, 21);
		panel.add(sexTf);
		sexTf.setColumns(10);
		
		ageTf = new JTextField();
		ageTf.setColumns(10);
		ageTf.setBounds(139, 174, 64, 21);
		panel.add(ageTf);
		
		mailTf = new JTextField();
		mailTf.setColumns(10);
		mailTf.setBounds(285, 174, 121, 21);
		panel.add(mailTf);
		
		telTf = new JTextField();
		telTf.setColumns(10);
		telTf.setBounds(204, 174, 78, 21);
		panel.add(telTf);
		
		JButton sureBtn = new JButton("\u786E\u5B9A");
		sureBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		sureBtn.setBounds(215, 257, 93, 23);
		panel.add(sureBtn);
		
		JButton cancelBtn = new JButton("\u53D6\u6D88");
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setBounds(313, 257, 93, 23);
		panel.add(cancelBtn);
	}
	
	public void fillInField(Users user){
		lblHead.setIcon(Tools.getImageIcon(Var.HEADIMGPATH+user.getuHeadImg(), 40, 40));
		nickNameTf.setText(user.getuNickName());
		textPane.setText(user.getuWords());
		uIDTf.setText(user.getuID());
		countryTf.setText(user.getuCountry());
		provinceTf.setText(user.getuProvice());
		cityTf.setText(user.getuCity());  
		sexTf.setText(user.getuSex()==0?"女":"男");   
		ageTf.setText(user.getuAge()+"");
		mailTf.setText(user.getuMail());
		telTf.setText(user.getuTel());
	}

}
