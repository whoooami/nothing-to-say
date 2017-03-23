package com.nothing.factory;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.nothing.object.Message;
import com.nothing.object.Users;

public class MapFactory {
	
	public static String onlineUsers = "";

	public static ConcurrentMap<String, Users> userMap = new ConcurrentHashMap<String, Users>();
	
	public static List<Message> groupMsgList = new Vector<Message>();

	
//	public static Vector<Message> groupMsgList = new Vector<Message>();
	
	/*public static List<Message> getGroupMsgList() {
		return groupMsgList;
	}

	public synchronized static void addGroupMsg(Message msg){
		groupMsgList.add(msg);
	}
	
	public synchronized static void removeGroupMsg(Message msg){
		groupMsgList.remove(msg);
	}
	
	public synchronized static int sizeGroupMsg(){
		return groupMsgList.size();
	}
	
	public synchronized static List<Message> listGroupMsg(){
		List<Message> l = new ArrayList<Message>();
		for(Message m:groupMsgList){
			l.add(m);
		}
		return l;
	}*/
}
