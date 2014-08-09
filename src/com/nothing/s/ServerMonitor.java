package com.nothing.s;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.nothing.factory.UIFactory;
import com.nothing.form.base.MinCloseForm;
import com.nothing.global.Var;
import com.nothing.s.socket.Server;

public class ServerMonitor extends MinCloseForm {

	private JPanel contentPane;
	public Server server;
	public boolean started = false;
	public static int port = Var.port;
	public JTextPane tax;

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerMonitor frame = new ServerMonitor(7777);
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
	public ServerMonitor(int port) {
		this.port = port;
		this.setTitle("服务器监控窗口");
		
		super.lbTitle.setText("服务器监视器");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 469);
		super.btnExit.setLocation(this.getWidth()-27, 0);
		super.btnMini.setLocation(this.getWidth()-50, 0);
		super.btnExit.addActionListener(new closeFormServer());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(c);
		
		JPanel panButtom = new JPanel();
		panButtom.setPreferredSize(new Dimension(0,40));
		panButtom.setBackground(c);
		getContentPane().add(panButtom, BorderLayout.SOUTH);
		panButtom.setLayout(null);
		
		final JButton controlBtn = new JButton("\u63A7\u5236\u9762\u677F");
		controlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if(started){
					controlBtn.setText("暂停");
				}else{
					controlBtn.setText("启动");
				}*/
			}
		});
		controlBtn.setBounds(178, 10, 93, 23);
		panButtom.add(controlBtn);
		
		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new closeFormServer());
		btnExit.setBounds(379, 10, 93, 23);
		panButtom.add(btnExit);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("\u670D\u52A1\u5668\u76D1\u63A7");
		label.setBounds(285, 10, 87, 21);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label, BorderLayout.NORTH);
		
		tax = new JTextPane();
		tax.setBounds(0, 34, 657, 369);
		UIFactory.monitor = tax;
		panel.add(new JScrollPane(tax), BorderLayout.CENTER);
		
		server = new Server(port);
		new Thread(server).start();
	}
	
	public ServerMonitor() {
		// TODO Auto-generated constructor stub
	}

	public class closeFormServer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(server.ss != null)
					server.ss.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(0);			
		}
		
	}
	
	/*public void Listen(){
		try {
			serverSocket = new ServerSocket(port);
			while(started){
				serverSocket.accept();
				System.out.println("a clents connection");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
