package com.nothing.factory;

import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.nothing.object.Users;

public class TreeFactory {
	
	private JTree tree;
	private String uID;
//	private DefaultMutableTreeNode root;
	
	public TreeFactory(){}
	public TreeFactory(String uID, JTree tree){
		this.uID = uID;
		this.tree = tree;
	}
	
	/*public void selNode(String findName){
		System.out.println("findUser:"+findName);
//		UserListOpera ul = new UserListOpera(tree, uID);
		DefaultMutableTreeNode node = searchNode(findName);
		DefaultTreeModel model = new DefaultTreeModel(root);
		if(node != null){
			TreeNode[] nodes = model.getPathToRoot(node);
			TreePath path = new TreePath(nodes);
			tree.scrollPathToVisible(path);
			tree.setSelectionPath(path);
		}
	}
	
	public DefaultMutableTreeNode searchNode(String findName){
		DefaultMutableTreeNode node = null;
		System.out.println("ManageTree.getNodeByID.size:"+ManageTree.hmNode.size());
		if(root == null){
			System.out.println("root is null");
		}else{
			System.out.println("leftCount:"+root.getLeafCount()+"----------------"+uID);
			System.out.println("hashcode="+root.hashCode());
			System.out.println(root.hashCode());
		}
		Enumeration e = root.breadthFirstEnumeration();
		System.out.println("findName:"+findName);
		while(e.hasMoreElements()){
			node = (DefaultMutableTreeNode)e.nextElement();
			if(node.isLeaf()){
				if(node.getUserObject() instanceof Users){
					Users user = (Users)node.getUserObject();
					if(findName.equals(user.getuNickName())){
						System.out.println("in the rults:"+user.getuNickName());
						return node;
					}
				}
			}
		}
		return null;
	}*/
	
	public void addTreeNode(DefaultMutableTreeNode node, Users user){
		((DefaultTreeModel)tree.getModel()).insertNodeInto(new DefaultMutableTreeNode(user), node, node.getChildCount());
	}
	
	public void delTreeNode(DefaultMutableTreeNode node){
		((DefaultTreeModel) tree.getModel()).removeNodeFromParent(node);
	}
}
