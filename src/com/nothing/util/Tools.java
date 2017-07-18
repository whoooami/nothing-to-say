package com.nothing.util;

import java.awt.Component;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import com.nothing.c.sound.WavPlay;
import com.nothing.clients.Nothing;
import com.nothing.factory.DBFactory;

public class Tools {
	
	/**
	 * 
	 * @author NOTHING
	 * 		��ȡ������ͼƬ��ʾ�ĳߴ�
	 * @param imgPath ͼƬ�ļ�/�ļ��еĵ�ַ ����class����
	 * @param width Ҫ��ʾ�Ŀ��
	 * @param height Ҫ��ʾ�ĸ߶�
	 * @return ImageIcon icon
	 */
	public static ImageIcon getImageIcon(String imgPath, int width, int height){
/*//		System.out.println("---------------1----:"+imgPath);
		int i = 0;
		if(ManageIcon.getImageIconByPath(imgPath) != null){
			System.out.println(i+++"�� IO");
			return ManageIcon.getImageIconByPath(imgPath);
		}else{
			System.out.println("only frist");*/
			ImageIcon  icon = new ImageIcon(Nothing.class.getResource(imgPath));
			if(width == 0)
				width = icon.getIconWidth();
			if(height == 0)
				height = icon.getIconHeight();
			icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
//			System.out.println("path:"+imgPath+"icon:"+icon+"--------"+ManageIcon.hm.size());
//			ManageIcon.addImageIcon(imgPath, icon);
			return icon;
//		}
	}
	
	public static ImageIcon getGrayImg(String imgPath, int width, int height) {
//		System.out.println("---------------2----:"+imgPath);
		/*int i = 0;
		if(ManageIcon.getGrayImageIconByPath(imgPath) != null){
			System.out.println(i+++"no IO!");
			return ManageIcon.getGrayImageIconByPath(imgPath);
		}else{*/
//			System.out.println(imgPath);
			BufferedImage bi = null;
			try {
				/*InputStream is= Nothing.class.getResourceAsStream("/"+imgPath); 
				System.out.println(Nothing.class.getResourceAsStream(imgPath));
				System.out.println(Nothing.class.getResource(imgPath));
				System.out.println(is);
				bi = ImageIO.read(is);*/
				bi = ImageIO.read(Nothing.class.getResource(imgPath));
//				bi = ImageIO.read(new File(imgPath));
			} catch (IOException e) {
				System.out.println("�Ҳ���ͷ���ļ���");
				e.printStackTrace();
			}
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null);
			bi = op.filter(bi, null);
			ImageIcon icon = new ImageIcon(bi.getScaledInstance(width, height, Image.SCALE_DEFAULT));
	//		icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
//			ManageIcon.addGrayImageIcon(imgPath, icon);
			return icon;
//		}
	}
	
	public static ImageIcon resizeImage(ImageIcon icon, int width, int height){
		icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		return icon;
	}
	
	/**
	 * 
	 * @author NOTHING
	 * 		�ҳ������ִ�����ͬ��Ԫ��
	 * @param onlines	10000,10001,10002,10004
	 * @param friends	10000,10002,10003,10005
	 * @return	List[10000,10002]
	 */
	public static List<String> getAllSameElement(String onlines,String friends) {
		if(onlines == null || friends == null) {  
            return null;  
        }
		String[] strArr1 = onlines.split(",");
		String[] strArr2 = friends.split(",");
        Arrays.sort(strArr1);  
        Arrays.sort(strArr2);  
          
        List<String> list = new ArrayList<String>();  
          
        int k = 0;  
        int j = 0;  
        while(k<strArr1.length && j<strArr2.length) {  
            if(strArr1[k].compareTo(strArr2[j])==0) {  
                if(strArr1[k].equals(strArr2[j]) ) {  
                    list.add(strArr1[k]);  
                    k++;  
                    j++;  
                }  
                continue;  
            } else  if(strArr1[k].compareTo(strArr2[j])<0){  
                k++;  
            } else {  
                j++;  
            }  
        }  
       return list;  
    }
	
	public static String listToString(List<String> list){
		String res = "";
		for(Iterator<String> s = list.iterator();s.hasNext();){
			res += s.next().toString()+",";
		}
		if(res.indexOf(",") != -1){
			res = res.substring(0, res.lastIndexOf(","));
		}
		return res;
	}
	
	public static List<String> stringToList(String s){
		if(s == null){
			return null;
		}
		List<String> l = new ArrayList<String>();
		if(s.indexOf(",") != -1){
			String szu[] = s.split(",");
			l = Arrays.asList(szu);
		}else if(s.length() != 0){
			l.add(s);
		}else{
			l = null;
		}
		return l;
	}
	
	public static String getnow(){
		Date a=new Date();
		return "	"+a.toLocaleString();
	}
	
	public static void alert(String warn){
		JOptionPane.showMessageDialog(null, warn);
	}
	public static void alert(Component isWho, String warn){
		JOptionPane.showMessageDialog(isWho, warn);
	}
	
	public static int confirm(String ask,String title){
		int k=JOptionPane.showConfirmDialog(null, ask,title , JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		return k;
	}

	/**
	 * 在面板上显示消息
	 * @param tp
	 * @param sender
	 * @param recver
	 * @param msg
	 */
	public static void addMessage(JTextPane tp, String sender, String recver,String msg){
		tp.setText(tp.getText()+sender+" 对 "+recver+" 说 "+"\r"+"       "+Tools.formatMsg(msg)+"\r");
		tp.select(tp.getText().length(), tp.getText().length());
	}

	/**
	 * 在面板上显示消息
	 * @param tp
	 * @param sender
	 * @param msg
	 */
	public static void addMessage(JTextPane tp, String sender,String msg){
		tp.setText(tp.getText()+sender+" 说 "+"\r"+"       "+Tools.formatMsg(msg)+"\r");
		tp.select(tp.getText().length(), tp.getText().length());
	}
	
	public static String formatMsg(String msg){
		msg = msg.replace("\r\n", "\r\n"+"       ");
		return msg;
	}

	/**
	 * 在面板上显示消息
	 * @param tp
	 * @param msg
	 */
	public static void addMessage(JTextPane tp, String msg){
		tp.setText(tp.getText()+"       "+Tools.formatMsg(msg)+"\r");
	}

	/**
	 * 根据区间生成新用户账号
	 * @return
	 */
	public static String getAcountNum(){
		int baseNum = 10000,incrementNum = 1000000;
		String result = "";
		while(true){
			int uID = randomNum(baseNum, incrementNum);
			if(null == DBFactory.getUserByID(uID+"")){
				result = uID+"";
				break;
			}
		}
		return result;
	}
	
	public static int randomNum(int baseNum, int incrementNum){
		int result = (int) (baseNum + Math.random()*incrementNum);
		return result;
	}

	public static void playWav(String path){
		/*System.out.println(path);
		WavPlay p = new WavPlay(Nothing.class.getResource(path));
		p.play();*/
		/*try {
			InputStream fis = new FileInputStream(Nothing.class.getResource(path).getPath());
			AudioStream as = new AudioStream(fis);
			AudioPlayer.player.start(as);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		WavPlay w = new WavPlay();
		w.play(path);
	}
}
