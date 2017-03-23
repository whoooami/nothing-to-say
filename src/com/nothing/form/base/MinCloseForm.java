package com.nothing.form.base;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nothing.clients.ChatForm;

public class MinCloseForm extends BaseForm {

	public JPanel panTop = null, panel = null;
	public JButton btnMini = null;
	public JButton btnExit = null;
	public JLabel lbTitle = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MinCloseForm frame = new MinCloseForm();
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
	public MinCloseForm() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panTop = new JPanel();
		contentPane.add(panTop, BorderLayout.NORTH);
		panTop.setPreferredSize(new Dimension(0, 16));
		panTop.setBackground(c);
		panTop.setLayout(null);
		
		lbTitle = new JLabel();
		lbTitle.setFont(new Font("����", Font.BOLD, 12));
		lbTitle.setBounds(10, 0, 200, 16);
		panTop.add(lbTitle);
		
		btnMini = new JButton("");
		btnMini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		btnMini.setIcon(new ImageIcon(ChatForm.class.getResource("/images/mini.png")));
		btnMini.setBounds(this.getWidth()-50, 0, 16, 16);
		panTop.add(btnMini);
		
		btnExit = new JButton("");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//nothing close
//				System.out.println("-------invoke base close");
				/*StackTraceElement[] elements = (new Throwable()).getStackTrace();
				for(StackTraceElement ele : elements){
					try {
						System.out.println(Class.forName(ele.getClassName()).getSuperclass()+"-"+ele.getClass());
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}*/
				dispose();
			}
		});
		btnExit.setIcon(new ImageIcon(ChatForm.class.getResource("/images/exit.png")));
		btnExit.setBounds(this.getWidth()-27, 0, 16, 16);
		panTop.add(btnExit);
	}

}
