package com.nothing.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nothing.c.socket.Client;
import com.nothing.global.MSGType;
import com.nothing.object.Message;

public class SysExit implements ActionListener {

	public String uid = "";
	public SysExit(){}
	public SysExit(String uid){
		this.uid = uid;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Message m = new Message();
		m.setMsgType(MSGType.SYSEXIT);
		m.setSender(uid);
		Client c = new Client();
		c.Send(m);
		System.exit(0);
	}

}
