package com.nothing.factory;

import java.util.ArrayList;
import java.util.List;

import com.nothing.clients.Nothing;
import com.nothing.global.Var;
import com.nothing.object.Groups;
import com.nothing.object.LoginInfo;
import com.nothing.object.Relations;
import com.nothing.object.Users;
import com.nothing.util.DB;
import com.nothing.util.Tools;

public class DBFactory {

	public static DB getDB(){
		return new DB();
	}
	
	public static boolean login(LoginInfo li){
		boolean b = false;
		String sql = "select * from users where uID='"+li.getUid()+"' and uPassword='"+li.getPwd()+"'";
		DB db = getDB();
		int i = db.exeQuery(sql, Users.class).size();
		if(i > 0){
			b = true;
		}
		return b;
	}
	
	public static List getFriends(String uID){
		List<Users> l = new ArrayList<Users>();
		DB db = getDB();
		Relations r = (Relations) db.exeQuery("select * from relations where uID='"+uID+"'", Relations.class).get(0);
		String friends = r.getFriends();
//		String blacks = r.getBlacks();
//		String stangers = r.getStrangers();
//		String groups = r.getGroups();
//		String recents = r.getRecents();
		List friend = Tools.stringToList(friends);
		for(int i=0;i<friend.size();i++){
			String sql = "select * from users where uid='"+friend.get(i)+"'";
			Users u = (Users)db.exeQuery(sql, Users.class).get(0);
			MapFactory.userMap.put(friend.get(i).toString(), u);
			l.add(u);
		}
		return l;
	}
	
	public static String getFriendsByID(String uID){
		List<Users> l = new ArrayList<Users>();
		DB db = getDB();
		Relations r = (Relations) db.exeQuery("select * from relations where relations.uID='"+uID+"'", Relations.class).get(0);
		String friends = r.getFriends();
		return friends;
	}
	
	public static List<Groups> getGroupsByID(String uID){
		List<Groups> l = new ArrayList<Groups>();
		List<Users> ul = new ArrayList<Users>();
		DB db = getDB();
		List relationL = db.exeQuery("select groups from relations where uID='"+uID+"'", Relations.class);
		if(relationL.size()>0){
			Relations r = (Relations)relationL.get(0);
			String groups = r.getGroups();
			List group = Tools.stringToList(groups);
			if(group != null){
				for(int i=0;i<group.size();i++){
					String sql = "select * from groups where gID='"+group.get(i)+"'";
					List groupL = db.exeQuery(sql, Groups.class);
					if(groupL.size()>0){
						Groups g = (Groups) groupL.get(0);
						l.add(g);
					}
				}
			}
		}
		return l;
	}
	
	public static List<String> getRecentsByID(String uID){
//		List<Groups> l = new ArrayList<Groups>();
//		List<Users> ul = new ArrayList<Users>();
		DB db = getDB();
		List<String> recent = new ArrayList<String>();
		List recentL = db.exeQuery("select recents from relations where uID='"+uID+"'", Relations.class);
		if(recentL.size()>0){
			Relations r = (Relations) recentL.get(0);
			String recents = r.getRecents();
			if(recents != null && !recents.trim().equals("")){
				recent = Tools.stringToList(recents);
			}
		}
		return recent;
	}
	
	public static Users getUserByID(String uID){
		Users user = null;
		if(MapFactory.userMap.get(uID) != null){
			user = MapFactory.userMap.get(uID);
		}else{
			DB db = getDB();
			String sql = "select * from users where uID='"+uID+"'";
			List l = db.exeQuery(sql, Users.class);
			if(l.size()==0){
				return null;
			}else{
				user = (Users) l.get(0);
				//判断当前用户是否在线，在线把isonline设为Var.ONLINE
//				System.out.println("DBFactory.在线好友列表："+MapFactory.onlineUsers);
				if(MapFactory.onlineUsers.indexOf(user.getuID()) != -1){
//					System.out.println("设置在线："+user.getuID());
					user.setIsOnline(Var.ONLINE);
				}
			}
		}
		return user;
	}
	
	public static void addRecentByID(String uID){
		DB db = new DB();
		List<String> recent = new ArrayList<String>(getRecentsByID(Nothing.uID));
		System.out.println("DBFactory.最近："+recent.toString());
		if(recent.contains(uID)){
			recent.remove(uID);
		}else{
			if(recent.size()>20){
				recent.remove(recent.size());
			}
		}
		String recents = uID+","+Tools.listToString(recent);
		String sql = "update relations set recents='"+recents+"' where uID='"+Nothing.uID+"'";
		System.out.println("DBFactory.加入最近："+sql);
		db.operaData(sql);
		Users user = getUserByID(uID);
		if(!UIFactory.recentTree.users.contains(user)){
			TreeFactory tfac = new TreeFactory(Nothing.uID, UIFactory.recentTree.tree);
			tfac.addTreeNode(UIFactory.recentTree.recentNode, user);
		}
	}

	public static List querySql(String sql, String[] params, Class clazz){
		List l = getDB().exeQuery(sql, params, clazz);
		return l;
	}
	

	public static List querySql(String sql, Class clazz){
		List l = getDB().exeQuery(sql, clazz);
		return l;
	}
	
	public static int operaDB(String sql){
		int i = getDB().operaData(sql);
		return i;
	}
	
	/**
	 * 
	 * @author NOTHING
	 * 		检测你暗恋的人是否也在暗恋你
	 * @param uID 你暗恋人的ID
	 * @return
	 */
	public static boolean isInLove(String uID){
		String sql = "select uLover from users where uID='"+uID+"'";
		Users user = (Users) querySql(sql, Users.class).get(0);
		String lover = user.getuLover();
		if(lover.equals(Nothing.uID))
			return true;
		else
			return false;
	}

}