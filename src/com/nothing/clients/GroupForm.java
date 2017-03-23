package com.nothing.clients;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTree;

import com.nothing.c.socket.Client;
import com.nothing.c.tree.TreeBase;
import com.nothing.factory.MapFactory;
import com.nothing.factory.UIFactory;
import com.nothing.form.base.BaseForm;
import com.nothing.global.MSGType;
import com.nothing.global.Constants;
import com.nothing.object.Groups;
import com.nothing.object.Message;
import com.nothing.util.Tools;

public class GroupForm extends BaseForm {

	public static GroupForm frame = null;
	public JButton btnMini = null;
	public JTextPane notice;
	public JTextPane tpSend, tpRecv;
	private Groups group;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GroupForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public GroupForm(Groups g) {
		setBounds(100, 100, 450, 442);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		group = g;
		
		JPanel panTop = new JPanel();
		getContentPane().add(panTop, BorderLayout.NORTH);
		panTop.setPreferredSize(new Dimension(0, 70));
		panTop.setBackground(c);
		panTop.setLayout(null);
		

		final JButton[] buttons = new JButton[8];
		for(int i=0;i<buttons.length;i++){
			buttons[i] = new JButton();
			buttons[i].setBorder(null);
			buttons[i].setIcon(Tools.getImageIcon("/images/chat/chat"+(i+1)+".gif", 0, 0));
			panTop.add(buttons[i]);
//			buttons[i].addMouseListener(new BorderSetEvt(buttons));
		}
		buttons[0].setBounds(7, 42, 43, 27);
		buttons[1].setBounds(50, 42, 43, 27);
		buttons[2].setBounds(93, 42, 43, 27);
		buttons[3].setBounds(136, 42, 30, 27);
		buttons[4].setBounds(166, 42, 30, 27);
		buttons[5].setBounds(196, 42, 37, 27);
		buttons[6].setBounds(233, 42, 43, 27);
		buttons[7].setBounds(276, 42, 30, 27);

		JButton button = new JButton("");
		button.setIcon(Tools.getImageIcon(Constants.GROUPIMGPATH+g.getgImg(), 30, 30));
		button.setBounds(10, 7, 30, 30);
		panTop.add(button);

		JLabel lblGameOver = new JLabel();
		lblGameOver.setText(g.getgName());
		lblGameOver.setBounds(50, 7, 74, 15);
		panTop.add(lblGameOver);

		JLabel lblSomeWords = new JLabel("�ǻҸ߼�Ⱥ");
		lblSomeWords.setBounds(50, 22, 74, 15);
		panTop.add(lblSomeWords);
		
		btnMini = new JButton("");
		btnMini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		btnMini.setIcon(new ImageIcon(GroupForm.class.getResource("/images/mini.png")));
		btnMini.setBounds(397, 0, 16, 16);
		panTop.add(btnMini);
		
		JButton btnExit = new JButton("");
		btnExit.addActionListener(new ExitEvt());
		btnExit.setIcon(new ImageIcon(GroupForm.class.getResource("/images/exit.png")));
		btnExit.setBounds(419, 0, 16, 16);
		panTop.add(btnExit);

		JPanel panRight = new JPanel();
		getContentPane().add(panRight, BorderLayout.EAST);
		panRight.setPreferredSize(new Dimension(125, 0));
		panRight.setLayout(null);
		
		notice = new JTextPane();
		notice.setText(g.getgNotice());
		notice.setEditable(false);
		notice.setBounds(0, 10, 125, 135);
		panRight.add(notice);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 145, 125, 217);
		panRight.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("\u7FA4\u6210\u5458\u5217\u8868\uFF1A");
		panel.add(label, BorderLayout.NORTH);
		
		JTree tree = new JTree();
		TreeBase groupUserTree = new TreeBase(tree, g, false);
		UIFactory.groupUserTreeMap.put(g.getgID(), groupUserTree);	//nothing del?
		panel.add(new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);

		JPanel panCenter = new JPanel();
		getContentPane().add(panCenter, BorderLayout.CENTER);
		panCenter.setLayout(null);

		tpSend = new JTextPane();
		tpSend.grabFocus();
		tpSend.setBounds(0, 225, 315, 110);
		panCenter.add(tpSend);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(GroupForm.class.getResource("/images/tmpbar.png")));
		button_1.setBounds(0, 201, 315, 23);
		panCenter.add(button_1);
		
		JButton btnClose = new JButton("\u5173\u95ED(C)");
		btnClose.addActionListener(new ExitEvt());
		btnClose.setMnemonic(KeyEvent.VK_C);
		btnClose.setBounds(142, 339, 78, 20);
		panCenter.add(btnClose);
		
		JButton btnSend = new JButton("\u53D1\u9001(S)");
		btnSend.addActionListener(new SendEvt());
		btnSend.setMnemonic(KeyEvent.VK_S);
		btnSend.setBounds(227, 339, 78, 20);
		panCenter.add(btnSend);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 315, 201);
		panCenter.add(scrollPane);
		
		tpRecv = new JTextPane();
		scrollPane.setViewportView(tpRecv);
	}
	
	public class SendEvt implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Client c = new Client();
			Message m = new Message();
			m.setMsgType(MSGType.GROUPMSG);
			m.setSender(Nothing.uID);
			m.setRecver(group.getMembers());
			m.setMsg(tpSend.getText());
			//��Ⱥ������뱸���ֶ�
			m.setComment(group.getgID());
			c.Send(m);
//			System.out.println("---------------------------fuck");
//			Tools.addMessage(tpRecv, m.getSender(), m.getMsg());
			tpSend.setText("");
			tpSend.grabFocus();
		}
	}
	
	public class ExitEvt implements ActionListener{
		public void actionPerformed(ActionEvent e) {
//			System.out.println(UIFactory.groupFormMap.size());
			UIFactory.groupFormMap.remove(group.getgID());
//			System.out.println(UIFactory.groupFormMap.size());
			dispose();
		}
	}
	
	public void openGroupForm(){
		GroupForm gf = null;
		if(UIFactory.groupFormMap.containsKey(group.getgID())){
			gf = UIFactory.groupFormMap.get(group.getgID());
		}else{
			gf = new GroupForm(group);
			UIFactory.groupFormMap.put(group.getgID(), gf);
		}
		List<Message> l = new ArrayList<Message>();
		for(Message m:MapFactory.groupMsgList){
			l.add(m);
		}
//		System.out.println(group.getgID()+"��������Ϣ:"+l.size());
		if(l.size()>0){
			for(Iterator<Message> it = l.iterator();it.hasNext();){
				Message m = it.next();
				if(m.getComment().equals(group.getgID())){
					Tools.addMessage(gf.tpRecv, m.getSender(), m.getMsg());
					System.out.println("GroupForm.Ⱥ��Ϣ��"+m.getSender()+":"+m.getMsg());
					group.setIsHaveMsg(Constants.NO);
					MapFactory.groupMsgList.remove(m);
				}
			}
		}
		gf.setVisible(true);
	}
}
