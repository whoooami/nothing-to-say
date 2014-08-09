package com.nothing.Lab;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Se {

	private boolean bStart = false;
	private List<Client> clients = new ArrayList<Client>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Se().run();
	}
	
	public void run(){
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(9999);
			bStart = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(bStart){
				Socket s = ss.accept();
				Client c = new Client(s);
				new Thread(c).start();
				clients.add(c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public class Client implements Runnable{

		private Socket s;
		private ObjectOutputStream oos = null;
		private ObjectInputStream ois = null;
		private boolean bConnect = false;
		public Client(Socket s){
			this.s = s;
			try {
				oos = new ObjectOutputStream(s.getOutputStream());
				ois = new ObjectInputStream(s.getInputStream());
				bConnect = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String str = "";
			try {
				while(bConnect){
					str = ois.readObject().toString();
					System.out.println("S:"+str);
					System.out.println("S:c.l:"+clients.size());
					for(int i=0;i<clients.size();i++){
						System.out.println("en");
						Client c = clients.get(i);
						c.send(str);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void send(Object o){
			try {
				System.out.println("o:"+o);
				oos.writeObject(o);
				oos.flush();
				System.out.println("send end");
			} catch (IOException e) {
				clients.remove(this);
				e.printStackTrace();
			}
		}
		
	}

}
