package io.github.versatilevelociraptors.ld32.audio;

import java.util.HashMap;

import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Music;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Sound;
import com.badlogic.gdx.utils.Disposable;

public abstract class AudioManager implements Disposable {

	protected HashMap<String , Music> musics;
	protected HashMap<String , Sound> sounds;
	
	protected static final String ASSETS_MUSIC_PATH = "assets/music";
	protected static final String ASSETS_SOUND_PATH = "assets/sound";

	public AudioManager(){
		
	}

}
