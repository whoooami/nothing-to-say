package com.nothing.clients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import com.nothing.global.Constants;
import com.nothing.util.Tools;

/**
 * @author NOTHING
 *	<br>直接用getImage()、getGrayImage()取出相应该图片。减少磁盘的IO次数，提高程序效率。
 */
public class ManageImage {
	
	public static HashMap<String, ImageIcon> hm = new HashMap<String, ImageIcon>();
	public static HashMap<String, ImageIcon> hmGray = new HashMap<String, ImageIcon>();
	public static List<String> headImgPathlist = new ArrayList<String>();
	
	public static void addImage(String imgPath, ImageIcon icon){
		hm.put(imgPath, icon);
	}
	
	public static ImageIcon getImage(String imgPath, int width, int height){
		if(hm.get(imgPath) != null){
			return hm.get(imgPath);
		}else{
			ImageIcon icon = Tools.getImageIcon(imgPath, width, height);
			ManageImage.addImage(imgPath, icon);
			return icon;
		}
	}
	
	public static void addGrayImage(String imgPath, ImageIcon icon){
		hmGray.put(imgPath, icon);
	}
	
	public static ImageIcon getGrayImage(String imgPath, int width, int height){
		if(hmGray.get(imgPath) != null){
			return hmGray.get(imgPath);
		}else{
			ImageIcon icon = Tools.getGrayImg(imgPath, width, height);
			ManageImage.addGrayImage(imgPath, icon);
			return icon;
		}
	}
	
	public static List<String> allHeadImage(){
		
		headImgPathlist.add(Constants.HEADIMGPATH+"go.png");
		headImgPathlist.add(Constants.HEADIMGPATH+"1.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"12.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"13.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"16.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"18.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"20.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"23.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"28.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"29.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"30.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"32.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"36.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"37.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"38.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"40.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"41.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"45.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"49.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"50.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"51.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"52.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"55.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"57.jpg");
		headImgPathlist.add(Constants.HEADIMGPATH+"6.jpg");
		return headImgPathlist;
		
	}
	
	/*public static List<ImageIcon> getAllHeadImage(){
		List<String> l = allHeadImage();
		List<ImageIcon> headImgList = new ArrayList<ImageIcon>();
		for(int i=0;i<l.size();i++){
			headImgList.add(getImage(l.get(i), 20, 20));
		}
		return headImgList;
	}*/
	
	public static void loadAllHeadImage(){
		List<String> lpath = allHeadImage();
		for(int i=0;i<lpath.size();i++){
			String imgPath = lpath.get(i);
			addImage(imgPath, getImage(imgPath, 20, 20));
		}
	}
}
