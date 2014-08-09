package com.nothing.c.voice;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.nothing.c.socket.Client;
import com.nothing.global.MSGType;
import com.nothing.object.Message;

public class VoiceChat {
	
	public String cfid;
	
	public VoiceChat(){}
	public VoiceChat(String cfid){
		this.cfid = cfid;
	}
	
	public void sendVoiceResquest(){
		String[] IDs = cfid.split("-");
		String sender = IDs[0], recver = IDs[1];
		Message m = new Message();
		m.setMsgType(MSGType.VOICECHATREQUEST);
		m.setSender(sender);
		m.setRecver(recver);
		Client c = new Client();
		c.Send(m);
	}
}
