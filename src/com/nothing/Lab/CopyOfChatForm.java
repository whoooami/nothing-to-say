package com.nothing.Lab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.nothing.evt.BorderSetEvt;
import com.nothing.form.base.BaseForm;
import com.nothing.util.Tools;
import javax.swing.JScrollPane;

public class CopyOfChatForm extends BaseForm {

	public static CopyOfChatForm frame = null;
	public JButton btnMini = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new CopyOfChatForm();
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
	public CopyOfChatForm() {
//		frame = new ChatForm();
//		frame.setContentPane(getContentPane());
		setBounds(100, 100, 430, 442);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
//		((JComponent) getContentPane()).setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panTop = new JPanel();
		getContentPane().add(panTop, BorderLayout.NORTH);
		panTop.setPreferredSize(new Dimension(0, 70));
		panTop.setBackground(c);
		panTop.setLayout(null);
		

		final JButton[] buttons = new JButton[8];
		for(int i=0;i<buttons.length;i++){
			buttons[i] = new JButton();
			buttons[i].setBorder(null);
			buttons[i].setIcon(Tools.getImageIcon("/images/chat/chat"+(i+1)+".gif", 0, 0));
			panTop.add(buttons[i]);
//			buttons[i].addMouseListener(new BorderSetEvt(buttons));
		}
		buttons[0].setBounds(7, 42, 43, 27);
		buttons[1].setBounds(50, 42, 43, 27);
		buttons[2].setBounds(93, 42, 43, 27);
		buttons[3].setBounds(136, 42, 30, 27);
		buttons[4].setBounds(166, 42, 30, 27);
		buttons[5].setBounds(196, 42, 37, 27);
		buttons[6].setBounds(233, 42, 43, 27);
		buttons[7].setBounds(276, 42, 30, 27);

		JButton button = new JButton("");
		button.setIcon(Tools.getImageIcon("/images/head/p.jpg", 30, 30));
		button.setBounds(10, 7, 30, 30);
		panTop.add(button);

		JLabel lblGameOver = new JLabel("GAME OVER");
		lblGameOver.setBounds(50, 7, 74, 15);
		panTop.add(lblGameOver);

		JLabel lblSomeWords = new JLabel("some words");
		lblSomeWords.setBounds(50, 22, 74, 15);
		panTop.add(lblSomeWords);
		
		btnMini = new JButton("");
		btnMini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		btnMini.setIcon(new ImageIcon(CopyOfChatForm.class.getResource("/images/mini.png")));
		btnMini.setBounds(377, 0, 16, 16);
		panTop.add(btnMini);
		
		JButton btnExit = new JButton("");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setIcon(new ImageIcon(CopyOfChatForm.class.getResource("/images/exit.png")));
		btnExit.setBounds(399, 0, 16, 16);
		panTop.add(btnExit);

		JPanel panRight = new JPanel();
		getContentPane().add(panRight, BorderLayout.EAST);
		panRight.setPreferredSize(new Dimension(105, 0));
		panRight.setLayout(null);

		JLabel label = new JLabel("New label");
		label.setIcon(new ImageIcon(CopyOfChatForm.class.getResource("/images/show/qshow.png")));
		label.setBounds(0, 0, 105, 183);
		panRight.add(label);

		JLabel label_1 = new JLabel("New label");
		label_1.setIcon(new ImageIcon(CopyOfChatForm.class.getResource("/images/show/qshow2.png")));
		label_1.setBounds(0, 207, 105, 154);
		panRight.add(label_1);

		JPanel panCenter = new JPanel();
		getContentPane().add(panCenter, BorderLayout.CENTER);
		panCenter.setLayout(null);

		JTextPane tpSend = new JTextPane();
		tpSend.setBounds(0, 225, 315, 110);
		panCenter.add(tpSend);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(CopyOfChatForm.class.getResource("/images/tmpbar.png")));
		button_1.setBounds(0, 201, 315, 23);
		panCenter.add(button_1);
		
		JButton btnClose = new JButton("\u5173\u95ED");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(142, 339, 78, 20);
		panCenter.add(btnClose);
		
		JButton btnSend = new JButton("\u53D1\u9001");
		btnSend.setBounds(227, 339, 78, 20);
		panCenter.add(btnSend);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 315, 201);
		panCenter.add(scrollPane);
		
				JTextPane tpRecv = new JTextPane();
				scrollPane.setViewportView(tpRecv);

	}
}
