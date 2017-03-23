package com.nothing.c.filetrans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;

import com.nothing.c.socket.Client;
import com.nothing.clients.ChatForm;
import com.nothing.clients.ManageChatForm;
import com.nothing.global.MSGType;
import com.nothing.object.Message;
import com.nothing.util.Tools;

public class SendFile extends Thread {
	
	public String ip;
	public String cfid;
	public File file;
	private long sendSize = 0;
	
	public SendFile(){}
	public SendFile(String cfid){
		this.cfid = cfid;
	}
	public SendFile(File file,String cfid,String ip){
		this.file = file;
		this.cfid = cfid;
		this.ip = ip;
	}
	
	public void run(){
		try {
			Socket s = new Socket(ip,9000);
			FileInputStream fis = new FileInputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
			BufferedInputStream bis = new BufferedInputStream(fis);
//			System.out.println("sendCFID="+cfid);
//			ManageChatForm.ikey(1);
			//nothing
//			ManageChatForm.operaFTCount(cfid, "+");
			Tools.addMessage(ManageChatForm.getChatFormByID(cfid).tpRecv, "开始发送文件");
			int tmp = 0;
			while(true){
				tmp = bis.read();
				if(-1 == tmp){
					bos.write(-1);
					break;
				}
				bos.write(tmp);
				sendSize++;
			}
			Tools.addMessage(ManageChatForm.getChatFormByID(cfid).tpRecv, file.getName()+"传输完成");
			bis.close();
			bos.flush();
			bos.flush();
			s.close();
			//nothing
//			ManageChatForm.operaFTCount(cfid, "-");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void chooseSendFile(){
		ChatForm cf = ManageChatForm.getChatFormByID(cfid);
		JFileChooser fileChooser = new JFileChooser();
		int option = fileChooser.showDialog(null, "请选择文件");
		if(option == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			String fileName = file.getName();
			String path = file.getAbsolutePath();
			String size = (float)(file.length()/1000)+"KB";
			Message m = new Message();
			m.setMsgType(MSGType.SENDFILEREQUEST);
			m.setSender(cf.sendID);
			m.setRecver(cf.recvID);
			m.setMsg(fileName+","+path+","+size);
			Client c = new Client();
			c.Send(m);
		}
		
	}
	public long getSendSize() {
		return sendSize;
	}
}
