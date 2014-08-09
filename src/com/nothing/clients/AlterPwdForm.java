package com.nothing.clients;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.nothing.factory.DBFactory;
import com.nothing.form.base.MinCloseForm;
import com.nothing.main.LoginIN;
import com.nothing.object.LoginInfo;
import com.nothing.util.Tools;

public class AlterPwdForm extends MinCloseForm {

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
					AlterPwdForm frame = new AlterPwdForm();
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
	public AlterPwdForm() {
		setBounds(100, 100, 285, 211);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setBackground(c);
		getContentPane().add(panel, BorderLayout.CENTER);
		super.btnExit.setLocation(this.getWidth()-27, 0);
		super.btnMini.setLocation(this.getWidth()-50, 0);
		panel.setLayout(null);
		this.setLocationRelativeTo(null);
		
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
		
		orgPwdF = new JPasswordField();
		orgPwdF.setBounds(107, 49, 135, 21);
		panel.add(orgPwdF);
		
		newPwdF = new JPasswordField();
		newPwdF.setBounds(107, 77, 135, 21);
		panel.add(newPwdF);
		
		rePwdF = new JPasswordField();
		rePwdF.setBounds(107, 105, 135, 21);
		panel.add(rePwdF);
		
		JButton sureBtn = new JButton("\u786E\u5B9A");
		sureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uID="",orgPwd="",newPwd="",rePwd="";
				uID = IDTf.getText();
				orgPwd = new String(orgPwdF.getPassword());
				newPwd = new String(newPwdF.getPassword());
				rePwd = new String(rePwdF.getPassword());
				if(uID.trim().equals("")){
					Tools.alert(AlterPwdForm.this, "用户ID不能为空！");
				}else if(orgPwd.equals("")){
					Tools.alert(AlterPwdForm.this, "原密码不能为空！");
				}else if(newPwd.equals("")){
					Tools.alert(AlterPwdForm.this, "新密码不能为空！");
				}else if(rePwd.equals("")){
					Tools.alert(AlterPwdForm.this, "请再输入一次新密码！");
				}else if(!newPwd.equals(rePwd)){
					Tools.alert(AlterPwdForm.this, "两次输入密码不一致！");
				}else{
					LoginInfo li = new LoginInfo();
					li.setUid(uID);
					li.setPwd(orgPwd);
					if(DBFactory.login(li)){
						String sql = "update Users set uPassword='"+newPwd+"' where uID='"+uID+"'";
						int i = DBFactory.operaDB(sql);
						if(i == 1){
							Tools.alert(AlterPwdForm.this, "密码修改成功！");
							dispose();
							LoginIN lin = new LoginIN();
							lin.setLocationRelativeTo(null);
							lin.setVisible(true);
						}else{
							Tools.alert(AlterPwdForm.this, "修改失败！稍候请重试");
						}
					}else{
						Tools.alert(AlterPwdForm.this, "原密码输入错误！");
					}
				}
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
	}

}
