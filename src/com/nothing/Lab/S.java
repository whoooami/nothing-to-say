package com.nothing.Lab;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class S {
	
	public ServerSocket serverSocket = null;
	public boolean started = false, bConnect = false;
	public List<Client> clients = new ArrayList<Client>();
	
	public static void main(String[] args){
		new S().go();
	}
	
	public void go(){
		try {
			serverSocket = new ServerSocket(8008);
			started = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			while(started){
				Socket s = serverSocket.accept();
				System.out.println("a clients in");
				Client c = new Client(s);
				new Thread(c).start();
				clients.add(c);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	class Client implements Runnable{

		Socket s;
		String str = "";
		public ObjectInputStream ois = null;
		public ObjectOutputStream oos = null;
		public Client(Socket s){
			this.s = s;
			try {
				ois = new ObjectInputStream(s.getInputStream());
				oos = new ObjectOutputStream(s.getOutputStream());
				bConnect = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void send(Object o){
			try {
				oos.writeObject(o);
				oos.flush();
			} catch (IOException e) {
				clients.remove(this);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				while(bConnect){
					str = ois.readObject().toString();
					for(int i =0;i<clients.size();i++){
						Client c = clients.get(i);
						c.send(str);
					}
				}
			}catch(ClassNotFoundException e1){
				System.out.println("classNotFind");
				
				e1.printStackTrace();
			}catch(IOException e){
				clients.remove(this);
				System.out.println("quit");
//				e.printStackTrace();
			}finally{
				
					try {
						if(ois != null)
							ois.close();
						if(oos != null)
							oos.close();
						if(s != null)
							s.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		
	}
}
