package com.nothing.Lab;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SerchCondition extends JFrame {

	private JPanel contentPane;
	private JTextField ID_TF;
	private JTextField nickName_TF;
	private JTextField groupNum_TF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SerchCondition frame = new SerchCondition();
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
	public SerchCondition() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton sreachBtn = new JButton("\u67E5\u627E");
		sreachBtn.setBounds(212, 239, 93, 23);
		contentPane.add(sreachBtn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(107, 10, 325, 225);
		contentPane.add(tabbedPane);
		
		JPanel searchUserPanel = new JPanel();
		tabbedPane.addTab("\u67E5\u627E\u8054\u7CFB\u4EBA", null, searchUserPanel, null);
		searchUserPanel.setLayout(null);
		
		JRadioButton preciseRBtn = new JRadioButton("\u7CBE\u786E\u67E5\u627E");
		preciseRBtn.setBounds(6, 31, 88, 23);
		searchUserPanel.add(preciseRBtn);
		
		JRadioButton allRBtn = new JRadioButton("\u5168\u90E8\u67E5\u627E");
		allRBtn.setSelected(true);
		allRBtn.setBounds(6, 6, 88, 23);
		searchUserPanel.add(allRBtn);
		
		JPanel precisePanel = new JPanel();
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
		sreachCancelBtn.setBounds(326, 239, 93, 23);
		contentPane.add(sreachCancelBtn);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(10, 16, 98, 219);
		contentPane.add(label_3);
	}
}
