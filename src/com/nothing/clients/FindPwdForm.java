package com.nothing.clients;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nothing.factory.DBFactory;
import com.nothing.form.base.MinCloseForm;
import com.nothing.main.LoginIN;
import com.nothing.object.Users;
import com.nothing.util.Tools;

public class FindPwdForm extends MinCloseForm {

	private JTextField IDTf;
	private JTextField mailTf;
	private JButton findPwdBtn;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindPwdForm frame = new FindPwdForm();
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
	public FindPwdForm() {
		setBounds(100, 100, 239, 145);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setBackground(c);
		getContentPane().add(panel, BorderLayout.CENTER);
		super.btnExit.setLocation(this.getWidth()-27, 0);
		super.btnMini.setLocation(this.getWidth()-50, 0);
		panel.setLayout(null);
		this.setLocationRelativeTo(null);
		
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
		findPwdBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String uID = IDTf.getText();
				String uMail = mailTf.getText();
				if(uID.trim().equals("")){
					Tools.alert(FindPwdForm.this, "�û�ID����Ϊ�գ�");
				}else if(uMail.trim().equals("")){
					Tools.alert(FindPwdForm.this, "�����ʼ�����Ϊ��");
				}else{
					String sql = "select * from Users where uID='"+uID+"' and uMail='"+uMail+"'";
					List<Users> l = DBFactory.querySql(sql, Users.class);
					if(l.size()>0){
						Users u = l.get(0);
						Tools.alert(FindPwdForm.this, "�һسɹ�������Ϊ��"+u.getuPassword());
						dispose();
						LoginIN login = new LoginIN();
						login.setLocationRelativeTo(null);
						login.setVisible(true);
					}else{
						Tools.alert(FindPwdForm.this, "������ˣ����˺���С�ӵ���");
					}
				}
			}
		});
		findPwdBtn.setBounds(69, 88, 93, 23);
		panel.add(findPwdBtn);
	}

}
