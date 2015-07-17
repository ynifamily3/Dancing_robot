package robot;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Music {
	private String musicName;
	private AudioInputStream audioInputStream;
	private Clip clip;
	private boolean isPlaying;
	public Music(String aMusicName) {
		musicName = aMusicName;
		isPlaying = false;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(musicName).getAbsoluteFile());
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			clip.open(audioInputStream);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}
	public void playSound() {
		if(!isPlaying) {
			clip.start();
			clip.loop(999999);
			isPlaying = true;
		}
	}
	public void stopSound() {
		if(isPlaying) {
			clip.stop();
			isPlaying = false;
		}
	}
}
