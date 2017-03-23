package com.nothing.clients;

import java.util.HashMap;
import java.util.Iterator;

import com.nothing.c.tree.UserListOpera;

/**
 * 	׼�������乬���������ٴ�ʹ�ã�
 * @author NOTHING
 * @deprecated
 */
public class ManageUserlist {
	
	public static HashMap<String, UserListOpera> hm = new HashMap<String, UserListOpera>();
	
	public static void addUserListByID(String uID, UserListOpera ul){
		hm.put(uID, ul);
	}
	
	public static UserListOpera getUserListByID(String uID){
		return hm.get(uID);
	}
	
	public static boolean isInMap(String pkey){
		return hm.containsKey(pkey);
	}
}
