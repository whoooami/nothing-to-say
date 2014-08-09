package com.nothing.Lab;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tlove extends JFrame {

	private JPanel contentPane;
	private JTextField loveTf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tlove frame = new Tlove();
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
	public Tlove() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 306, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblxx = new JLabel("\u8BF7\u8F93\u5165\u4F60\u6697\u604B\u7684\u4EBA\u7684XX\u53F7\uFF1A");
		lblxx.setBounds(10, 25, 190, 15);
		contentPane.add(lblxx);
		
		loveTf = new JTextField();
		loveTf.setBounds(32, 50, 228, 21);
		contentPane.add(loveTf);
		loveTf.setColumns(10);
		
		JLabel lblTip = new JLabel("TIP:  \u5982\u679C\u4F60\u6697\u604B\u7684\u4EBA\u4E5F\u6B63\u5728\u6697\u604B\u4F60");
		lblTip.setBounds(32, 81, 228, 15);
		contentPane.add(lblTip);
		
		JLabel label = new JLabel("          \u7CFB\u7EDF\u4F1A\u5728\u7B2C\u4E00\u65F6\u95F4\u544A\u8BC9\u4F60");
		label.setBounds(32, 100, 228, 15);
		contentPane.add(label);
		
		JButton sureLove = new JButton("\u559C\u6B22");
		sureLove.setBounds(42, 125, 80, 22);
		contentPane.add(sureLove);
		
		JButton justLook = new JButton("\u53EA\u662F\u770B\u770B");
		justLook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		justLook.setBounds(145, 125, 102, 22);
		contentPane.add(justLook);
	}
}
