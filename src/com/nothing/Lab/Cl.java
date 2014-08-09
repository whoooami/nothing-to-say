package com.nothing.Lab;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Cl extends JFrame {

	
	private JTextField txField = new JTextField();
	private JTextPane txPan = new JTextPane();
	private Socket s = null;
	private ObjectOutputStream  oos = null;
	private ObjectInputStream ois = null;
	private boolean bConnect = false;
	public Cl(){
		setBounds(0, 0, 400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(txPan,BorderLayout.CENTER);
		getContentPane().add(txField,BorderLayout.SOUTH);
		txField.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String str = txField.getText();
				try {
					oos.writeObject(str);
					oos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		run();
		new Thread(new recv()).start();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Cl().setVisible(true);
	}
	
	public void run(){
		try {
			s = new Socket("127.0.0.1",9999);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			bConnect = true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public class recv implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(bConnect);
			while(bConnect){
				try {
					String str = ois.readObject().toString();
					txPan.setText(str);
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

}
