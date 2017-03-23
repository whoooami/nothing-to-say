package com.nothing.s.socket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import com.nothing.factory.DBFactory;
import com.nothing.global.MSGType;
import com.nothing.object.Message;
import com.nothing.util.Tools;

public class ManageClientThread extends Thread {
	
	public static ConcurrentHashMap<String, StoCThread> hm = new ConcurrentHashMap<String ,StoCThread>();
	public static List<Message> msgList = new Vector<Message>();
	public static Server server = new Server();
	
	public static void addClientThread(String uId,StoCThread sc){
		hm.put(uId, sc);
	}
	
	public static StoCThread getCurrentClientThread(String uID){
		return hm.get(uID);
	}
	
	public static void removeCurrentClientThread(String uID){
		hm.remove(uID);
	}
	
	public static String getOnlineUserIDs(){
		String IDs = "";
		Iterator<String> it = hm.keySet().iterator();
		while(it.hasNext()){
			IDs += it.next().toString() + ",";
		}
		return IDs;
	}
	
	public static void notifyOthers(String uID, int msgType, int state){
		List<String> list = Tools.getAllSameElement(ManageClientThread.getOnlineUserIDs(), DBFactory.getFriendsByID(uID));
//		List<String> list = new ArrayList<String>(Tools.stringToList(ManageClientThread.getOnlineUserIDs()));
		System.out.println("all online friends of uID:" + uID + " " +list.toString());
		Message m = new Message();
		if(msgType == MSGType.ONLINEUSERS){
			m.setMsgType(MSGType.ONLINEUSERS);
			for(Iterator<String> it=list.iterator();it.hasNext();){
				String sender = it.next().toString();
//				String senderFriends = DBFactory.getFriendsByID(sender);
				String onlines = ManageClientThread.getOnlineUserIDs();
//				String senderOnline = Tools.listToString(Tools.getAllSameElement(ManageClientThread.getOnlineUserIDs(), senderFriends));
				m.setSender(sender);
//				m.setMsg(senderOnline);
				m.setMsg(onlines);
				m.setStates(state);
				m.setRecver(uID);
				server.Send(m, m.getSender());
			}
		}/*else if(msgType == MSGType.SYSEXIT){
			m.setMsgType(MSGType.SYSEXIT);
			for(Iterator<String> it=list.iterator();it.hasNext();){
				String sender = it.next().toString();
				if(sender == uID){
					System.out.println();
					continue;
				}
				m.setSender(sender);
				server.Send(m);
			}
		}*/
	}
	
	public static boolean reLogin(String uID){
		return hm.containsKey(uID);
	}
	
	public static List<Message> isInList(String uID){
		List<Message> l = new ArrayList<Message>();
		for(Iterator<Message> it = msgList.iterator();it.hasNext();){
			Message m = it.next();
			String cfID = m.getRecver();
//			System.out.println("cfID="+cfID+" ubothID="+uID);
			if(cfID.equals(uID)){
				l.add(m);
			}
		}
		return l;
	}
	
}
