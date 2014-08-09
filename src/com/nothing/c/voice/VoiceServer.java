package com.nothing.c.voice;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class VoiceServer extends Thread {

	public int port;
	public String cfid;
	public VoiceServer(String cfid, int port){
		this.cfid = cfid;
		this.port = port;
	}
	
	public void run(){
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			Socket s = server.accept();
			Playback pb = new Playback(cfid, s);
			pb.start();
		} catch (IOException e) {
			System.out.println("throw exception-----------");
			e.printStackTrace();
		}
	}
}
