package com.nothing.object;

import java.io.Serializable;

public class LoginInfo implements Serializable {
	
	private String uid = "";
	private String pwd = "";
	private String lastIP = "";
	private String lastTime = "";
	private String lastPlace = "";
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getLastIP() {
		return lastIP;
	}
	public void setLastIP(String lastIP) {
		this.lastIP = lastIP;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getLastPlace() {
		return lastPlace;
	}
	public void setLastPlace(String lastPlace) {
		this.lastPlace = lastPlace;
	}
}
