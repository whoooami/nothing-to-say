package com.nothing.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.nothing.c.tree.UserListOpera;

public class FindUser implements ActionListener {

	private JTree tree;
	private String uID, findName;
	
	public FindUser(String uID, JTree tree,String findName){
		this.uID = uID;
		this.tree = tree;
		this.findName = findName;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("findUser:"+findName);
		UserListOpera ul = new UserListOpera(tree, uID);
		DefaultMutableTreeNode node = ul.searchNode(findName);
		DefaultTreeModel model = new DefaultTreeModel(ul.getRoot());
		if(node != null){
			TreeNode[] nodes = model.getPathToRoot(node);
			TreePath path = new TreePath(nodes);
			tree.scrollPathToVisible(path);
			tree.setSelectionPath(path);
		}
//		ul.findInTree(findName);
	}

}
