package com.nothing.clients;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.nothing.c.socket.Client;
import com.nothing.factory.DBFactory;
import com.nothing.form.base.MinCloseForm;
import com.nothing.global.MSGType;
import com.nothing.object.Message;
import com.nothing.util.Tools;

public class LoveHouse extends MinCloseForm {

	public JTextField loveTf;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoveHouse frame = new LoveHouse();
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
	public LoveHouse() {
		setBounds(100, 100, 306, 185);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		super.lbTitle.setText("LOVE IN THE HOUSE");
		
		panel = new JPanel();
		panel.setBackground(c);
		getContentPane().add(panel, BorderLayout.CENTER);
		super.btnExit.setLocation(this.getWidth()-27, 0);
		super.btnMini.setLocation(this.getWidth()-50, 0);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblxx = new JLabel("\u8BF7\u8F93\u5165\u4F60\u6697\u604B\u7684\u4EBA\u7684XX\u53F7\uFF1A");
		lblxx.setBounds(10, 25, 190, 15);
		panel.add(lblxx);
		
		loveTf = new JTextField();
		loveTf.setBounds(32, 50, 228, 21);
		panel.add(loveTf);
		loveTf.setColumns(10);
		
		JLabel lblTip = new JLabel("TIP:  \u5982\u679C\u4F60\u6697\u604B\u7684\u4EBA\u4E5F\u6B63\u5728\u6697\u604B\u4F60");
		lblTip.setBounds(32, 81, 228, 15);
		panel.add(lblTip);
		
		JLabel label = new JLabel("          \u7CFB\u7EDF\u4F1A\u5728\u7B2C\u4E00\u65F6\u95F4\u544A\u8BC9\u4F60");
		label.setBounds(32, 100, 228, 15);
		panel.add(label);
		
		JButton sureLove = new JButton("\u559C\u6B22");
		sureLove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				lover();
			}
		});
		sureLove.setBounds(42, 125, 80, 22);
		panel.add(sureLove);
		
		JButton justLook = new JButton("\u53EA\u662F\u770B\u770B");
		justLook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		justLook.setBounds(145, 125, 102, 22);
		panel.add(justLook);
	}
	
	public void lover(){
		String loveID = loveTf.getText();
		String sql = "update users set uLover='"+loveID+"' where uID='"+Nothing.uID+"'";
		int i = DBFactory.operaDB(sql);
		if(i == 1){
			dispose();
			Tools.alert("暗恋成功！");
			if(DBFactory.isInLove(loveID)){
				System.out.println("其实，"+loveID+" 已经暗恋你很久了。");
				Tools.alert("其实，"+loveID+" 已经暗恋你很久了。");
				Client c = new Client();
				Message m = new Message();
				m.setMsg("love");
				m.setMsgType(MSGType.LOVEINTHEHOUSE);
				m.setSender(Nothing.uID);
				m.setRecver(loveID);
				c.Send(m);
			}
		}else{
			Tools.alert(LoveHouse.this, "暗恋失败，请重新暗恋！");
		}
	}

}
