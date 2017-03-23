package com.nothing.object;

import com.nothing.global.Constants;

public class Groups {

	private String uID;   
	private String gID;
	private String members;
	private String gName; 
	private String gNote; 
	private String gKind;
	private String gLevel; 
	private String gNotice;
	private String gWords;
	private String gImg;
	
	private int isHaveMsg = Constants.NO;
	
	
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getgID() {
		return gID;
	}
	public void setgID(String gID) {
		this.gID = gID;
	}
	public String getMembers() {
		return members;
	}
	public void setMembers(String members) {
		this.members = members;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public String getgNote() {
		return gNote;
	}
	public void setgNote(String gNote) {
		this.gNote = gNote;
	}
	public String getgKind() {
		return gKind;
	}
	public void setgKind(String gKind) {
		this.gKind = gKind;
	}
	public String getgLevel() {
		return gLevel;
	}
	public void setgLevel(String gLevel) {
		this.gLevel = gLevel;
	}
	public String getgNotice() {
		return gNotice;
	}
	public void setgNotice(String gNotice) {
		this.gNotice = gNotice;
	}
	public String getgWords() {
		return gWords;
	}
	public void setgWords(String gWords) {
		this.gWords = gWords;
	}
	public String getgImg() {
		return gImg;
	}
	public void setgImg(String gImg) {
		this.gImg = gImg;
	}
	
	public int getIsHaveMsg() {
		return isHaveMsg;
	}
	public void setIsHaveMsg(int isHaveMsg) {
		this.isHaveMsg = isHaveMsg;
	}
	
	public String toString() {
		return "<html><center><b>" + gName + "</b>["+ gKind +"]</html>";
	}
}
