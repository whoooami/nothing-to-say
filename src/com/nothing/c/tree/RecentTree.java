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

public class RecentTree implements TreeSelectionListener {

	public JTree tree;
	public DefaultMutableTreeNode root, recentNode;
	public List<Groups> qunlist = new ArrayList<Groups>();
	public List<Users> users = new ArrayList<Users>();
	protected String uID;
	
	public RecentTree(JTree Tree, final String uID){
		this.uID = uID;
		root = new DefaultMutableTreeNode("root");
		TreeModel tm = new DefaultTreeModel(root);
		
		createRecentList(root);
		tree = Tree;
		tree.setModel(tm);
		tree.setRootVisible(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setCellRenderer(new UserListRenderer());
		tree.setToggleClickCount(1);	//设置单击展开节点
		tree.setRowHeight(24);
		tree.putClientProperty("JTree.lineStyle", "None");
		tree.addMouseListener(new TreeListener(tree, false));
		tree.expandRow(0);
	}
	
	protected void createRecentList(DefaultMutableTreeNode root){
		recentNode = new DefaultMutableTreeNode("最近联系人");
		root.add(recentNode);
		List<String> recentL = DBFactory.getRecentsByID(uID);
//		users = DBFactory.getRecentsByID(uID);
//		System.out.println("RecentTree.最近联系人："+recentL.toString());
//		System.out.println("RecentTree.总工厂用户数量："+MapFactory.userMap.size());
		for(String recentID : recentL){
			Users u = MapFactory.userMap.get(recentID);
//			System.out.println("RecentTree.u="+u+"  "+recentID+"总工厂用户数量："+MapFactory.userMap.size());
			users.add(u);
			recentNode.add(new DefaultMutableTreeNode(u));
		}
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {

	}

}
