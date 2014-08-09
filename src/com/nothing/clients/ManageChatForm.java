package com.nothing.clients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.nothing.c.filetrans.ProgressView;
import com.nothing.object.Message;


public class ManageChatForm {

	public static HashMap<String, ChatForm> hm = new HashMap<String, ChatForm>();
	/** 离线消息列表 */
	public static List<Message> msgList = new ArrayList<Message>();
	/** 文件传输的panel */
	public static HashMap<String, ProgressView> hmpv = new HashMap<String, ProgressView>();
	/** 一个窗口同时传输文件的数量 */
//	public static HashMap<String, String> hmFTCount = new HashMap<String, String>();
	//nothing tmp
	public static boolean b = false;
	public static boolean isAddFilePan = true;
	
	public static void addChatFormByID(String ID, ChatForm cf){
		hm.put(ID, cf);
	}
	
	public static ChatForm getChatFormByID(String ID){
		ChatForm cf = null;
		if(hm.get(ID) == null){
			String[] IDs = ID.split("-");
			System.out.println(IDs[0]+"-"+IDs[1]);
			cf = new ChatForm(IDs[0],IDs[1]);
			hm.put(ID, cf);
		}else{
			cf = hm.get(ID);
		}
//		cf.setVisible(true);
		return cf;
	}
	
	public static boolean isInMap(String pkey){
		boolean bIN = false;
		bIN = hm.containsKey(pkey);
		/*Iterator it = hm.keySet().iterator();
		while(it.hasNext()){
//			System.out.println("it.next="+it.next()+"  pkey="+pkey);
			String cfID = it.next().toString();
			if(cfID.equals(pkey)){
				bIN = true;
				break;
			}
		}*/
		return bIN;
	}
	
	public static List<Message> isInList(String uID){
		List<Message> l = new ArrayList<Message>();
//		System.out.println("-------------isInlist:msgList="+msgList.size());
		for(Iterator<Message> it = msgList.iterator();it.hasNext();){
			Message m = (Message)it.next();
//			System.out.println("----------------"+m.getSender());
			//nothing
			String cfID = m.getRecver()+"-"+m.getSender();
//			System.out.println("cfID="+cfID+" ubothID="+uID);
			if(cfID.equals(uID)){
				l.add(m);
			}
		}
		return l;
	}
	
	//nothing
	/*public static void ikey(int i){
		Iterator it = hm.keySet().iterator();
		System.out.println("当前ChatForm个数："+hm.size());
		System.out.println("当前ChatForm列表：");
		while(it.hasNext()){
			System.out.println("it.next=pkey="+it.next().toString());
		}
	}*/
	
	/*public static void addProgressView(String cfid, ProgressView view){
		hmpv.put(cfid, view);
	}
	
	public static ProgressView getProgressView(String cfid){
		return hmpv.get(cfid);
	}*/
	
	/*public static void operaFTCount(String cfid, String op){
		System.out.println(cfid+"文件传输线程"+"当前FTcount="+getFTCount(cfid));
		int count = 0;
		if(hmFTCount.containsKey(cfid)){
			count = Integer.parseInt(hmFTCount.get(cfid));
			if(op.equals("+")){
				count++;
				hmFTCount.put(cfid, String.valueOf(count));
				System.out.println("count++="+count);
			}else{
				count--;
				hmFTCount.put(cfid, String.valueOf(count));
				System.out.println("count--="+count);
			}
		}else{
			hmFTCount.put(cfid, "1");
		}
		System.out.println(cfid+"更改后的"+"FTcount="+getFTCount(cfid));
	}
	
	public static int getFTCount(String cfid){
		int count = 0;
		if(hmFTCount.containsKey(cfid)){
			return Integer.parseInt(hmFTCount.get(cfid));
		}else{
			return count;
		}
	}*/
}
