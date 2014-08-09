package com.nothing.util;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

import com.sun.awt.AWTUtilities;

/**
 * 屏幕右下角出现渐隐渐显的提示框 使用到了JDK1.6中新特性的透明窗体，所以必须要使用JDK1.6或以上版本的JDK 案例性质：原创案例
 * 案例来源：http://www.blackswansoft.com
 * 
 * 功能如下： 1.窗体出现时逐渐清晰 2.停留一会儿时间之后会自动逐渐模糊直至消失 3.点击关闭按钮后逐渐模糊直至消失 4.提示内容支持html标签
 * 
 * @author shk
 * 
 */
public class fadeOut implements Runnable {

	JDialog dlg;

	JLabel label1;

	JEditorPane editorPane1;

	private int width;// 窗体宽度

	private int height;// 窗体高度

	private int stayTime;// 休眠时间

	private String title, message;// 消息标题,内容

	private int style;// 窗体样式

	/**
	 * 渐隐渐显的提示框
	 * 
	 * @param width
	 *            提示框宽度
	 * @param height
	 *            提示框高度
	 * @param stayTime
	 *            提示框停留时间
	 * @param style
	 *            提示框的样式 以下为样式可选值： 0 NONE 无装饰（即去掉标题栏） 1 FRAME 普通窗口风格 2
	 *            PLAIN_DIALOG 简单对话框风格 3 INFORMATION_DIALOG 信息对话框风格 4
	 *            ERROR_DIALOG 错误对话框风格 5 COLOR_CHOOSER_DIALOG 拾色器对话框风格 6
	 *            FILE_CHOOSER_DIALOG 文件选择对话框风格 7 QUESTION_DIALOG 问题对话框风格 8
	 *            WARNING_DIALOG 警告对话框风格
	 * @param title
	 *            提示框标题
	 * @param message
	 *            提示框内容（支持html标签）
	 */
	public fadeOut(int width, int height, int stayTime, int style,
			String title, String message) {
		this.width = width;
		this.height = height;
		this.stayTime = stayTime;
		this.style = style;
		this.title = title;
		this.message = message;
	}

	/**
	 * 渐隐渐显的提示框
	 * 
	 * @param style
	 *            提示框样式同上
	 * @param title
	 *            提示框标题
	 * @param message
	 *            提示框内容
	 */
	public fadeOut(String title, String message) {
		this.width = 250;
		this.height = 180;
		this.stayTime = 5;
		this.style = 2;
		this.title = title;
		this.message = message;
	}

	public static void main(String[] args) {
		String title = "友情提示！";
		String message = "<strong>JDK1.6新特性测试</strong><br>《透明窗体》<br>作者：Not ME!";
		// Runnable translucent=new
		// TranslucentFrame(250,180,10,4,title,message);
		Runnable translucent = new fadeOut(title, message);
		Thread thread = new Thread(translucent);
		thread.start();
	}
	
	public void showDlg(){
		Runnable translucent = new fadeOut(title, message);
		Thread thread = new Thread(translucent);
		thread.start();
	}

	public void print() {
		dlg = new JDialog();
		editorPane1 = new JEditorPane();
		editorPane1.setEditable(false);// 不可编辑
		editorPane1.setContentType("text/html");// 将编辑框设置为支持html的编辑格式
		editorPane1.setText(message);
		dlg.add(editorPane1);

		dlg.setTitle(title);
		// 设置窗体的位置及大小
		int x = Toolkit.getDefaultToolkit().getScreenSize().width
				- Toolkit.getDefaultToolkit().getScreenInsets(dlg.getGraphicsConfiguration()).right
				- width - 5;
		int y = Toolkit.getDefaultToolkit().getScreenSize().height
				- Toolkit.getDefaultToolkit().getScreenInsets(dlg.getGraphicsConfiguration()).bottom
				- height - 5;
		dlg.setBounds(x, y, width, height);
		dlg.setUndecorated(true); // 去掉窗口的装饰
		dlg.getRootPane().setWindowDecorationStyle(style); // 窗体样式
		// frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG
		// ); //窗体样式
		AWTUtilities.setWindowOpacity(dlg, 0.01f);// 初始化透明度
		dlg.setVisible(true);
		dlg.setAlwaysOnTop(true);// 窗体置顶
		// 添加关闭窗口的监听
		dlg.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				hide();
			}
		});
	}

	/**
	 * 窗体逐渐变清晰
	 * 
	 */
	public void show() {
		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
			}
			AWTUtilities.setWindowOpacity(dlg, i * 0.02f);
		}
	}

	/**
	 * 窗体逐渐变淡直至消失
	 * 
	 */
	public void hide() {
		float opacity = 100;
		while (true) {
			if(opacity < 2) {
				System.out.println();
				break;
			}
			opacity = opacity - 2;
			AWTUtilities.setWindowOpacity(dlg, opacity / 100);
			try {
				Thread.sleep(20);
			} catch (Exception e1) {
			}
		}
		// frame.hide();
//		System.exit(0);
	}

	public void run() {
		print();
		show();
		try {
			Thread.sleep(stayTime * 1000);
		} catch (Exception e) {
		}
		hide();
	}
}