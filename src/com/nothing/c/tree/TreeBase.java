package com.nothing.c.tree;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.nothing.factory.DBFactory;
import com.nothing.factory.MapFactory;
import com.nothing.object.Groups;
import com.nothing.object.Users;
import com.nothing.util.Tools;

public class TreeBase implements TreeSelectionListener {

	protected JTree tree;
	protected DefaultMutableTreeNode root;
	private List<Groups> qunlist = new ArrayList<Groups>();
	private List<Users> users = new ArrayList<Users>();
	private Groups group = null;
	protected String uID;
	
	public TreeBase(JTree Tree, Groups group, boolean isRecent){
		this.group = group;
		this.uID = group.getuID();
		root = new DefaultMutableTreeNode("root");
		TreeModel tm = new DefaultTreeModel(root);
		if(isRecent){
			createRecentUserList(root);
		}else{
			createGroupUserLists(root);
		}
		tree = Tree;
		tree.setModel(tm);
		tree.setRootVisible(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setCellRenderer(new UserListRenderer());
		tree.setToggleClickCount(1);	//���õ���չ���ڵ�
		tree.setRowHeight(24);
		tree.putClientProperty("JTree.lineStyle", "None");
		
		tree.addMouseListener(new TreeListener(tree, false));
	}
	
	protected void createGroupUserLists(DefaultMutableTreeNode root){
		String members = group.getMembers();
		List member = Tools.stringToList(members);
		for(int i=0;i<member.size();i++){
//			root.add(new DefaultMutableTreeNode(MapFactory.userMap.get(member.get(i))));
			root.add(new DefaultMutableTreeNode(DBFactory.getUserByID((String) member.get(i))));
		}
	}
	
	protected void createRecentUserList(DefaultMutableTreeNode root){
		DefaultMutableTreeNode recentNode = new DefaultMutableTreeNode("�����ϵ��");
		root.add(recentNode);
		List<String> recentL = DBFactory.getRecentsByID(uID);
//		users = DBFactory.getRecentsByID(uID);
		for(String uID : recentL){
			Users u = MapFactory.userMap.get(uID);
			users.add(u);
			recentNode.add(new DefaultMutableTreeNode(u));
		}
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
