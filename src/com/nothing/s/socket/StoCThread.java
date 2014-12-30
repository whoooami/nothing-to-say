package com.nothing.s.socket;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.nothing.factory.UIFactory;
import com.nothing.global.MSGType;
import com.nothing.global.Var;
import com.nothing.object.Message;
import com.nothing.util.Tools;

public class StoCThread extends Thread {
	
	public Socket s;
	public ObjectOutputStream oos = null;
	public ObjectInputStream ois = null;
	public boolean bConnect = false;
	
	public StoCThread(Socket s){
		this.s = s;
		bConnect = true;
		/*try {
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
			bConnect = true;
		} catch (IOException e) {
			System.out.println("clients exit!");
//			e.printStackTrace();
		}*/
	}
	
	public void send(Message m){
		StoCThread sc = ManageClientThread.getCurrentClientThread(m.getRecver());
		try {
			oos = new ObjectOutputStream(sc.s.getOutputStream());
			oos.writeObject(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		try {
			while(bConnect){
				ois = new ObjectInputStream(s.getInputStream());
				Object o = ois.readObject();
				if(o instanceof Message){
					Message m = (Message)o;
					if(m.getMsgType() == MSGType.TEXTMSG){
						//这里用来判断接收者是否在线
						if(ManageClientThread.reLogin(m.getRecver())){
							Tools.addMessage(UIFactory.monitor, "收到信息包:"+m.getSender()+"对"+m.getRecver()+"说:"+m.getMsg()+Tools.getnow());
							this.send(m);
						}else{
							ManageClientThread.msgList.add(ManageClientThread.msgList.size(), m);
							Tools.addMessage(UIFactory.monitor, m.getRecver()+"不在线，暂存入离线消息包"+Tools.getnow());
//							System.out.println("离线消息:StoCThread:msg.size="+ManageClientThread.msgList.size());
						}
					}else if(m.getMsgType() == MSGType.SYSEXIT){
						ManageClientThread.removeCurrentClientThread(m.getSender());
						//返回当前的在线列表，更新。
						ManageClientThread.notifyOthers(m.getSender(), MSGType.ONLINEUSERS, Var.OFFLINE);
						Tools.addMessage(UIFactory.monitor, m.getSender()+"退出系统！"+Tools.getnow());
						System.out.println(m.getSender()+"退出系统！");
					}else if(m.getMsgType() == MSGType.GROUPMSG){
//						String recvers = m.getRecver();
						//获取群中在线人数的list
						List<String> onlineRecver = Tools.getAllSameElement(ManageClientThread.getOnlineUserIDs(), m.getRecver());
//						System.out.println("StoCThread.群消息接收人列表:"+onlineRecver.toString());
//						System.out.println("StoCThread."+onlineRecver.contains(Nothing.uID)+"--"+Nothing.uID);
//						if(onlineRecver.contains(Nothing.uID)){
//							onlineRecver.remove(Nothing.uID);
//						}
//						System.out.println("StoCThread.群消息接收人列表（自己不接收）:"+onlineRecver.toString());
						for(String recver:onlineRecver){
							m.setRecver(recver);
							this.send(m);
						}
						
					}else if(m.getMsgType() == MSGType.SENDFILEREQUEST || m.getMsgType() == MSGType.SENDFILERESPONSE){
						if(ManageClientThread.hm.containsKey(m.getRecver())){
							if(m.getMsgType() == MSGType.SENDFILEREQUEST){
								String ip = ManageClientThread.getCurrentClientThread(m.getRecver()).s.getInetAddress().toString().substring(1);
								m.setComment(ip);
							}
							this.send(m);
						}else{
							//发、接倒，重发至发送者
							String sender = m.getSender();
							m.setSender(m.getRecver());
							m.setRecver(sender);
							m.setMsg("0");
							this.send(m);
						}
					}else if(m.getMsgType() == MSGType.VOICECHATREQUEST || m.getMsgType() == MSGType.VOICECHATRESPONSE){
						if(ManageClientThread.hm.containsKey(m.getRecver())){
							if(m.getMsgType() == MSGType.VOICECHATREQUEST){
								String ip = ManageClientThread.getCurrentClientThread(m.getRecver()).s.getInetAddress().toString().substring(1);
								m.setComment(ip);
							}
							this.send(m);
						}else{
							//发、接倒，重发至发送者
							String sender = m.getSender();
							m.setSender(m.getRecver());
							m.setRecver(sender);
							m.setMsg("0");
							this.send(m);
						}
					}else if(m.getMsgType() == MSGType.VOICECHATINTERRUPT){
						//语音、视频中断通知
						System.out.println("中断请求");
						String sender = m.getSender();
						m.setSender(m.getRecver());
						m.setRecver(sender);
						this.send(m);
					}else if(m.getMsgType() == MSGType.LOVEINTHEHOUSE){
						this.send(m);
					}
				}else if(o instanceof String){
					System.out.println("服务端收到字串消息："+o.toString());
				}else{
					Tools.alert("未知信息格式！");
				}
			}
		} catch (IOException e) {
			System.out.println("Client is exit!");
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}
