package com.nothing.c.video;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.media.Buffer;
import javax.media.CannotRealizeException;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.nothing.form.base.MinCloseForm;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class TakePhoto extends MinCloseForm {

	private CaptureDeviceInfo captureDeviceInfo = null;
	private MediaLocator mediaLocator = null;
	private static Player player = null;
	private Buffer buffer = null;
	private VideoFormat videoFormat = null;
	private BufferToImage bufferToImage = null;
	private Image image = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TakePhoto frame = new TakePhoto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TakePhoto() {
		setBounds(100, 100, 340, 310);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setBackground(c);
		getContentPane().add(panel, BorderLayout.CENTER);
		super.btnExit.setLocation(this.getWidth()-27, 0);
		super.btnMini.setLocation(this.getWidth()-50, 0);
		panel.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		
		Video v = new Video();
		
		JPanel videoPan = new JPanel();
		videoPan.setLayout(new BorderLayout());
		videoPan.add(v);
		panel.add(videoPan, BorderLayout.CENTER);
		
		JPanel btnPan = new JPanel();
		panel.add(btnPan, BorderLayout.SOUTH);
		
		JButton sureBtn = new JButton("\u62CD\u7167");
		sureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameGrabbingControl fgc = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
				buffer = fgc.grabFrame();
				bufferToImage = new BufferToImage((VideoFormat) buffer.getFormat());
				image = bufferToImage.createImage(buffer);
				saveImage(image, "D:\\temp.jpg");
			}
		});
		btnPan.add(sureBtn);
		
		JButton cancelBtn = new JButton("\u53D6\u6D88");
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				player.close();
				dispose();
			}
		});
		btnPan.add(cancelBtn);
		
		super.btnExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				player.close();
			}
		});
	}
	
	class Video extends JPanel{
		public Video(){
			String str = "vfw:Microsoft WDM Image Capture (Win32):0";
			captureDeviceInfo = CaptureDeviceManager.getDevice(str);
			mediaLocator = new MediaLocator("vfw://0");
			try {
				player = Manager.createRealizedPlayer(mediaLocator);
				player.start();
				Component comp;
				if((comp = player.getVisualComponent()) != null)
					comp.setBounds(0, 0, 240, 100);
				this.add(comp);
			} catch (NoPlayerException e) {
				player.close();
				e.printStackTrace();
			} catch (CannotRealizeException e) {
				player.close();
				e.printStackTrace();
			} catch (IOException e) {
				player.close();
				e.printStackTrace();
			}
		}
	}
	
	public static void saveImage(Image image, String path) {
		BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(image, null, null);
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(path);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JPEGImageEncoder je = JPEGCodec.createJPEGEncoder(fos);
		JPEGEncodeParam jp = je.getDefaultJPEGEncodeParam(bi);
		jp.setQuality(1f, false);
		je.setJPEGEncodeParam(jp);
		try {
			je.encode(bi);
			fos.close();
		} catch (ImageFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
