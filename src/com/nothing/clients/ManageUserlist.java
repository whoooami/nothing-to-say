package com.nothing.clients;

import java.util.HashMap;
import java.util.Iterator;

import com.nothing.c.tree.UserListOpera;

/**
 * 	准备打入冷宫，不建议再次使用！
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
		if(hm.containsKey(pkey))
			return true;
		else
			return false;
	}
}
