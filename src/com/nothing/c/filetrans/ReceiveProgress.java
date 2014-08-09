package com.nothing.c.filetrans;

import java.awt.Toolkit;
import java.io.File;

import javax.swing.SwingWorker;

public class ReceiveProgress extends SwingWorker<Void,Void>{

	private File file;
	private long totalSize;
	private int progress = 0;
	
	public ReceiveProgress(File file,long totalSize){
		this.file = file;
		this.totalSize = totalSize;
	}
	
	protected Void doInBackground() throws Exception {
		while(file.length()<totalSize){
			progress = (int)(file.length()/(float)(totalSize/100));
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
