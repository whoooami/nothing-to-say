package com.nothing.c.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.nothing.clients.ChatForm;
import com.nothing.clients.ManageChatForm;
import com.nothing.clients.ManageTree;
import com.nothing.clients.ManageUserlist;
import com.nothing.clients.Nothing;
import com.nothing.factory.DBFactory;
import com.nothing.factory.MapFactory;
import com.nothing.factory.UIFactory;
import com.nothing.global.Var;
import com.nothing.object.Message;
import com.nothing.object.Users;
import com.nothing.util.Tools;
import com.sun.jmx.snmp.UserAcl;

public class UserListOpera implements TreeSelectionListener{
	
	public static JTree tree;
	public static DefaultMutableTreeNode root;
	private String uID = "";
	public static DefaultMutableTreeNode friendNode = null;
	public ArrayList<Users> users = new ArrayList<Users>();
	
	public DefaultMutableTreeNode getRoot() {
		return root;
	}

	public UserListOpera(){
		
	}
	
	public UserListOpera(JTree Tree, final String uID){
		this.uID = uID;
		root = new DefaultMutableTreeNode("root");
		TreeModel tm = new DefaultTreeModel(root);
		//nothing
		ManageTree.addNodeByID(uID, root);
		
		createUserLists(root);
		tree = Tree;
		tree.setModel(tm);
		tree.setRootVisible(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setCellRenderer(new UserListRenderer());
		tree.setToggleClickCount(1);	//设置单击展开节点
		tree.setRowHeight(24);
		tree.putClientProperty("JTree.lineStyle", "None");
		MouseListener ml = new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				int selRow = tree.getRowForLocation(e.getX(), e.getY());
				TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				if(selRow != -1){
					if(e.getClickCount() == 2){
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
						if(node.isLeaf()){
							if(node.getUserObject() instanceof Users){
								Users ul = (Users)node.getUserObject();
								if(uID.equals(ul.getuID())){
									Tools.alert("点自已干吗啊！欠揍啊！");
									return;
								}
								ChatForm cf = new ChatForm();
								cf.openChatForm(uID, ul.getuID());
								/*ChatForm cf = ManageChatForm.getChatFormByID(uID+"-"+ul.getuID());
//								ChatForm cf = new ChatForm(uID,ul.getUid());
//								ManageChatForm.addChatFormByID(uID+"-"+ul.getUid(), cf);
								//判断离线消息中有没当前用户的消息
								List<Message> l = ManageChatForm.isInList(uID+"-"+ul.getuID());
								System.out.println(uID+"有离线消息:"+l.size());
								if(l.size()>0){
									for(Iterator<Message> it = l.iterator();it.hasNext();){
										Message m = (Message)it.next();
										Tools.addMessage(cf.tpRecv, m.getSender(), m.getRecver(), m.getMsg());
										List l1 = ManageUserlist.getUserListByID(m.getRecver()).users;
										for(Iterator<Users> i = l1.iterator();i.hasNext();){
											Users u = i.next();
											if(u.getuID().equals(m.getSender())){
												u.setIsHaveMsg(Var.NO);
											}
										}
										ManageChatForm.msgList.remove(m);
									}
								}
//								Client.setHasMsg(ManageChatForm.msgList);
								cf.setVisible(true);*/
							}
						}
					}
				}
			}
		};
		tree.addMouseListener(ml);
		//nothing
//		ManageTree.addTreeByID(uID, this.tree);
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void createUserLists(DefaultMutableTreeNode root){
		DefaultMutableTreeNode strangerNode = null;
		DefaultMutableTreeNode blackListNode = null;
		DefaultMutableTreeNode[] friends = null;
		DefaultMutableTreeNode[] strangers = null;
		DefaultMutableTreeNode[] blackLists = null;
		
		friendNode = new DefaultMutableTreeNode("我的好友");
		strangerNode = new DefaultMutableTreeNode("陌生人");
		blackListNode = new DefaultMutableTreeNode("黑名单");
		root.add(friendNode);
		root.add(strangerNode);
		root.add(blackListNode);
		
		List friend = DBFactory.getFriends(uID);
		int i = 0 ;
//		friends = new DefaultMutableTreeNode[11];
//		strangers = new DefaultMutableTreeNode[11];
		for(i=0;i<friend.size();i++){
			friendNode.add(new DefaultMutableTreeNode(friend.get(i)));
		}
		//添加我的好友到总好友里面
		users.addAll(friend);
		/*for(i=0;i<11;i++){
			Users user = new Users("friend"+i,"go.png",i+"");
			strangers[i] = new DefaultMutableTreeNode(user);
			strangerNode.add(strangers[i]);
		}*/
		
	}
	
