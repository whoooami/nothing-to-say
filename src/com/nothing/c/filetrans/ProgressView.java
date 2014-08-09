package com.nothing.c.filetrans;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import com.nothing.clients.ManageChatForm;


public class ProgressView extends JPanel implements PropertyChangeListener{

	private JLabel fileIcon;
	private JLabel nameIcon;
	private JLabel openFile;
	private JLabel openDirector;
	private JPanel panel;
	private JProgressBar progressBar;
	
	private ImageIcon icon;
	
	private File file;
	public SwingWorker<Void,Void> progress;
	private String type;
	private String cfid;
	
	public ProgressView(File file,SwingWorker<Void,Void> progress,String type,String cfid){
		this.file = file;
		this.progress = progress;
		this.type = type;
		this.cfid = cfid;
		panel = this;
	}
	
	/*public String getUserName(){
		return userName;
	}*/
	
	public void initialize(){
		this.setPreferredSize(new Dimension(145, 70));
		this.setLayout(null);
		this.setOpaque(false);
//		count = ManageChatForm.getFTCount(cfid);
		//图标区
		/*if(type.equals("receive")){
			icon = new ImageIcon("image/receiveFile.jpg");
		}else{
			icon = new ImageIcon("image/sendFile.jpg");
		}
		fileIcon = new JLabel(icon);
		fileIcon.setBounds(2, 2, 29, 44);
		this.add(fileIcon);*/
		//文本区
		nameIcon = new JLabel(file.getName());
		nameIcon.setForeground(Color.black);
		nameIcon.setBounds(33, 2, 100, 44);
		this.add(nameIcon);
		//进度条
		progressBar = new JProgressBar(0,100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setBorderPainted(false);
		progressBar.setBounds(3, 49, 140, 15);
		this.add(progressBar);
		//添加进度监听器兵启动progress
		progress.addPropertyChangeListener(this);
		progress.execute();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if("progress".equals(evt.getPropertyName())){
			int i = (Integer)evt.getNewValue();
			progressBar.setValue(i);
			if(i==100){
				transmitionDone();
			}
		}
	}
	
	public void transmitionDone(){
		final ProgressView view = this;
//		String ids[] = cfid.split("-");
//		ManageChatForm.getChatFormByID(ids[1]+"-"+ids[0]).defaultView(view);
		if(type.equals("receive")){
//			System.out.println(cfid+"窗口一个文件传输完成");
//			ManageChatForm.operaFTCount(cfid, "-");
			this.remove(progressBar);
			
			openFile = new JLabel("<html><u>打开文件</u></html>");
			openFile.setBounds(3, 50, 60, 15);
			this.add(openFile);
			openFile.addMouseListener(new MouseAdapter(){
				
				public void mouseEntered(MouseEvent e){
					openFile.setForeground(Color.red);
				}
				public void mouseExited(MouseEvent e){
					openFile.setForeground(Color.black);
				}
				public void mouseReleased(MouseEvent e){
					if(Desktop.isDesktopSupported()){
						Desktop desktop = Desktop.getDesktop();
						try{
							desktop.open(file);
							ManageChatForm.getChatFormByID(cfid).defaultView(view);
						}catch(Exception ef){
							JOptionPane.showMessageDialog(null, "文件格式未知，无法打开！！！");
						}
					}
				}
			});
			
			openDirector = new JLabel("<html><u>打开目录</u></html>");
			openDirector.setBounds(73, 50, 60, 15);
			this.add(openDirector);
			openDirector.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent e){
					openDirector.setForeground(Color.red);
				}
				public void mouseExited(MouseEvent e){
					openDirector.setForeground(Color.black);
				}
				public void mouseReleased(MouseEvent e){
					File director = new File(file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf("\\")));
					if(Desktop.isDesktopSupported()){
						Desktop desktop = Desktop.getDesktop();
						try{
							desktop.open(director);
						}catch(Exception ef){
							ef.printStackTrace();
						}
						ManageChatForm.getChatFormByID(cfid).defaultView(view);
					}
				}
			});
		}else{
			if(progress.isDone()){
				ManageChatForm.getChatFormByID(cfid).defaultView(view);
			}
		}
		this.updateUI();
	}
}
