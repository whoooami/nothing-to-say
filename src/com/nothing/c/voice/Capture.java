package com.nothing.c.voice;

//音频捕获部分，
//Capture.java
import java.io.*; 
import javax.sound.sampled.*; 
import java.net.*;

/** 
* Title:        VoiceChat 
* Description: 音频捕捉（录音程序） 
* Copyright:    Copyright (c) 2001 
* Company: 
* @author        
* @version 1.0 
*/

class Capture implements Runnable {

       TargetDataLine line; 
       Thread thread; 
       Socket s; 
       BufferedOutputStream captrueOutputStream;
       String cfid;

       Capture(String cfid, Socket s){//构造器 取得socket以获得网络输出流 
    	   this.cfid = cfid;
    	   this.s=s; 
       }

       public void start() {

           thread = new Thread(this); 
           thread.setName("Capture"); 
           thread.start(); 
       }

       public void stop() { 
           thread = null; 
       }

       public void run() {

           try { 
             captrueOutputStream=new BufferedOutputStream(s.getOutputStream());//建立输出流 此处可以加套压缩流用来压缩数据 
           } 
           catch (IOException ex) { 
               return; 
           }

           AudioFormat format =new AudioFormat(8000,16,2,true,true);//AudioFormat(float sampleRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian） 
           DataLine.Info info = new DataLine.Info(TargetDataLine.class,format);

           try { 
               line = (TargetDataLine) AudioSystem.getLine(info); 
               line.open(format, line.getBufferSize()); 
           } catch (Exception ex) { 
               return; 
           }

           byte[] data = new byte[1024];//此处的1024可以情况进行调整，应跟下面的1024应保持一致 
           int numBytesRead=0; 
           line.start();
           ManageVoiceLine.addVoiceClientLine(cfid, line);

           while (thread != null) { 
               numBytesRead = line.read(data, 0,128);//取数据（1024）的大小直接关系到传输的速度，一般越小越快， 
               try { 
                 captrueOutputStream.write(data, 0, numBytesRead);//写入网络流 
               } 
               catch (Exception ex) { 
                   break; 
               } 
           }

           line.stop(); 
           line.close(); 
           line = null;

           try { 
               captrueOutputStream.flush(); 
               captrueOutputStream.close(); 
           } catch (IOException ex) { 
               ex.printStackTrace(); 
           } 
       } 
     
}

