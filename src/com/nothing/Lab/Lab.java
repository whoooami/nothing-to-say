package com.nothing.Lab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import com.nothing.clients.Nothing;
import com.nothing.util.Tools;

public class Lab{

	public static void main(String[] args) {
//		System.out.println(Tools.randomNum(10000, 1000000));
//		System.out.println(DBFactory.getUserByID("100000"));
//		System.out.println(Tools.getAcountNum());
//		Tools.playWav("/tones/msg.wav");
//		AePlayWave p = new AePlayWave("src/tones/msg.wav");
//		p.start();
		
		/* 测试声音的播放
		try {
			System.out.println(Nothing.class.getResource("/tones/msg.wav").getPath().substring(1));
			InputStream in = new FileInputStream(Nothing.class.getResource("/tones/msg.wav").getPath().substring(1));// 流文件
			try {
				AudioStream as = new AudioStream(in);// 创建AudioStream 对象
				AudioPlayer.player.start(as);// 开始播放
				// AudioPlayer.player.stop(as);//停止播放，本例没有设置播放时间，歌曲结束自动停止
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		*/
		
//		fadeOut fo = new fadeOut("...", ".......");
//		fo.showDlg();
		
		List2String();
	}
	
	/**
	 * 
	 * @author NOTHING
	 *  测试Tools.stringToList
	 */
	public static void tStoL(){
		String ts = "1,2,3";
		String res = "";
		List l = Tools.stringToList(ts);
		/*for(Iterator<String> s = l.iterator();s.hasNext();){
			res += s.next().toString()+",";
		}
		System.out.println(res);*/
		for(int i=0;i<l.size();i++){
			System.out.println(l.get(i));
		}
	}
	
	public static void tIndex(){
		String a="a,b,c,e,f",b="b,c",c="d",d="c";
		System.out.println(a.indexOf(b));
		System.out.println(a.indexOf(c));
		a = a.replace(","+d, "");
		System.out.println(a);
	}
	
	public static void tMap(){
		Map<String, String> a = new HashMap<String, String>();
		a.put("1", "a");
		a.put("2", "b");
		a.put("3", "c");
		System.out.println(a.get("1")+"  "+a.get("0"));
	}
	
	public static void List2String(){
		List l = new ArrayList();
		l.add("1");
		l.add("2");
		l.add("3");
		System.out.println(l.toString().substring(1, l.toString().length()-1));
		String s1 = "1, 2, 3";
		String s2 = "1, 3, 4";
		String[] s3 = s1.split(",");
		
		List li = Tools.getAllSameElement(s1, s2);
		System.out.println(li.toString());
	}

}
