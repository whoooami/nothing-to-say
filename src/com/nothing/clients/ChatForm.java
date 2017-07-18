package com.nothing.clients;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.nothing.c.filetrans.ProgressView;
import com.nothing.c.socket.Client;
import com.nothing.c.socket.CtoSThread;
import com.nothing.c.socket.ManageLoginThread;
import com.nothing.c.voice.ManageVoiceLine;
import com.nothing.evt.BorderSetEvt;
import com.nothing.factory.DBFactory;
import com.nothing.factory.UIFactory;
import com.nothing.form.base.MinCloseForm;
import com.nothing.global.MSGType;
import com.nothing.global.Constants;
import com.nothing.object.Message;
import com.nothing.object.Users;
import com.nothing.util.Tools;

public class ChatForm extends MinCloseForm {

//	public static ChatForm frame = null;
	private JButton btnSend = null;
	//�����
	public JPanel panel = null;
	public String sendID = null, recvID = null;
	public JTextPane tpRecv = null,tpSend = null;
	public JPanel panRight = null;
	public JButton headBtn = null;
	public JLabel lblGameOver = null, lblSomeWords = null;
	public JPanel filePan = new JPanel(new FlowLayout());
//	public JButton btnMini = null;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ChatForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public ChatForm(){
		
	}
	
	public ChatForm(String sendID,String recvID) {
		setBounds(100, 100, 430, 442);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.sendID = sendID;
		this.recvID = recvID;
		String cfid = sendID+"-"+recvID;
		this.setTitle(sendID+" 对 "+recvID+" 说");
		super.lbTitle.setText(sendID+" 对 "+recvID+" 说");
//		((JComponent) getContentPane()).setBorder(new EmptyBorder(5, 5, 5, 5));
		
		panel = new JPanel();
		panel.setBackground(c);
		getContentPane().add(panel, BorderLayout.CENTER);
		super.btnExit.setLocation(this.getWidth()-27, 0);
		super.btnMini.setLocation(this.getWidth()-50, 0);
		panel.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		
		JPanel panTop = new JPanel();
		panel.add(panTop, BorderLayout.NORTH);
		panTop.setPreferredSize(new Dimension(0, 70));
		panTop.setBackground(c);
		panTop.setLayout(null);
		

		final JButton[] buttons = new JButton[8];
		for(int i=0;i<buttons.length;i++){
			buttons[i] = new JButton();
			buttons[i].setBorder(null);
			buttons[i].setName(Constants.CFTOPBTN+i);
			buttons[i].setIcon(Tools.getImageIcon("/images/chat/chat"+(i+1)+".gif", 0, 0));
			panTop.add(buttons[i]);
			buttons[i].addMouseListener(new BorderSetEvt(cfid, buttons));
		}
		buttons[0].setBounds(7, 42, 43, 27);
		buttons[1].setBounds(50, 42, 43, 27);
		buttons[2].setBounds(93, 42, 43, 27);
		buttons[3].setBounds(136, 42, 30, 27);
		buttons[4].setBounds(166, 42, 30, 27);
		buttons[5].setBounds(196, 42, 37, 27);
		buttons[6].setBounds(233, 42, 43, 27);
		buttons[7].setBounds(276, 42, 30, 27);

		headBtn = new JButton("");
		headBtn.setIcon(Tools.getImageIcon("/images/head/p.jpg", 30, 30));
		headBtn.setBounds(10, 7, 30, 30);
		panTop.add(headBtn);

		lblGameOver = new JLabel("GAME OVER");
		lblGameOver.setBounds(50, 7, 74, 15);
		panTop.add(lblGameOver);

		lblSomeWords = new JLabel("some words");
		lblSomeWords.setBounds(50, 22, 300, 15);
		panTop.add(lblSomeWords);
		
		/*btnMini = new JButton("");
		btnMini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		btnMini.setIcon(new ImageIcon(ChatForm.class.getResource("/images/mini.png")));
		btnMini.setBounds(377, 0, 16, 16);
		panTop.add(btnMini);*/
		
		/*JButton btnExit = new JButton("");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setIcon(new ImageIcon(ChatForm.class.getResource("/images/exit.png")));
		btnExit.setBounds(399, 0, 16, 16);
		panTop.add(btnExit);*/
		super.btnExit.addActionListener(new ExitEvt());

		panRight = new JPanel();
		panel.add(panRight, BorderLayout.EAST);
		panRight.setPreferredSize(new Dimension(105, 0));
		panRight.setLayout(null);

		JLabel label = new JLabel("New label");
		label.setIcon(new ImageIcon(ChatForm.class.getResource("/images/show/qshow.png")));
		label.setBounds(0, 0, 105, 183);
		panRight.add(label);

		JLabel label_1 = new JLabel("New label");
		label_1.setIcon(new ImageIcon(ChatForm.class.getResource("/images/show/qshow2.png")));
		label_1.setBounds(0, 203, 105, 143);
		panRight.add(label_1);

		JPanel panCenter = new JPanel();
		panel.add(panCenter, BorderLayout.CENTER);
		panCenter.setLayout(null);
		
		tpRecv = new JTextPane();
		tpRecv.setEditable(false);
		JScrollPane spRecv = new JScrollPane();
		spRecv.setBounds(0, 0, 315, 189);
//		tpRecv.setBounds(0, 0, 315, 189);
		spRecv.setViewportView(tpRecv);
//		tpRecv.setCaretPosition(tpRecv.getText().length());
		panCenter.add(spRecv);

		tpSend = new JTextPane();
		JScrollPane spSend = new JScrollPane();
		spSend.setBounds(0, 212, 315, 110);
//		tpSend.setBounds(0, 212, 315, 110);
		spSend.setViewportView(tpSend);
		panCenter.add(spSend);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(ChatForm.class.getResource("/images/tmpbar.png")));
		button_1.setBounds(0, 189, 315, 23);
		panCenter.add(button_1);
		
		JButton btnClose = new JButton("\u5173\u95ED(C)");
		btnClose.addActionListener(new ExitEvt());
		btnClose.setMnemonic(KeyEvent.VK_C);
		btnClose.setBounds(141, 325, 78, 20);
		panCenter.add(btnClose);
		
		btnSend = new JButton("\u53D1\u9001(S)");
		btnSend.addActionListener(new sendEvt());
		/*btnSend.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});*/
		btnSend.setMnemonic(KeyEvent.VK_S);
		btnSend.setBounds(227, 325, 78, 20);
		panCenter.add(btnSend);

	}
	
	public class sendEvt implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnSend){
				try {
					CtoSThread cs = ManageLoginThread.getCurrentLoginThread(sendID);
					ObjectOutputStream oos = new ObjectOutputStream(cs.s.getOutputStream());
					Message m = new Message();
					m.setMsgType(MSGType.TEXTMSG);
					m.setMsg(tpSend.getText().trim());
					m.setRecver(recvID);
					m.setSender(sendID);
					oos.writeObject(m);
					Tools.addMessage(tpRecv, sendID, recvID, tpSend.getText().trim());
					DBFactory.addRecentByID(recvID);
					tpSend.setText("");
					tpSend.grabFocus();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
	}
	
	public class ExitEvt implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String cfid = sendID+"-"+recvID;
			TargetDataLine cline = ManageVoiceLine.getVoiceClientLine(cfid);
			if(cline != null && cline.isRunning()==true){
				System.out.println("C_cfid==="+cfid);
				System.out.println("Active="+cline.isActive()+"  available="+cline.available()+"   open="+cline.isOpen()+"   running="+cline.isRunning());
				cline.stop();
				System.out.println("STOP: Active="+cline.isActive()+"  available="+cline.available()+"   open="+cline.isOpen()+"   running="+cline.isRunning());
				cline.close();
				System.out.println("CLOSE: Active="+cline.isActive()+"  available="+cline.available()+"   open="+cline.isOpen()+"   running="+cline.isRunning());
				
				Message m = new Message();
				m.setMsgType(MSGType.VOICECHATINTERRUPT);
				m.setSender(sendID);
				m.setRecver(recvID);
				new Client().Send(m);
			}
			SourceDataLine sline = ManageVoiceLine.getVoiceServerLine(cfid);
			if(sline != null && sline.isRunning()==true){
				System.out.println("S_cfid==="+cfid);
				System.out.println("Active="+sline.isActive()+"  available="+sline.available()+"   open="+sline.isOpen()+"   running="+sline.isRunning());
				sline.stop();
				System.out.println("STOP: Active="+sline.isActive()+"  available="+sline.available()+"   open="+sline.isOpen()+"   running="+sline.isRunning());
				sline.close();
				System.out.println("CLOSE: Active="+sline.isActive()+"  available="+sline.available()+"   open="+sline.isOpen()+"   running="+sline.isRunning());
				
				Message m = new Message();
				m.setMsgType(MSGType.VOICECHATINTERRUPT);
				m.setSender(sendID);
				m.setRecver(recvID);
				new Client().Send(m);
			}
			ManageChatForm.hm.remove(cfid);
			dispose();
			/*if(vc!=null && vc.getState().equals(State.RUNNABLE)){
				System.out.println("the VCthread is running");
				int option = JOptionPane.showOptionDialog(ManageChatForm.getChatFormByID(cfid), "���ڽ����ﵥͨѶ���Ƿ�Ҫ�رգ�", "��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if(option == JOptionPane.YES_OPTION){
					vc.interrupt();
					if(vc.isInterrupted()){
						System.out.println("�����߳̽����ɹ�!");
					}
				}else{
					System.out.println("return");
					return;
				}
			}else if(ManageVoiceThread.getVoiceServerThread(cfid)!=null && ManageVoiceThread.getVoiceServerThread(cfid).getState().equals(State.RUNNABLE)){
				System.out.println("the VSthread is running");
			}else{
				System.out.println("Enter in close frame!");
				ManageChatForm.hm.remove(cfid);
				dispose();
			}*/
			/*if(vc!=null && vc.getState().equals(State.RUNNABLE)){
				System.out.println("the VCthread is running");
				int option = JOptionPane.showOptionDialog(ManageChatForm.getChatFormByID(cfid), "���ڽ����ﵥͨѶ���Ƿ�Ҫ�رգ�", "��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if(option == JOptionPane.YES_OPTION){
					vc.interrupt();
					if(vc.isInterrupted()){
						System.out.println("�����߳̽����ɹ�!");
					}
				}else{
					System.out.println("return");
					return;
				}
			}else if(ManageVoiceThread.getVoiceServerThread(cfid)!=null && ManageVoiceThread.getVoiceServerThread(cfid).getState().equals(State.RUNNABLE)){
				System.out.println("the VSthread is running");
			}else{
				System.out.println("Enter in close frame!");
				ManageChatForm.hm.remove(cfid);
				dispose();
			}*/
		}
		
	}

	/**
	 * 
	 * @author NOTHING
	 * 		��ǰ��ͼ�л�Ϊ�ļ�����
	 * @param view
	 * 		�ļ������panel
	 */
	public void fileTransView(ProgressView view){
//		System.out.println(cf.sendID+":"+cf.recvID);
//		this.panRight.removeAll();
		UIFactory.proview.add(view);
		this.setSize(470, 442);
//		System.out.println(this.panRight.getWidth()+"-----"+this.panRight.getHeight());
//		this.panRight.setSize(145, 346);
		this.btnExit.setLocation(this.getWidth()-27, 0);
		this.btnMini.setLocation(this.getWidth()-50, 0);
		this.panel.remove(this.panRight);
		this.panel.remove(filePan);
		filePan.removeAll();
		filePan.setPreferredSize(new Dimension(145, 0));
		for(ProgressView pview:UIFactory.proview){
			filePan.add(pview);
		}
		this.panel.add(filePan, BorderLayout.EAST);
		this.repaint();
	}
	
	public void defaultView(ProgressView view){
//		this.remove(view);
		this.setSize(430,442);
		this.btnExit.setLocation(this.getWidth()-27, 0);
		this.btnMini.setLocation(this.getWidth()-50, 0);
//		filePan.removeAll();
//		panel.remove(filePan);
//		this.panel.add(this.panRight, BorderLayout.EAST);
//		this.repaint();
		UIFactory.proview.remove(view);
		filePan.removeAll();
		for(ProgressView pview:UIFactory.proview){
			filePan.add(pview);
		}
		if(UIFactory.proview.size() == 0){
			filePan.removeAll();
			this.panel.remove(filePan);
			panel.add(panRight, BorderLayout.EAST);
		}
		this.repaint();
	}
	
	public void openChatForm(String uID, String recvID){
		ChatForm cf = ManageChatForm.getChatFormByID(uID+"-"+recvID);
		Users user = DBFactory.getUserByID(recvID);
		cf.headBtn.setIcon(Tools.getImageIcon(Constants.HEADIMGPATH+user.getuHeadImg(), 30, 30));
		cf.lblGameOver.setText(user.getuNickName());
		cf.lblSomeWords.setText(user.getuWords());
//		ChatForm cf = new ChatForm(uID,ul.getUid());
//		ManageChatForm.addChatFormByID(uID+"-"+ul.getUid(), cf);
		//�ж�������Ϣ����û��ǰ�û�����Ϣ
		List<Message> l = ManageChatForm.isInList(uID+"-"+recvID);
		System.out.println(uID+"离线消息数量: "+l.size());
		if(l.size()>0){
			for(Iterator<Message> it = l.iterator();it.hasNext();){
				Message m = it.next();
				Tools.addMessage(cf.tpRecv, m.getSender(), m.getRecver(), m.getMsg());
//				List<Users> l1 = ManageUserlist.getUserListByID(m.getRecver()).users;
				List<Users> l1 = UIFactory.nothingTree.users;
				for(Iterator<Users> i = l1.iterator();i.hasNext();){
					Users u = i.next();
					if(u.getuID().equals(m.getSender())){
						u.setIsHaveMsg(Constants.NO);
					}
				}
				ManageChatForm.msgList.remove(m);
			}
		}
//		Client.setHasMsg(ManageChatForm.msgList);
		cf.setVisible(true);
	}

}
