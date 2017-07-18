package com.nothing.c.socket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import com.nothing.clients.Nothing;
import com.nothing.factory.UIFactory;
import com.nothing.global.MSGType;
import com.nothing.global.Constants;
import com.nothing.object.Groups;
import com.nothing.object.LoginInfo;
import com.nothing.object.Message;
import com.nothing.object.Users;
import com.nothing.util.Tools;

public class Client implements Runnable{

	public Socket s = null;
	public ObjectInputStream ois = null;
	public ObjectOutputStream oos = null;
	public boolean bConnect = false;
	public int loginStatus = 0;
	public String IP = "";
	public int port = 0;
	
//	public static HashMap<String, String> hmONline = new HashMap<String, String>();
	
	public Client(){}
	public Client(String[] IPport){
		this.IP = IPport[0];
		this.port = Integer.parseInt(IPport[1]);
	}

	public int checkLoginStatu(LoginInfo li){
		try {
			s = new Socket(IP,port);
			bConnect = true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Login failed!");
			Tools.alert("登陆失败");
		}
		try {
			if(bConnect) {
				oos = new ObjectOutputStream(s.getOutputStream());
				ois = new ObjectInputStream(s.getInputStream());
				oos.writeObject(li);
				oos.flush();
				int i = Integer.parseInt(ois.readObject().toString());
				Constants.Status = i;
				if(Constants.Status == Constants.SUCCESS){
					CtoSThread cs = new CtoSThread(s);
					cs.start();
					ManageLoginThread.addLoginThread(li.getUid(), cs);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Constants.Status;
		
	}
	
	public void Send(Message m){
		CtoSThread cs = ManageLoginThread.getCurrentLoginThread(m.getSender());
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
		while(bConnect){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Object o = null;
			try {
				o = ois.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(o instanceof Integer){
				Constants.Status = Integer.parseInt(o.toString());
				System.out.println("var.logn:"+ Constants.Status);
			}
		}
	}
	
	/**
	 * 
	 * @author NOTHING
	 * 		
	 * @param l 消息
	 */
	public static void setHasMsg(List<Message> l){
		Set<String> IDset = new HashSet<String>();
		for(Iterator<Message> it = l.iterator(); it.hasNext();){
			Message m = it.next();
			IDset.add(m.getRecver());
		}
		int t = 0;
		while(t++<IDset.size()){
//			InputStream in = new FileInputStream(Nothing.class.getResource("/tones/msg.wav").getPath().substring(1));// ���ļ�
//			System.out.println(Nothing.class.getResource("/tones/msg.wav").getPath().substring(1));
//			System.out.println(Constants.VOICEPATH+"msg.wav");
			InputStream in = ClassLoader.getSystemResourceAsStream("tones/msg.wav");
			try {
				AudioStream as = new AudioStream(in);
				AudioPlayer.player.start(as);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(Iterator<Message> it = l.iterator(); it.hasNext();){
			Message m = it.next();
			if(m.getMsgType() == MSGType.TEXTMSG){
//				if(UIFactory.nothingTree == null)
//					continue;
				while(true){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					System.out.println("---------sleep--------");
					if(UIFactory.nothingTree != null){
						break;
					}
				}
				List<Users> recvUsers = UIFactory.nothingTree.users;
				System.out.println("Client.接收消息用户IDs"+ Arrays.toString(recvUsers.toArray()));
				for(Iterator<Users> i = recvUsers.iterator(); i.hasNext();){
					Users u = i.next();
					String nowID = u.getuID();
	//				System.out.println(u.getuID());
					if(m.getSender().equals(nowID)){
						u.setIsHaveMsg(Constants.YES);
					}
				}
			}else if(m.getMsgType() == MSGType.GROUPMSG){
				System.out.println("Client.消息备注="+m.getComment());
				System.out.println("Client.idk ="+UIFactory.groupUserTreeMap.toString());
				List<Groups> recvGroups = UIFactory.groupTree.qunlist;
				System.out.println("Client. group size: "+recvGroups.size());
				for(Iterator<Groups> i = recvGroups.iterator();i.hasNext();){
					System.out.println("-----------1-------");
					Groups g = i.next();
					String nowID = g.getgID();
					System.out.println("-------------2------");
					System.out.println("Client.消息匹配："+m.getComment() +"  "+ nowID);
					if(m.getComment().equals(nowID)){
						g.setIsHaveMsg(Constants.YES);
					}
				}
				/*if(UIFactory.groupUserTreeMap.containsKey(m.getComment())){
					List<Groups> recvGroups = UIFactory.groupTree.qunlist;
					System.out.println("Client.��ǰ��Ⱥ����"+recvGroups.size());
					for(Iterator<Groups> i = recvGroups.iterator();i.hasNext();){
						Groups g = i.next();
						String nowID = g.getgID();
						System.out.println("Client.��ǰ��ȺID��"+m.getComment() +"  "+ nowID);
						if(m.getComment().equals(nowID)){
							g.setIsHaveMsg(Var.YES);
						}
						System.out.println(g.getIsHaveMsg());
					}
				}else{
					continue;
				}*/
			}
		}
	}

}
