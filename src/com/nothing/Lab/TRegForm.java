package com.nothing.Lab;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.nothing.clients.ManageImage;
import com.nothing.util.Tools;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class TRegForm extends JFrame {

	private JPanel panel;
	private JTextField nickNameTf;
	private JTextField countryTf;
	private JTextField provinceTf;
	private JTextField cityTf;
	private JTextField sexTf;
	private JTextField ageTf;
	private JTextField mailTf;
	private JTextField telTf;
	private JPasswordField pwdField;
	private JPasswordField repwdField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TRegForm frame = new TRegForm();
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
	public TRegForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 343);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		final JLabel lblHead = new JLabel("head");
		lblHead.setBounds(307, 10, 40, 39);
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
		
		JTextPane textPane = new JTextPane();
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
		
		sexTf = new JTextField();
		sexTf.setBounds(23, 174, 64, 21);
		panel.add(sexTf);
		sexTf.setColumns(10);
		
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
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(202, 28, 72, 21);
		panel.add(comboBox);
		
		JLabel label_3 = new JLabel("\u8BF7\u8F93\u5165\u4E24\u6B21\u5BC6\u7801\uFF1A");
		label_3.setBounds(23, 59, 126, 15);
		panel.add(label_3);
		
		pwdField = new JPasswordField();
		pwdField.setBounds(23, 76, 152, 21);
		panel.add(pwdField);
		
		repwdField = new JPasswordField();
		repwdField.setBounds(23, 107, 152, 21);
		panel.add(repwdField);
		final List<String> headImg = ManageImage.allHeadImage();
		for(int i=0;i<headImg.size();i++){
			comboBox.addItem(ManageImage.getImage(headImg.get(i), 20, 20));
		}
		comboBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				int index = comboBox.getSelectedIndex();
				lblHead.setIcon(Tools.getImageIcon(headImg.get(index), 40, 40));
			}
		});
	}
}
