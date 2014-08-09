package com.nothing.clients;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.nothing.factory.DBFactory;
import com.nothing.form.base.MinCloseForm;
import com.nothing.object.Groups;
import com.nothing.object.Users;
import com.nothing.util.Tools;

public class SearchConditionForm extends MinCloseForm {

	private JPanel panel, precisePanel;
	private JTextField ID_TF;
	private JTextField nickName_TF;
	private JTextField groupNum_TF;
	private JTabbedPane tabbedPane;
	private JRadioButton allRBtn;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchConditionForm frame = new SearchConditionForm();
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
	public SearchConditionForm() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.lbTitle.setText("查找联系人");

		panel = new JPanel();
		panel.setBackground(c);
		getContentPane().add(panel, BorderLayout.CENTER);
		super.btnExit.setLocation(this.getWidth()-27, 0);
		super.btnMini.setLocation(this.getWidth()-50, 0);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JButton sreachBtn = new JButton("\u67E5\u627E");
		sreachBtn.setName("sreachBtn");
		sreachBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				findUserOrGroup();
			}
		});
		sreachBtn.setBounds(212, 239, 93, 23);
		panel.add(sreachBtn);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(107, 10, 325, 225);
		panel.add(tabbedPane);
		
		JPanel searchUserPanel = new JPanel();
		tabbedPane.addTab("\u67E5\u627E\u8054\u7CFB\u4EBA", null, searchUserPanel, null);
		searchUserPanel.setLayout(null);
		
		JRadioButton preciseRBtn = new JRadioButton("\u7CBE\u786E\u67E5\u627E");
		preciseRBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				precisePanel.setVisible(true);
			}
		});
		preciseRBtn.setBounds(6, 6, 88, 23);
		preciseRBtn.setSelected(true);
		searchUserPanel.add(preciseRBtn);
		
		allRBtn = new JRadioButton("\u5168\u90E8\u67E5\u627E");
		allRBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				precisePanel.setVisible(false);
			}
		});
		allRBtn.setBounds(6, 31, 88, 23);
		searchUserPanel.add(allRBtn);
		
		ButtonGroup group = new ButtonGroup();
		group.add(preciseRBtn);
		group.add(allRBtn);
		
		precisePanel = new JPanel();
		precisePanel.setBounds(6, 60, 304, 126);
		searchUserPanel.add(precisePanel);
		precisePanel.setLayout(null);
		
		JLabel label = new JLabel("\u8D26\u53F7:");
		label.setBounds(10, 10, 54, 15);
		precisePanel.add(label);
		
		ID_TF = new JTextField();
		ID_TF.setBounds(74, 7, 128, 18);
		precisePanel.add(ID_TF);
		ID_TF.setColumns(10);
		
		nickName_TF = new JTextField();
		nickName_TF.setColumns(10);
		nickName_TF.setBounds(74, 38, 128, 18);
		precisePanel.add(nickName_TF);
		
		JLabel label_1 = new JLabel("\u6635\u79F0:");
		label_1.setBounds(10, 41, 54, 15);
		precisePanel.add(label_1);
		
		JPanel searchGroupPanel = new JPanel();
		tabbedPane.addTab("\u67E5\u627E\u7FA4", null, searchGroupPanel, null);
		searchGroupPanel.setLayout(null);
		
		JLabel label_2 = new JLabel("\u8BF7\u8F93\u5165\u7FA4\u53F7\u7801\uFF1A");
		label_2.setBounds(10, 30, 100, 15);
		searchGroupPanel.add(label_2);
		
		groupNum_TF = new JTextField();
		groupNum_TF.setBounds(47, 55, 147, 15);
		searchGroupPanel.add(groupNum_TF);
		groupNum_TF.setColumns(10);
		
		JButton sreachCancelBtn = new JButton("\u53D6\u6D88");
		sreachCancelBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		sreachCancelBtn.setBounds(326, 239, 93, 23);
		panel.add(sreachCancelBtn);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(10, 16, 98, 219);
		panel.add(label_3);
	}
	
	public void findUserOrGroup(){
		if(tabbedPane.getSelectedIndex() == 0 ){
			if(allRBtn.isSelected() == true){
				List<Users> users = DBFactory.querySql("select * from users", Users.class);
				if(users.size()>0){
					this.dispose();
					SearchResultForm sr = new SearchResultForm(users, 0);
					sr.fillInTable();
					sr.setVisible(true);
				}
			}else{
				String sql = "";
				String uid = ID_TF.getText().trim();
				String unickname = nickName_TF.getText().trim();
				if(uid.equals("") && unickname.equals("")){
					Tools.alert(SearchConditionForm.this, "请至少输入一项条件进行查询！");
				}else{
					if(!uid.equals("") && !unickname.equals("")){
						sql = "select * from users where uID='"+uid+"' and uNickName='"+unickname+"'";
					}else if(!uid.equals("") && unickname.equals("")){
						sql = "select * from users where uID='"+uid+"'";
					}else if(uid.equals("") && !unickname.equals("")){
						sql = "select * from users where uNickName like '%"+unickname+"%'";
					}
					List<Users> users = DBFactory.querySql(sql, Users.class);
					if(users.size()>0){
						this.dispose();
						SearchResultForm sr = new SearchResultForm(users, 0);
						sr.fillInTable();
						sr.setVisible(true);
					}else{
						Tools.alert(SearchConditionForm.this, "没有符合条件的ID!");
					}
				}
			}
		}else{
			String sql = "";
			if(groupNum_TF.getText().equals("")){
				Tools.alert(SearchConditionForm.this, "群号码不能不空！");
			}else{
				sql = "select * from groups where gID='"+groupNum_TF.getText()+"'";
				List<Groups> group = DBFactory.querySql(sql, Groups.class);
				if(group.size() > 0){
					this.dispose();
					SearchResultForm sr = new SearchResultForm(group, 1);
					sr.fillInTable();
					sr.setVisible(true);
				}else{
					Tools.alert(SearchConditionForm.this, "没有找到群！");
				}
			}
		}
	}

}
