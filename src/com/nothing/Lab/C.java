package com.nothing.Lab;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class C extends JFrame {

	public Socket socket = null;
	public ObjectInputStream ois = null;
	public ObjectOutputStream oos = null;
	private JPanel contentPane;
	private boolean bConnect = false;
	private JTextField textField;
	private JTextArea textArea = null;

	Thread t = new Thread(new recv());
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					C frame = new C();
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
	public C() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 308, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textField = new JTextField();
		contentPane.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
		go();
		textField.addActionListener(new EnterDown());
		t.start();
	}
	
	public void go(){
		try{
			socket = new Socket("127.0.0.1", 8888);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			bConnect = true;
			System.out.println("connection");
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public class EnterDown implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				
				String str = textField.getText();
				oos.writeObject(str);
				oos.flush();
//				oos.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public class recv implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub

				try {
					while(bConnect){
						String str = ois.readObject().toString();
						System.out.println("C_recv:"+str);
						textArea.setText(textArea.getText()+str+"\n");
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

}
