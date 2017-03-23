package com.nothing.c.socket;

import java.util.concurrent.ConcurrentHashMap;

public class ManageLoginThread {
	
	public static ConcurrentHashMap<String, CtoSThread> hm = new ConcurrentHashMap<String, CtoSThread>();
	
	public static void addLoginThread(String uid, CtoSThread cs){
		hm.put(uid, cs);
//		System.out.println(hm.hashCode());
//		System.out.println("uid:"+uid+"||||||ClientLogin:"+hm.size());
	}
	
	public static CtoSThread getCurrentLoginThread(String uid){
		return hm.get(uid);
	}
}
