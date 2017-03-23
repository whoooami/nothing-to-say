package com.nothing.c.filetrans;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.nothing.clients.ManageChatForm;
import com.nothing.util.Tools;

public class RecvFile extends Thread{

	public File file;
	public String cfid;
	public boolean flag = true;
	
	public RecvFile(){}
	public RecvFile(String cfid,File file){
		this.cfid = cfid;
		this.file = file;
	}
	
	public void run(){
		ServerSocket server = null;
		try {
			server = new ServerSocket(9000);
			while(flag){
				Socket s = server.accept();
				Recv r = new Recv(s);
				r.start();
			}
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		}
	}
	
	public class Recv extends Thread{
		
		public Socket s;
		public FileOutputStream fout;
		public Recv(Socket s){
			this.s = s;
		}
		
		public void run(){
			try {
				fout = new FileOutputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fout);
				BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
				Tools.addMessage(ManageChatForm.getChatFormByID(cfid).tpRecv, "文件名称:"+file.getName());
				int tmp = 0;
				while(true){
					tmp = bis.read();
					if(tmp == -1){
						break;
					}
					bos.write(tmp);
				}
				bis.close();
				bos.flush();
				bos.close();
				s.close();
				Tools.addMessage(ManageChatForm.getChatFormByID(cfid).tpRecv, "文件接收完成.");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setFile(File file) {
		this.file = file;
	}
	
}