	/**
	 * 
	 * @author NOTHING
	 * 		设置列表的在线状态
	 * @param onlineIDs 当前在线字串，以便头像显示为彩色！
	 */
	public void setOnlineUsers(String onlineIDs){
		String[] onlines = onlineIDs.split(",");
		int i=0,j=0;
		for(i=0;i<users.size();i++){
			Users user = users.get(i);
			user.setIsOnline(Var.OFFLINE);
//			MapFactory.userMap.put(user.getuID(), user);
		}
		//换下遍历设置在线算法
//		if(users.size()>0 && onlineIDs.length()>5){
//			
//		}
		for(i=0;i<users.size();i++){
			for(j=0;j<onlines.length;j++){
				Users user = users.get(i);
				if(user.getuID().toString().equals(onlines[j])){
					user.setIsOnline(Var.ONLINE);
				}
			}
		}
		Timer timer = new Timer(1000, new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
	         	UserListRenderer.isHidden = !UserListRenderer.isHidden;
	         	tree.repaint();
	         	UIFactory.groupTree.tree.repaint();
	         }
		});
		timer.start();
	}
	
	/**
	 * 
	 * @author NOTHING
	 * 		按照信息、在线、离线排序
	 * @param userList 好友列表
	 */
	public List<Users> sortUserList(List<Users> userList){
		List<Users> all = new ArrayList<Users>(), msg = new ArrayList<Users>(), online = new ArrayList<Users>(), outline = new ArrayList<Users>();
		for(Iterator<Users> i = userList.iterator();i.hasNext();){
			Users u = i.next();
			if(u.getIsHaveMsg() == Var.YES){
				msg.add(u);
			}else if(u.getIsOnline() == Var.ONLINE){
				online.add(u);
			}
		}
		userList.removeAll(msg);
		userList.removeAll(online);
		all.addAll(msg);
		all.addAll(online);
		all.addAll(userList);
		return new ArrayList<Users>(new HashSet<Users>(all));
	}
	
	/**
	 * 
	 * @author NOTHING
	 * 		查找树节点的方法->好友上面搜索框
	 * @param str 树节点字串
	 */
	public static void findInTree(String str) {
		Object root = tree.getModel().getRoot();
		TreePath treePath = new TreePath(root);
		treePath = findInPath(treePath, str);
		if(treePath != null) {
			tree.setSelectionPath(treePath);
			tree.scrollPathToVisible(treePath);
		}
	}

	public static TreePath findInPath(TreePath treePath, String str) {
		Object object = treePath.getLastPathComponent();
		if(object == null) {
			return null;
		}

		String value = object.toString();
		if(value.contains(str)) {
			return treePath;
		}
		else {
			TreeModel model = tree.getModel();
			int n = model.getChildCount(object);
			for (int i = 0; i < n; i++) {
				Object child = model.getChild(object, i);
				TreePath path = treePath.pathByAddingChild(child);
				path = findInPath(path, str);
				if(path != null) {
					return path;
				}
			}
			return null;
		}
	}
	
	public DefaultMutableTreeNode searchNode(String findName){
		DefaultMutableTreeNode node = null, root = getRoot();
		Enumeration e = root.breadthFirstEnumeration();
		while(e.hasMoreElements()){
			node = (DefaultMutableTreeNode)e.nextElement();
			if(node.isLeaf()){
				if(node.getUserObject() instanceof Users){
					Users user = (Users)node.getUserObject();
					if(findName.trim().equals(user.getuNickName())){
						return node;
					}
				}
			}
		}
		return null;
	}
	
}