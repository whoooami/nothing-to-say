package com.nothing.c.sound;

import java.io.IOException;
import java.net.URL;

import javax.media.CannotRealizeException;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;

import com.nothing.clients.Nothing;

public class WavPlay implements ControllerListener{

	Player player = null;
	public WavPlay() {}

	public void play(String path) {
		URL url = Nothing.class.getResource(path);
		System.out.println(url);
		MediaLocator mediaLocator = new MediaLocator(url);
		try {
			player = Manager.createRealizedPlayer(mediaLocator);
		} catch (NoPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotRealizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player.addControllerListener (this);
		player.prefetch();
		player.start();
	}

	@Override
	public void controllerUpdate(ControllerEvent e) {
		// TODO Auto-generated method stub
		if (e instanceof EndOfMediaEvent) { 
            player.stop();
            player.close();
            return; 
        }
	}
}
