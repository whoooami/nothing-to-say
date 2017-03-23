package com.nothing.evt;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nothing.c.filetrans.SendFile;
import com.nothing.c.voice.VoiceChat;
import com.nothing.clients.ChatForm;
import com.nothing.clients.ManageChatForm;
import com.nothing.global.Constants;

public class BorderSetEvt implements MouseListener{

//	public ChatForm cf = null;
	public String ID;
	public JButton[] buttons;
	public BorderSetEvt(String ID, JButton[] buttons){
		this.ID = ID;
		this.buttons = buttons;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String btnName = ((JButton)e.getSource()).getName();
		System.out.println(btnName);
//		Container c = e.getComponent().getParent().getParent().getParent().getParent().getParent().getParent();
		//���촰��
		if(btnName.startsWith("CF")){
			ChatForm cf = ManageChatForm.getChatFormByID(ID);
			if(Constants.SENDFILEBTN.equals(btnName)){
				//�ļ�����
				SendFile sf = new SendFile(ID);
				sf.chooseSendFile();
			}else if(Constants.VOICECHATBTN.equals(btnName)){
				//��������
				VoiceChat vc = new VoiceChat(ID);
				vc.sendVoiceResquest();
			}
		}else if(btnName.startsWith("NS") || btnName.startsWith("NT")){
			//
		}
//		System.out.println(c);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<buttons.length;i++){
			if(e.getSource().equals(buttons[i])){
				buttons[i].setBorder(BorderFactory.createBevelBorder(0));
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<buttons.length;i++){
			if(e.getSource().equals(buttons[i])){
				buttons[i].setBorder(BorderFactory.createEmptyBorder());
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<buttons.length;i++){
			if(e.getSource().equals(buttons[i])){
				buttons[i].setBorder(BorderFactory.createLoweredBevelBorder());
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<buttons.length;i++){
			if(e.getSource().equals(buttons[i])){
				buttons[i].setBorder(BorderFactory.createBevelBorder(0));
			}
		}
	}

}
