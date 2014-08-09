package com.nothing.Lab;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;

public class sreachResult extends JFrame {

	private JPanel contentPane;
	private JTable TB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sreachResult frame = new sreachResult();
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
	public sreachResult() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton backBtn = new JButton("\u8FD4\u56DE");
		backBtn.setBounds(23, 209, 62, 23);
		contentPane.add(backBtn);
		
		JButton infoBtn = new JButton("\u8BE6\u7EC6\u8D44\u6599");
		infoBtn.setBounds(95, 209, 88, 23);
		contentPane.add(infoBtn);
		
		JButton addBtn = new JButton("\u52A0\u4E3A\u597D\u53CB");
		addBtn.setBounds(193, 209, 88, 23);
		contentPane.add(addBtn);
		
		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.setBounds(291, 209, 62, 23);
		contentPane.add(btnCancel);
		
		JPanel TBpanel = new JPanel();
		TBpanel.setBounds(33, 25, 335, 174);
		contentPane.add(TBpanel);
		TBpanel.setLayout(new BorderLayout(0, 0));
		
		TB = new JTable();
		TBpanel.add(TB, BorderLayout.CENTER);
	}
}
