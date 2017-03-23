package com.nothing.clients;

import java.util.HashMap;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 
 * @author NOTHING
 */
public class ManageTree {
	
	public static HashMap<String, JTree> hm = new HashMap<String, JTree>();
	public static HashMap<String, DefaultMutableTreeNode> hmNode = new HashMap<String, DefaultMutableTreeNode>();
	
	public static void addTreeByID(String uID, JTree t){
		hm.put(uID, t);
	}
	
	public static JTree getTreeByID(String uID){
		return hm.get(uID);
	}
	
	public static void addNodeByID(String uID, DefaultMutableTreeNode t){
		hmNode.put(uID, t);
	}
	
	public static DefaultMutableTreeNode getNodeByID(String uID){
		return hmNode.get(uID);
	}
}
