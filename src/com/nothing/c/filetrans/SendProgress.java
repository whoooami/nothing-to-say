package com.nothing.c.filetrans;

import java.awt.Toolkit;

import javax.swing.SwingWorker;

public class SendProgress extends SwingWorker<Void,Void>{

	private SendFile send;
	private int progress = 0;
	private long totalSize;
	
	public SendProgress(SendFile send,long totalSize){
		this.send = send;
		this.totalSize = totalSize;
	}
	
	protected Void doInBackground() throws Exception {
		while(send.getSendSize()<totalSize){
			progress = (int)(send.getSendSize()/(float)(totalSize/100));
			this.setProgress(progress);
			try{
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	protected void done(){
		this.setProgress(100);
		Toolkit.getDefaultToolkit().beep();
	}

}
