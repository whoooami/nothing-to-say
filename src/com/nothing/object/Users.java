package com.nothing.object;

import java.io.Serializable;
import java.util.Date;

import com.nothing.global.Var;

public class Users implements Serializable {

	private int serial = 0;
	private String uID = "";
	private String uPassword = "";
	private String uName = "";
	/** 0:女 1:男 */
	private int uSex = 0;
	private Date uBirthday;
	private int uAge = 0;
	private String uCountry = "";
	private String uProvice = "";
	private String uCity = "";
	private String uTel = "";
	private String uMail = "";
	private String uNickName = "";
	private String uHeadImg = "";
	private String uWords = "";
	private int uPolicy = 0;
	private Date uRegDate;
	private int uForbid = 0;
	private String uLover = "";
	/** 在线状态 1：离线 0：在线*/
	private int isOnline = Var.OFFLINE;
	private int isHaveMsg = Var.NO;
	
	public Users(){
	}
	
	public Users(String nickName,String headImg,String userID){
		this.uNickName = nickName;
		this.uHeadImg = headImg;
		this.uID = userID;
	}
	
	public Users(String nickName,String headImg,String userID,String uWords){
		this.uNickName = nickName;
		this.uHeadImg = headImg;
		this.uID = userID;
		this.uWords = uWords;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getuID() {
		return uID;
	}

	public void setuID(String uID) {
		this.uID = uID;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public int getuSex() {
		return uSex;
	}

	public void setuSex(int uSex) {
		this.uSex = uSex;
	}

	public Date getuBirthday() {
		return uBirthday;
	}

	public void setuBirthday(Date uBirthday) {
		this.uBirthday = uBirthday;
	}

	public int getuAge() {
		return uAge;
	}

	public void setuAge(int uAge) {
		this.uAge = uAge;
	}

	public String getuCountry() {
		return uCountry;
	}

	public void setuCountry(String uCountry) {
		this.uCountry = uCountry;
	}

	public String getuProvice() {
		return uProvice;
	}

	public void setuProvice(String uProvice) {
		this.uProvice = uProvice;
	}

	public String getuCity() {
		return uCity;
	}

	public void setuCity(String uCity) {
		this.uCity = uCity;
	}

	public String getuTel() {
		return uTel;
	}

	public void setuTel(String uTel) {
		this.uTel = uTel;
	}

	public String getuMail() {
		return uMail;
	}

	public void setuMail(String uMail) {
		this.uMail = uMail;
	}

	public String getuNickName() {
		return uNickName;
	}

	public void setuNickName(String uNickName) {
		this.uNickName = uNickName;
	}

	public String getuHeadImg() {
		return uHeadImg;
	}

	public void setuHeadImg(String uHeadImg) {
		this.uHeadImg = uHeadImg;
	}

	public int getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}

	public int getIsHaveMsg() {
		return isHaveMsg;
	}

	public void setIsHaveMsg(int isHaveMsg) {
		this.isHaveMsg = isHaveMsg;
	}

	public String getuWords() {
		return uWords;
	}

	public void setuWords(String uWords) {
		this.uWords = uWords;
	}

	public int getuPolicy() {
		return uPolicy;
	}

	public void setuPolicy(int uPolicy) {
		this.uPolicy = uPolicy;
	}

	public Date getuRegDate() {
		return uRegDate;
	}

	public void setuRegDate(Date uRegDate) {
		this.uRegDate = uRegDate;
	}

	public int getuForbid() {
		return uForbid;
	}

	public void setuForbid(int uForbid) {
		this.uForbid = uForbid;
	}

	public String getuLover() {
		return uLover;
	}

	public void setuLover(String uLover) {
		this.uLover = uLover;
	}

	/**
	 * user HTML formation to format the components
	 */
	public String toString() {
		return "<html><center><b>" + uNickName + "(" + uID + ")</b>"
				+ "<i><font color=#0920F7> ["+ uWords +"]</font><i></html>";
	}
}
