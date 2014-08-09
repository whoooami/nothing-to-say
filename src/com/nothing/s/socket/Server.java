package com.nothing.s.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.nothing.factory.DBFactory;
import com.nothing.factory.UIFactory;
import com.nothing.global.Var;
import com.nothing.object.LoginInfo;
import com.nothing.object.Message;
import com.nothing.util.Tools;

public class Server implements Runnable{

	public ServerSocket ss = null;
	public ObjectInputStream ois = null;
	public ObjectOutputStream oos = null;
	boolean bConnect = false;
	
	public Server(){}
	public Server(int port){
		try {
			ss = new ServerSocket(port);
			bConnect = true;
		} catch (IOException e) {
			System.out.println("端口已被占用，请检查是否已开启！");
			System.exit(0);
		}
	}
	
	public void Send(Message m, String toWho){
		StoCThread cs = ManageClientThread.getCurrentClientThread(toWho);
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(cs.s.getOutputStream());
			oos.writeObject(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while(bConnect){
				Socket s = ss.accept();
				oos = new ObjectOutputStream(s.getOutputStream());
				ois = new ObjectInputStream(s.getInputStream());
				LoginInfo li = (LoginInfo)ois.readObject();
//				System.out.println(li.getUid()+"-"+li.getPwd()+"试图登陆");
				if(DBFactory.login(li)){
					if(ManageClientThread.reLogin(li.getUid())){
						oos.writeObject(Var.REPEAT);
						s.close();
					}else{
						oos.writeObject(Var.SUCCESS);
						StoCThread sct = new StoCThread(s);
						ManageClientThread.addClientThread(li.getUid(), sct);
						Tools.addMessage(UIFactory.monitor, li.getUid()+"登陆成功"+Tools.getnow());
						List<Message> l = ManageClientThread.isInList(li.getUid());
//						System.out.println("ManageClientThread.size="+l.size());
						for(Iterator<Message> it = l.iterator();it.hasNext();){
//							System.out.println("-------------en");
							Message m = (Message)it.next();
							System.out.println("服务端收到信息："+m.getMsgType()+"---"+m.getSender()+"-"+m.getRecver()+":"+m.getMsg());
							Send(m, m.getRecver());
							System.out.println("1-ManageClientThread.msgList.size="+ManageClientThread.msgList.size());
							ManageClientThread.msgList.remove(m);
							System.out.println("2-ManageClientThread.msgList.size="+ManageClientThread.msgList.size());
						}
						sct.start();
						new OnlineUsers(li.getUid()).start();
					}
				}
				else{
					oos.writeObject(Var.FAIL);
					s.close();
				}
			}
		} catch (IOException e) {
			System.out.println("服务端退出！");
			return;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
