package com.nothing.clients;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.nothing.factory.DBFactory;
import com.nothing.form.base.MinCloseForm;
import com.nothing.util.Tools;

public class RegForm extends MinCloseForm {

	public JTextField nickNameTf;
	public JTextField countryTf;
	public JTextField provinceTf;
	public JTextField cityTf;
//	public JTextField sexTf;
	public JTextField ageTf;
	public JTextField mailTf;
	public JTextField telTf;
	public JComboBox comboBox, comboBoxSex;
	public JTextPane textPane;
	private JPasswordField pwdField;
	private JPasswordField repwdField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegForm frame = new RegForm();
					frame.setVisible(true);
					frame.setAlwaysOnTop(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegForm() {
		setBounds(100, 100, 388, 343);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setBackground(c);
		getContentPane().add(panel, BorderLayout.CENTER);
		super.btnExit.setLocation(this.getWidth()-27, 0);
		super.btnMini.setLocation(this.getWidth()-50, 0);
		panel.setLayout(null);
		this.setLocationRelativeTo(null);
		super.lbTitle.setText("请填写个人资料");
		
		final JLabel lblHead = new JLabel("head");
		lblHead.setBounds(307, 10, 40, 39);
		lblHead.setIcon(Tools.getImageIcon("/images/head/go.png", 40, 40));
		panel.add(lblHead);
		
		nickNameTf = new JTextField();
		nickNameTf.setBounds(23, 28, 152, 21);
		panel.add(nickNameTf);
		nickNameTf.setColumns(10);
		
		JLabel label = new JLabel("\u6635\u79F0\uFF1A");
		label.setBounds(23, 10, 72, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u9009\u62E9\u5934\u50CF\uFF1A");
		label_1.setBounds(202, 10, 72, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u4E2A\u6027\u7B7E\u540D\uFF1A");
		label_2.setBounds(181, 59, 72, 15);
		panel.add(label_2);
		
		textPane = new JTextPane();
		textPane.setBounds(181, 76, 177, 51);
		panel.add(textPane);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(23, 137, 334, 2);
		panel.add(separator);
		
		JLabel label_4 = new JLabel("\u6027\u522B\uFF1A");
		label_4.setBounds(23, 149, 53, 15);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("\u5E74\u9F84\uFF1A");
		label_5.setBounds(96, 149, 53, 15);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("\u7535\u8BDD\uFF1A");
		label_6.setBounds(156, 149, 53, 15);
		panel.add(label_6);
		
		JLabel lblEmail = new JLabel("E-mail\uFF1A");
		lblEmail.setBounds(236, 149, 64, 15);
		panel.add(lblEmail);
		
		JLabel label_8 = new JLabel("\u56FD\u5BB6/\u5730\u533A:");
		label_8.setBounds(23, 205, 84, 15);
		panel.add(label_8);
		
		countryTf = new JTextField();
		countryTf.setBounds(23, 230, 126, 21);
		panel.add(countryTf);
		countryTf.setColumns(10);
		
		JLabel label_9 = new JLabel("\u7701\u4EFD\uFF1A");
		label_9.setBounds(156, 205, 64, 15);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("\u57CE\u5E02\uFF1A");
		label_10.setBounds(271, 205, 64, 15);
		panel.add(label_10);
		
		provinceTf = new JTextField();
		provinceTf.setBounds(156, 230, 101, 21);
		panel.add(provinceTf);
		provinceTf.setColumns(10);
		
		cityTf = new JTextField();
		cityTf.setColumns(10);
		cityTf.setBounds(271, 230, 86, 21);
		panel.add(cityTf);
		
		comboBoxSex = new JComboBox(new Object[]{"女","男"});
		comboBoxSex.setBounds(23, 174, 64, 21);
		panel.add(comboBoxSex);
//		sexTf.setColumns(10);
		
		ageTf = new JTextField();
		ageTf.setColumns(10);
		ageTf.setBounds(90, 174, 64, 21);
		panel.add(ageTf);
		
		mailTf = new JTextField();
		mailTf.setColumns(10);
		mailTf.setBounds(236, 174, 121, 21);
		panel.add(mailTf);
		
		telTf = new JTextField();
		telTf.setColumns(10);
		telTf.setBounds(155, 174, 78, 21);
		panel.add(telTf);
		
		JButton regBtn = new JButton("\u6CE8\u518C");
		regBtn.addActionListener(new RegAction());
		regBtn.setBounds(90, 282, 84, 23);
		panel.add(regBtn);
		
		JButton cancelBtn = new JButton("\u53D6\u6D88");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setBounds(205, 282, 84, 23);
		panel.add(cancelBtn);
		
		JLabel label_3 = new JLabel("\u8BF7\u8F93\u5165\u4E24\u6B21\u5BC6\u7801\uFF1A");
		label_3.setBounds(23, 59, 126, 15);
		panel.add(label_3);
		
		pwdField = new JPasswordField();
		pwdField.setBounds(23, 76, 152, 21);
		panel.add(pwdField);
		
		repwdField = new JPasswordField();
		repwdField.setBounds(23, 107, 152, 21);
		panel.add(repwdField);
		
		comboBox = new JComboBox();
		comboBox.setBounds(202, 26, 55, 25);
		panel.add(comboBox);
		final List<String> headImg = ManageImage.allHeadImage();
		for(int i=0;i<headImg.size();i++){
			comboBox.addItem(ManageImage.getImage(headImg.get(i), 20, 20));
		}
		comboBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					int index = comboBox.getSelectedIndex();
					System.out.println(headImg.get(index));
					lblHead.setIcon(Tools.getImageIcon(headImg.get(index), 40, 40));
				}
			}
		});
	}

	public class RegAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String pwd = new String(pwdField.getPassword());
			String repwd = new String(repwdField.getPassword());
			if(!pwd.equals(repwd)){
				Tools.alert(RegForm.this, "两次输入密码不一致，请重输！");
			}else{
				String tmp = comboBox.getSelectedItem()+"";
	//			System.out.println(tmp);
				String headImg = tmp.substring(tmp.lastIndexOf("/")+1);
	//			System.out.println(headImg);
				String uID = Tools.getAcountNum();
				String sql = "insert into users(uID,uPassword,uNickName,uHeadImg,uWords,uSex,uAge,uTel,uMail,uCountry,uProvice,uCity) values ('"
					+ uID +"','"+ pwd +"','"+ nickNameTf.getText() +"','"+ headImg +"','"+ textPane.getText() +"','"+ comboBoxSex.getSelectedIndex() +"','"+ ageTf.getText() +"','"+ telTf.getText() +"','" + mailTf.getText() +"','"+ countryTf.getText() +"','"+ provinceTf.getText() +"','"+ cityTf.getText()+"')";
				System.out.println(sql);
				int i = DBFactory.operaDB(sql);
				if(i == 1){
					String sql1 = "insert into relations(uID,friends) values('"+uID+"','"+uID+"')";
					DBFactory.operaDB(sql1);
					dispose();
					Tools.alert("注册成功！\n您的账号："+uID+"\n邮箱："+mailTf.getText()+"\n请妥善保管你的账户信息，以防被窃！");
				}else{
					Tools.alert("注册失败，请重试！");
				}
			}
		}

	}
}
