package com.nothing.s.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.nothing.global.MSGType;
import com.nothing.global.Constants;

public class OnlineUsers extends Thread{

	public Socket s;
	public ObjectOutputStream oos = null;
	public ObjectInputStream ois = null;
	public String uID = "";
	
	public OnlineUsers(){}
	public OnlineUsers(Socket s){
		this.s = s;
	}
	public OnlineUsers(String uID){
		this.uID = uID;
	}
	
	public void run(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ManageClientThread.notifyOthers(uID, MSGType.ONLINEUSERS, Constants.ONLINE);
		
	}
	
}
