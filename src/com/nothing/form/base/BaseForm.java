package com.nothing.form.base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BaseForm extends JFrame {

	public static BaseForm frame = null;
	public JPanel contentPane;
	public static Color c = new Color(128, 128, 255);
	/** 计算移动时的座标 */
	public int xx = 0,yy = 0;
	/** 移动的标志 */
	public boolean isDrag = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new BaseForm();
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
	public BaseForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(c);
		this.setUndecorated(true);
		setContentPane(contentPane);
		
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				isDrag = true;
				xx = e.getX();
				yy = e.getY();
			}
			public void mouseReleased(MouseEvent e){
				isDrag = false;
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				if(isDrag){
					int left = getLocation().x;
					int top = getLocation().y;
					setLocation(left + e.getX()-xx,top + e.getY()-yy);
				}
			}
		});
	}

}
