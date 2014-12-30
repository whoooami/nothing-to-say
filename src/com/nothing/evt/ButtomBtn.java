package com.nothing.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.nothing.c.video.TakePhoto;
import com.nothing.clients.LoveHouse;
import com.nothing.clients.SearchConditionForm;
import com.nothing.util.Tools;

public class ButtomBtn implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnName = ((JButton)e.getSource()).getName();
		if(btnName.equals("findBtn")){
			SearchConditionForm sc = new SearchConditionForm();
			sc.setVisible(true);
		}else if(btnName.equals("loveBtn")){
			LoveHouse loveHouse = new LoveHouse();
			loveHouse.setVisible(true);
		}else if(btnName.equals("photoBtn")){
			System.out.println("photo");
			TakePhoto photo = new TakePhoto();
			photo.setVisible(true);
		}else if(btnName.equals("pandaBtn")){
			Tools.alert("Hi!It's me!\nauther: GAME OVER\nE-mail: nothing.whoami@gmail.com");
		}
	}

}
