package com.nothing.c.voice;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class VoiceClient extends Thread {

	public String ip;
	public int port;
	public String cfid;
	
	public VoiceClient(String cfid, String ip, int port){
		this.cfid = cfid;
		this.ip = ip;
		this.port = port;
	}
	
	public void run(){
		try {
			Socket s = new Socket(ip, port);
			Capture c = new Capture(cfid, s);
			c.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
