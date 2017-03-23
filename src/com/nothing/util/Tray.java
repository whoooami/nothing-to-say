package com.nothing.util;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.nothing.clients.Nothing;
import com.nothing.evt.SysExit;
import com.nothing.factory.DBFactory;
import com.nothing.global.Constants;

public class Tray {

	SystemTray tray = null;
	private TrayIcon trayIcon = null;
	private ImageIcon icon = null;
	Nothing nothing = null;
//	Nothing no = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Tray(){
		
	}
	
	public Tray(Nothing nothing){
		this.nothing = nothing;
	}
	
	/*public Tray(Nothing nothing,SystemTray tray,TrayIcon trayIcon){
		this.tray = tray;
		this.trayIcon = trayIcon;
		this.nothing = nothing;
	}*/
	
	public void createTray(boolean flag){
//		nothing.setVisible(flag);
		tray = SystemTray.getSystemTray();
//		icon = new ImageIcon(getClass().getResource("/images/p.jpg"));
		PopupMenu pm = new PopupMenu();
		MenuItem mi1 = new MenuItem("�˳�");
		MenuItem mi2 = new MenuItem("���๦��");
		pm.add(mi2);
		pm.add(mi1);
		mi1.addActionListener(new SysExit(Nothing.uID));
		mi2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, "���Ҹ�����һ�̫���ˣ�������ܻ�û����\n˳��˵һ�䣬��������ң���ǧ�����ң�");
			}
		});
		String headImgPath = Constants.HEADIMGPATH+DBFactory.getUserByID(Nothing.uID).getuHeadImg();
		trayIcon = new TrayIcon(Tools.getImageIcon(headImgPath, 16, 16).getImage(),"NOTHING",pm);
		try {
			tray.add(trayIcon);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		trayIcon.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 2){
//					tray.remove(trayIcon);
//					nothing.setExtendedState(Frame.NORMAL);
					nothing.setAlwaysOnTop(true);
					nothing.setVisible(true);
				}
			}
		});
	}
	
	public void hideInTask(){
		nothing.setVisible(true);
		tray.remove(trayIcon);
	}

}
