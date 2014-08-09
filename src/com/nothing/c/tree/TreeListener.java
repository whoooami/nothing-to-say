package com.nothing.c.tree;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.nothing.clients.ChatForm;
import com.nothing.clients.GroupForm;
import com.nothing.clients.Nothing;
import com.nothing.object.Groups;
import com.nothing.object.Users;
import com.nothing.util.Tools;

public class TreeListener implements MouseListener {

	private JTree tree;
	private boolean isGroup;
	
	public TreeListener(JTree tree,boolean isGroup){
		this.tree = tree;
		this.isGroup = isGroup;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int selRow = tree.getRowForLocation(e.getX(), e.getY());
		TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
		if(selRow != -1){
			if(e.getClickCount() == 2){
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
				if(node.isLeaf()){
					if(node.getUserObject() instanceof Groups){
						Groups g = (Groups)node.getUserObject();
						GroupForm gf = new GroupForm(g);
						gf.openGroupForm();
					}else if(node.getUserObject() instanceof Users){
						System.out.println("user");
						Users ul = (Users)node.getUserObject();
						if(Nothing.uID.equals(ul.getuID())){
							Tools.alert("点自已干吗啊！欠揍啊！");
							return;
						}
						ChatForm cf = new ChatForm();
						cf.openChatForm(Nothing.uID, ul.getuID());
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
