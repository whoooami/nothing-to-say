package com.nothing.c.tree;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.nothing.clients.ManageImage;
import com.nothing.clients.Nothing;
import com.nothing.global.Var;
import com.nothing.object.Groups;
import com.nothing.object.Users;

public class UserListRenderer extends DefaultTreeCellRenderer {

	int i = 0;
	public static boolean isHidden = false;
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
	
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		if(expanded == false)
			setIcon(new ImageIcon(Nothing.class.getResource("/images/close.png")));
		else
			setIcon(new ImageIcon(Nothing.class.getResource("/images/open.png")));
		if(leaf){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
			if(node.getUserObject() instanceof Users){
				Users user = (Users)node.getUserObject();
				String headImg = user.getuHeadImg();
//				System.out.println(user.getuNickName()+" online:"+user.getIsOnline());
				if(user.getIsOnline() == Var.ONLINE){
//					System.out.println("-------------------"+user.getIsHaveMsg());
					if(user.getIsHaveMsg() == Var.YES){
						if(!isHidden)
							setIcon(ManageImage.getImage(Var.HEADIMGPATH+headImg, 20, 20));
						else
							setIcon(ManageImage.getImage("/images/head/0.jpg", 20, 20));
					}else{
						setIcon(ManageImage.getImage("/images/head/"+headImg, 20, 20));
					}
				}else{
					if(user.getIsHaveMsg() == Var.YES){
						if(!isHidden)
							setIcon(ManageImage.getGrayImage("/images/head/"+headImg, 20, 20));
						else
							setIcon(ManageImage.getImage("/images/head/0.jpg", 20, 20));
					}else{
						setIcon(ManageImage.getGrayImage("/images/head/"+headImg, 20, 20));
					}
				}
			}else if(node.getUserObject() instanceof Groups){
				Groups group = (Groups) node.getUserObject();
//				System.out.println("render_Group="+group.getIsHaveMsg());
				if(group.getIsHaveMsg() == Var.YES){
					if(!isHidden)
						setIcon(ManageImage.getImage(Var.GROUPIMGPATH+group.getgImg(), 20, 20));
					else
						setIcon(ManageImage.getImage("/images/head/0.jpg", 20, 20));
				}else{
//					System.out.println("Group="+group.getIsHaveMsg());
					setIcon(ManageImage.getImage(Var.GROUPIMGPATH+group.getgImg(), 20, 20));
				}
			}
		}
		return this;
	}
}
