package com.nothing.c.socket;

import java.util.HashMap;

public class ManageLoginThread {
	
	public static HashMap<String, CtoSThread> hm = new HashMap<String, CtoSThread>();
	
	public static void addLoginThread(String uid, CtoSThread cs){
		hm.put(uid, cs);
//		System.out.println(hm.hashCode());
//		System.out.println("uid:"+uid+"||||||ClientLogin:"+hm.size());
	}
	
	public static CtoSThread getCurrentLoginThread(String uid){
		return (CtoSThread)hm.get(uid);
	}
}
