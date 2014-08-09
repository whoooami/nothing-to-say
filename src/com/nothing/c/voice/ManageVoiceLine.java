package com.nothing.c.voice;

import java.util.HashMap;

import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class ManageVoiceLine {
	
	public static HashMap<String, SourceDataLine> hmvs = new HashMap<String, SourceDataLine>();
	public static HashMap<String, TargetDataLine> hmvc = new HashMap<String, TargetDataLine>();
	
	public static void addVoiceServerLine(String cfid, SourceDataLine line){
//		System.out.println("ADD===VS======="+vs.getName()+":"+vs.getState());
		hmvs.put(cfid, line);
	}
	
	public static SourceDataLine getVoiceServerLine(String cfid){
		return hmvs.get(cfid);
	}
	
	public static void addVoiceClientLine(String cfid, TargetDataLine line){
//		System.out.println("ADD===VC======="+vc.getName()+":"+vc.getState());
		hmvc.put(cfid, line);
	}
	
	public static TargetDataLine getVoiceClientLine(String cfid){
		return hmvc.get(cfid);
	}
	
	/*public static HashMap<String, Thread> hmvs = new HashMap<String, Thread>();
	public static HashMap<String, Thread> hmvc = new HashMap<String, Thread>();
	
	public static void addVoiceServerThread(String cfid, Thread vs){
		System.out.println("ADD===VS======="+vs.getName()+":"+vs.getState());
		hmvs.put(cfid, vs);
	}
	
	public static Thread getVoiceServerThread(String cfid){
		return hmvs.get(cfid);
	}
	
	public static void addVoiceClientThread(String cfid, Thread vc){
		System.out.println("ADD===VC======="+vc.getName()+":"+vc.getState());
		hmvc.put(cfid, vc);
	}
	
	public static Thread getVoiceClientThread(String cfid){
		return hmvc.get(cfid);
	}*/
}
