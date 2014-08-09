package com.nothing.c.socket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import com.nothing.clients.Nothing;
import com.nothing.factory.UIFactory;
import com.nothing.global.MSGType;
import com.nothing.global.Var;
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
			System.out.println("连接失败，请检查网络！");
			Tools.alert("连接失败，请检查网络！");
		}
		try {
			if(bConnect) {
				oos = new ObjectOutputStream(s.getOutputStream());
				ois = new ObjectInputStream(s.getInputStream());
				oos.writeObject(li);
				oos.flush();
				int i = Integer.parseInt(ois.readObject().toString());
				Var.Status = i;
				if(Var.Status == Var.SUCCESS){
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
		return Var.Status;
		
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
				Var.Status = Integer.parseInt(o.toString());
				System.out.println("var.logn:"+Var.Status);
			}
		}
	}
	
	/**
	 * 
	 * @author NOTHING
	 * 		
	 * @param l 离线消息列表
	 */
	public static void setHasMsg(List<Message> l){
		Set<String> IDset = new HashSet<String>();
		for(Iterator<Message> it = l.iterator(); it.hasNext();){
			Message m = it.next();
			IDset.add(m.getRecver());
		}
		int t = 0;
		while(t++<IDset.size()){
			//播放消息提示音 nothing
//			Tools.playWav("/tones/msg.wav");
			/*AePlayWave p = new AePlayWave("/tones/msg.wav");
			p.start();*/
			try {
//				InputStream in = new FileInputStream(Nothing.class.getResource("/tones/msg.wav").getPath().substring(1));// 流文件
				System.out.println(Nothing.class.getResource("/tones/msg.wav").getPath().substring(1));
				System.out.println(Var.VOICEPATH+"msg.wav");
				InputStream in = new FileInputStream(Var.VOICEPATH+"msg.wav");// 流文件
				try {
					AudioStream as = new AudioStream(in);// 创建AudioStream 对象
					AudioPlayer.player.start(as);// 开始播放
					// AudioPlayer.player.stop(as);//停止播放，本例没有设置播放时间，歌曲结束自动停止
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
//		System.out.println("Client.准备进入消息循环。");
		for(Iterator<Message> it = l.iterator(); it.hasNext();){
//			System.out.println("Client.检测进入消息循环。");
			Message m = it.next();
			if(m.getMsgType() == MSGType.TEXTMSG){
//				if(UIFactory.nothingTree == null)
//					continue;
				//这里让线程停下是为了让nothingTree实例完成后再运行程序
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
//				System.out.println("Client.进入文本消息循环。接收人："+m.getRecver()+"接收人列表："+UIFactory.nothingTree);
				List<Users> recvUsers = UIFactory.nothingTree.users;
//				System.out.println("Client.消息接收者列表："+recvUsers.toString());
				for(Iterator<Users> i = recvUsers.iterator(); i.hasNext();){
//					System.out.println("Client.检测进入接收列表循环。");
					Users u = i.next();
					String nowID = u.getuID();
	//				System.out.println(u.getuID());
					if(m.getSender().equals(nowID)){
//						System.out.println(m.getSender()+"Client.设置为有消息状态");
						u.setIsHaveMsg(Var.YES);
					}
				}
			}else if(m.getMsgType() == MSGType.GROUPMSG){
				System.out.println("Client.群号码="+m.getComment());
				System.out.println("Client.群号码="+UIFactory.groupUserTreeMap.toString());
				List<Groups> recvGroups = UIFactory.groupTree.qunlist;
				System.out.println("Client.当前的群长度"+recvGroups.size());
				for(Iterator<Groups> i = recvGroups.iterator();i.hasNext();){
					System.out.println("-----------1-------");
					Groups g = i.next();
					String nowID = g.getgID();
					System.out.println("-------------2------");
					System.out.println("Client.当前的群ID："+m.getComment() +"  "+ nowID);
					if(m.getComment().equals(nowID)){
						g.setIsHaveMsg(Var.YES);
					}
				}
				/*if(UIFactory.groupUserTreeMap.containsKey(m.getComment())){
					List<Groups> recvGroups = UIFactory.groupTree.qunlist;
					System.out.println("Client.当前的群长度"+recvGroups.size());
					for(Iterator<Groups> i = recvGroups.iterator();i.hasNext();){
						Groups g = i.next();
						String nowID = g.getgID();
						System.out.println("Client.当前的群ID："+m.getComment() +"  "+ nowID);
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
