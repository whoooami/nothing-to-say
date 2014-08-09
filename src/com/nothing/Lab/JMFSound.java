package com.nothing.Lab;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.media.CannotRealizeException;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.sound.midi.ShortMessage;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class JMFSound extends Object   
implements ControllerListener {   
File soundFile;   
JDialog playingDialog;   
public static void main (String[] args) {   
    JFileChooser chooser = new JFileChooser();   
    chooser.showOpenDialog(null);   
    File f = chooser.getSelectedFile();   
    try {   
        JMFSound s = new JMFSound (f);   
    } catch (Exception e) {   
        e.printStackTrace();   
    }   
}   
public JMFSound (File f)   
    throws NoPlayerException, CannotRealizeException,   
            MalformedURLException, IOException {   
    soundFile = f;   
    // prepare a dialog to display while playing   
    JOptionPane pane = new JOptionPane ("Playing " + f.getName(),   
                            JOptionPane.PLAIN_MESSAGE);   
    playingDialog = pane.createDialog (null, "JMF Sound");   
    playingDialog.pack();   
// get a player   
    MediaLocator mediaLocator =   
        new MediaLocator(soundFile.toURL());   
    Player player =   
        Manager.createRealizedPlayer (mediaLocator);   
//    player.addControllerListener (this);   
      player.prefetch();   
      player.start();   
      playingDialog.setVisible(true);   
}   
// ControllerListener implementation   
public void controllerUpdate (ControllerEvent e) {   
   System.out.println (e.getClass().getName());   
   if (e instanceof EndOfMediaEvent) {   
        playingDialog.setVisible(false);   
        System.exit (0);   
    }   
}   
public void controlChange(ShortMessage event) {   
    // TODO Auto-generated method stub   
}   
}