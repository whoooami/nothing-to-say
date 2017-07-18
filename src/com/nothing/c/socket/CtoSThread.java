package com.nothing.c.socket;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.nothing.c.filetrans.ProgressView;
import com.nothing.c.filetrans.ReceiveProgress;
import com.nothing.c.filetrans.RecvFile;
import com.nothing.c.filetrans.SendFile;
import com.nothing.c.filetrans.SendProgress;
import com.nothing.c.tree.UserListOpera;
import com.nothing.c.voice.VoiceClient;
import com.nothing.c.voice.VoiceServer;
import com.nothing.clients.ChatForm;
import com.nothing.clients.GroupForm;
import com.nothing.clients.LoveShow;
import com.nothing.clients.ManageChatForm;
import com.nothing.clients.Nothing;
import com.nothing.factory.DBFactory;
import com.nothing.factory.MapFactory;
import com.nothing.factory.UIFactory;
import com.nothing.global.MSGType;
import com.nothing.global.Constants;
import com.nothing.object.Message;
import com.nothing.object.Users;
import com.nothing.util.Tools;
import com.nothing.util.fadeOut;

public class CtoSThread extends Thread{

	public Socket s;
	public ObjectOutputStream oos = null;
	public ObjectInputStream ois = null;
	public boolean bConnect = false;
	//nothing tmp
	RecvFile rf = null;
	public CtoSThread(){
		
	}
	public CtoSThread(Socket s){
		this.s = s;
		bConnect = true;
		/*try {
			oos = new ObjectOutputStream(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void send(Message m){
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		try {
			while(bConnect){
				ois = new ObjectInputStream(s.getInputStream());
				Object o = ois.readObject();
				if(o instanceof Message){
					Message m = (Message)o;
					if(m.getMsgType() == MSGType.TEXTMSG){
						String cfID = m.getRecver()+"-"+m.getSender();
						if(ManageChatForm.isInMap(cfID)){
//							System.out.println("------------------1:CtoS");
							ChatForm cf = ManageChatForm.getChatFormByID(m.getRecver()+"-"+m.getSender());
							Tools.addMessage(cf.tpRecv, m.getSender(), m.getRecver(), m.getMsg());
							System.out.println("revt PACK:"+m.getSender()+"-"+m.getRecver()+":"+m.getMsg());
						}else{
//							System.out.println("------------------2:CtoS");
							ManageChatForm.msgList.add(ManageChatForm.msgList.size(), m);
							Client.setHasMsg(ManageChatForm.msgList);
							System.out.println("m:"+m.getSender()+"--"+m.getRecver()+":"+m.getMsg());
						}
					}else if(m.getMsgType() == MSGType.GROUPMSG){
						String gID = m.getComment();
//						System.out.println("CtoSThread.gID-list:"+UIFactory.groupFormMap.toString());
						if(UIFactory.groupFormMap.containsKey(gID)){
							GroupForm gf = UIFactory.groupFormMap.get(gID);
							Tools.addMessage(gf.tpRecv, m.getSender(), m.getMsg());
							System.out.println(gID+" 发送者:"+m.getSender()+" 消息:"+m.getMsg());
						}else{
							MapFactory.groupMsgList.add(MapFactory.groupMsgList.size(),m);
							Client.setHasMsg(MapFactory.groupMsgList);
							System.out.println(gID+" 发送者:"+m.getSender()+" 消息:"+m.getMsg());
						}
					}else if(m.getMsgType() == MSGType.ONLINEUSERS){
//						UserListOpera ul = ManageUserlist.getUserListByID(m.getSender());
						UserListOpera ul = UIFactory.nothingTree;
						ul.setOnlineUsers(m.getMsg());
//						Client.hmONline.put(m.getSender(), m.getMsg());
						MapFactory.onlineUsers = m.getMsg();
//						System.out.println("CtoSThread.onlineUsers: "+MapFactory.onlineUsers);
						UserListOpera.tree.repaint();
						//nothing
						if(!Nothing.uID.equals(m.getRecver())){
							if(m.getStates() == Constants.ONLINE){
								fadeOut fade = new fadeOut("消息提示","用户"+m.getRecver()+"已上线");
								fade.showDlg();
							}
						}
					}else if(m.getMsgType() == MSGType.SENDFILEREQUEST){
						ChatForm cf = ManageChatForm.getChatFormByID(m.getRecver()+"-"+ m.getSender());
						Users user = DBFactory.getUserByID(m.getSender());
						cf.headBtn.setIcon(Tools.getImageIcon(Constants.HEADIMGPATH+user.getuHeadImg(), 30, 30));
						cf.lblGameOver.setText(user.getuNickName());
						cf.lblSomeWords.setText(user.getuWords());
						if(m.getMsg().trim().equals("0")){
							cf.tpRecv.setText(cf.tpRecv.getText()+"已经接收文件");
						}else{
							String fileInfos[] = m.getMsg().split(",");
							String cfid = m.getRecver()+"-"+m.getSender();
							cf.setVisible(true);
//							cf.setFocusableWindowState(true);
//							ManageChatForm.ikey(1);
							
							String sender = m.getSender();
							m.setSender(m.getRecver());
							m.setRecver(sender);
							
							String fileInfo = "发送文件\n文件名:"+fileInfos[0]+"\n文件信息:"+fileInfos[2];
							int option = JOptionPane.showOptionDialog(cf, fileInfo, "接收文件", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
							if(option == JOptionPane.YES_OPTION){
								JFileChooser fc = new JFileChooser();
								fc.setSelectedFile(new File(fileInfos[0]));
								int re = fc.showSaveDialog(cf);
								if(re == JFileChooser.APPROVE_OPTION){
									System.out.println(m.getRecver()+"ͬ 接收文件 "+fileInfos[0]);
									File file = fc.getSelectedFile();
									long size = (long) ((Float.parseFloat(fileInfos[2].substring(0, fileInfos[2].length() - 2)) * 1000) - 4);
									
									//nothing
									ReceiveProgress rpro = new ReceiveProgress(file, size);
									// ��ʾ�ļ����ս���
									ProgressView pview = new ProgressView(file, rpro, "receive", cfid);
									pview.initialize();
									cf.fileTransView(pview);
//									ManageChatForm.addProgressView(cfid, pview);
//									cf.repaint();
									//nothing tmp��ʱ����취
									if(!ManageChatForm.b){
										rf = new RecvFile(cfid, file);
										rf.start();
										ManageChatForm.b = true;
									}else{
										rf.setFile(file);
									}
									m.setMsgType(MSGType.SENDFILERESPONSE);
									m.setStates(Constants.YES);
									this.send(m);
								}else{
									m.setMsgType(MSGType.SENDFILERESPONSE);
									m.setStates(Constants.NO);
									this.send(m);
									Tools.addMessage(cf.tpRecv, "用户:"+sender+" 接收 "+fileInfos[0]);
								}
							}else{
								m.setMsgType(MSGType.SENDFILERESPONSE);
								m.setStates(Constants.NO);
								this.send(m);
								Tools.addMessage(cf.tpRecv, "用户"+sender+"拒绝 "+fileInfos[0]);
							}
						}
					}else if(m.getMsgType() == MSGType.SENDFILERESPONSE){
						String cfid = m.getRecver()+"-"+m.getSender();
						ChatForm cf = ManageChatForm.getChatFormByID(cfid);
						System.out.println("cfid="+cfid);
						System.out.println(m.getSender()+"开始接收文件");
						String fileInfos[] = m.getMsg().split(",");
						if(m.getStates() == Constants.YES){
//							System.out.println("�Է�ͬ������ļ�");
							String filePath = fileInfos[1];
//							System.out.println(fileInfos[0]+"-"+fileInfos[1]);
							File file = new File(filePath);
							String ip = m.getComment();
							SendFile sf = new SendFile(file, cfid, ip);
							sf.start();// ��ʾ�ļ����ͽ���
//							ManageChatForm.operaFTCount(cfid, "+");		//����ͬ�ⷢ���ļ��󽫼�����һ
//							System.out.println(cfid+":��ǰ����ͬʱ�����ļ�������"+ManageChatForm.getFTCount(cfid));
							SendProgress spro = new SendProgress(sf, file.length());
							ProgressView pview = new ProgressView(file, spro, "send", cfid);
							pview.initialize();
							cf.fileTransView(pview);
//							ManageChatForm.addProgressView(cfid, pview);
							/*System.out.println("send:"+pview.progress.getProgress());
							if(pview.progress.getProgress() == 100){
								System.out.println("-----------");
								cf.defaultView(pview);
							}*/
						}else{
							Tools.addMessage(cf.tpRecv, "文件接收成功:"+fileInfos[0]);
						}
					}else if(m.getMsgType() == MSGType.VOICECHATREQUEST){
						ChatForm cf = ManageChatForm.getChatFormByID(m.getRecver()+"-"+ m.getSender());
						Users user = DBFactory.getUserByID(m.getSender());
						cf.headBtn.setIcon(Tools.getImageIcon(Constants.HEADIMGPATH+user.getuHeadImg(), 30, 30));
						cf.lblGameOver.setText(user.getuNickName());
						cf.lblSomeWords.setText(user.getuWords());
						if(m.getMsg()!=null && m.getMsg().trim().equals("0")){
							Tools.addMessage(cf.tpRecv, "接受语音");
						}else{
							String cfid = m.getRecver()+"-"+m.getSender();
							cf.setVisible(true);
//							cf.setFocusableWindowState(true);
//							ManageChatForm.ikey(1);
							
							String sender = m.getSender();
							m.setSender(m.getRecver());
							m.setRecver(sender);
							
							String voiceInfo = sender+"发起语音请求.";
							int option = JOptionPane.showOptionDialog(cf, voiceInfo, "发起语音", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
							if(option == JOptionPane.OK_OPTION){
								VoiceServer vs = new VoiceServer(cfid, 9200);
								vs.start();
								//nothing
//								ManageVoiceThread.addVoiceServerThread(cfid, VoiceServer.currentThread());
								m.setMsgType(MSGType.VOICECHATRESPONSE);
								m.setStates(Constants.YES);
								send(m);
								Tools.addMessage(cf.tpRecv, "接受"+sender+"语音请求");
							}else{
								m.setMsgType(MSGType.VOICECHATRESPONSE);
								m.setStates(Constants.NO);
								send(m);
								Tools.addMessage(cf.tpRecv, "拒绝"+sender+"语音请求");
							}
						}
					}else if(m.getMsgType() == MSGType.VOICECHATRESPONSE){
						String cfid = m.getRecver()+"-"+m.getSender();
						ChatForm cf = ManageChatForm.getChatFormByID(cfid);
						System.out.println("cfid="+cfid);
						System.out.println(m.getSender()+"接受语音请求");
						if(m.getStates() == Constants.YES){
							Tools.addMessage(cf.tpRecv, "接受语音请求");
//							System.out.println(fileInfos[0]+"-"+fileInfos[1]);
							String ip = m.getComment();
							VoiceClient vc = new VoiceClient(cfid, ip, 9200);
							vc.start();
//							ManageVoiceThread.addVoiceClientThread(cfid, VoiceClient.currentThread());
						}else{
							Tools.addMessage(cf.tpRecv, "拒绝语音请求");
						}
					}else if(m.getMsgType() == MSGType.VOICECHATINTERRUPT){
						String cfid = m.getSender()+"-"+m.getRecver();
						ChatForm cf = ManageChatForm.getChatFormByID(cfid);
//						System.out.println("cfid="+cfid);
						System.out.println(m.getSender()+"语音被中断");
						
						Tools.alert(cf, "语音被中断!");
						cf.tpRecv.setText("语音被中断!");
						System.out.println("END!"+cfid);
						System.out.println(cf);
					}else if(m.getMsgType() == MSGType.LOVEINTHEHOUSE){
						String s = "恭喜\n用户"+m.getSender()+"<br>已经爱上你了.<br>LET'S MOVE!";
						LoveShow love = new LoveShow(s);
						love.setVisible(true);
					}
					
				}else if(o instanceof String){
					System.out.println("文字消息:"+o.toString());
				}else{
					Tools.alert("未知消息类型.");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}
