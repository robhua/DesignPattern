package com.designpattern.structural;

/**
 * Adapter pattern works as a bridge between two incompatible interfaces.
 * Convert the interface of a class into another interface clients expect. The
 * demo following example that audio player device can play mp3 files only and
 * wants to use an advanced audio player capable of playing vlc files and mp4
 * files
 */
public class AdapterDemoMediaPlayer {
	public static void main(String[] args) {
		AudioPlayer audioPlayer = new AudioPlayer();

		audioPlayer.play("beyond the horizon.mp3", "mp3");
		audioPlayer.play("alone.mp4", "mp4");
		audioPlayer.play("far far away.vlc", "vlc");
		audioPlayer.play("mind me.avi", "avi");
	}
}

interface MediaPlayer {
	public void play(String fileName, String audioType);
}

/**
 * The class can play mp3 format audio files by default.
 */
class AudioPlayer implements MediaPlayer {
	private MediaPlayerAdapter adapter;

	public void play(String fileName, String audioType) {
		// inbuilt support to play mp3 music files
		if ("mp3".equals(audioType)) {
			System.out.printf("\nExecute mp3 file. Name: %s", fileName);

			// mediaAdapter is providing support to play other file formats
		} else if ("mp4".equals(audioType) || "vlc".equals(audioType)) {
			adapter = new MediaPlayerAdapter(audioType);
			adapter.play(fileName, audioType);

		} else {
			System.out.printf("\nInvalid media. %s format not supported",
					audioType);
		}
	}
}

class MediaPlayerAdapter implements MediaPlayer {
	private AdvanceMediaPlayer advanceMediaPlay;

	public MediaPlayerAdapter(String audioType) {
		if ("mp4".equals(audioType)) {
			advanceMediaPlay = new Mp4Player();
		} else if ("vlc".equals(audioType)) {
			advanceMediaPlay = new VlcPlayer();
		}
	}

	public void play(String fileName, String audioType) {
		if ("mp4".equals(audioType)) {
			advanceMediaPlay.playMp4(fileName);
		} else if ("vlc".equals(audioType)) {
			advanceMediaPlay.playVlc(fileName);
		}

	}
}

interface AdvanceMediaPlayer {
	public void playMp4(String fileName);

	public void playVlc(String fileName);
}

/**
 * The class can play vlc format file.
 */
class VlcPlayer implements AdvanceMediaPlayer {
	public void playVlc(String fileName) {
		System.out.printf("\nExecute Vlc file. Name: %s", fileName);
	}

	public void playMp4(String fileName) {
		// Do nothing
	}
}

/**
 * The class can play mp4 format file.
 */
class Mp4Player implements AdvanceMediaPlayer {
	public void playMp4(String fileName) {
		System.out.printf("\nExecute Mp4 file. Name: %s", fileName);
	}

	public void playVlc(String fileName) {
		// Do nothing
	}
}
