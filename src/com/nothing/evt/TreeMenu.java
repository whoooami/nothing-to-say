package com.nothing.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.nothing.clients.ChatForm;
import com.nothing.clients.Nothing;
import com.nothing.clients.UserInfoForm;
import com.nothing.factory.DBFactory;
import com.nothing.factory.TreeFactory;
import com.nothing.object.Users;
import com.nothing.util.Tools;

public class TreeMenu implements MouseListener {

	public JTree tree;
	public JPopupMenu popMenu;
	public TreeMenu(JTree tree){
		this.tree = tree;
		
		popMenu = new JPopupMenu();
		JMenuItem chatItem = new JMenuItem("对话");
		chatItem.setName("chat");
        chatItem.addActionListener(new MenuEvt());
        JMenuItem delItem = new JMenuItem("赐出");
        delItem.setName("del");
        delItem.addActionListener(new MenuEvt());
        JMenuItem infoItem = new JMenuItem("详细资料");
        infoItem.setName("info");
        infoItem.addActionListener(new MenuEvt());
        JMenuItem moreItem = new JMenuItem("更多功能");
        moreItem.setName("more");
        moreItem.addActionListener(new MenuEvt());

        popMenu.add(chatItem);
        popMenu.add(delItem);
        popMenu.add(infoItem);
        popMenu.add(moreItem);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		TreePath path = tree.getPathForLocation(e.getX(), e.getY());
		if(path == null){
			return;
		}else{
			tree.setSelectionPath(path);
		}
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		if(e.getButton() == 3){
			if(node.isLeaf() && node.getUserObject() instanceof Users){
				if(!((Users)node.getUserObject()).getuID().equals(Nothing.uID)){
					popMenu.show(tree, e.getX(), e.getY());
				}
			}
		}
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	public class MenuEvt implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			String menuName = ((JMenuItem)e.getSource()).getName();
			Users user = (Users) node.getUserObject();
			if(menuName.equals("chat")){
				ChatForm cf = new ChatForm();
				cf.openChatForm(Nothing.uID, user.getuID());
			}else if(menuName.equals("del")){
				if(JOptionPane.YES_OPTION == Tools.confirm("确定删除"+user.getuNickName()+"?", "删除确认")){
					TreeFactory fac = new TreeFactory(Nothing.uID, tree);
					fac.delTreeNode(node);
					String friends = DBFactory.getFriendsByID(Nothing.uID);
					String newFriends = friends.replace(","+user.getuID(), "");
					String sql = "update relations set friends='"+ newFriends +"' where uID='"+Nothing.uID+"'";
					int i = DBFactory.operaDB(sql);
					if(i>0){
						Tools.alert(user.getuNickName()+"已经被踢出好友名单");
					}else{
						Tools.alert("删除失败，请重试！");
					}
				}
			}else if(menuName.equals("info")){
				UserInfoForm uinfo = new UserInfoForm();
				uinfo.fillInField(DBFactory.getUserByID(user.getuID()));
				uinfo.setVisible(true);
			}else if(menuName.equals("more")){
				Tools.alert("哈哈，上当了吧！");
			}
		}
	}

}
