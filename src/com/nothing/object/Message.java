package com.nothing.object;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable{
	
	private int msgType;
	private int states;
	private String sender;
	private String recver;
	private String msg;
	private String msgTime;
	/** 附加字段 */
	private String comment;
	/** 数据库查询的list字段 */
	private List<Users> users;
	/** 数据库查询的list长度 */
	private int size;
	
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecver() {
		return recver;
	}
	public void setRecver(String recver) {
		this.recver = recver;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<Users> getUsers() {
		return users;
	}
	public void setUsers(List<Users> users) {
		this.users = users;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
