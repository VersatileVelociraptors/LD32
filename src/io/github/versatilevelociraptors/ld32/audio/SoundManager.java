package io.github.versatilevelociraptors.ld32.audio;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Sound;

public class SoundManager extends AudioManager{

	public SoundManager(){
		sounds = new HashMap<String , Sound>();
	}

	public void addAllSoundInAssets(){
		File file = new File(ASSETS_SOUND_PATH);
		File[] mp3s = file.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String mp3) {
				return mp3.endsWith("mp3");
			}
		});

		for(File f : mp3s){
			addSound(f.getName().substring(0, f.getName().lastIndexOf('.')) , f.getPath());
		}

	}

	public void addSound(String name, Sound music){
		sounds.put(name, music);
	}

	public void addSound(String name, String path){
		sounds.put(name , (Sound) Gdx.audio.newSound(Gdx.files.internal(path)));
	}

	public void play(String name){
		sounds.get(name).play(.5f);
	}
	
	public void play(String name , float volume){
		sounds.get(name).play(volume);
	}

	public void loop(String name){
		sounds.get(name).loop(.5f);
	}

	public void stop(String name){
		sounds.get(name).stop();	
	}

	public void stop(){
		for (Sound music : sounds.values()) {
			music.stop();
		}
	}

	public ArrayList<String> getsoundsNames(){
		ArrayList<String> names = new ArrayList<String>();
		for (String name : sounds.keySet()) {
			names.add(name);
		}
		return names;
	}

	public ArrayList<Sound> getSounds(){
		ArrayList<Sound> sounds_ = new ArrayList<Sound>();
		for (Sound music : sounds.values()) {
			sounds_.add(music);
		}
		return sounds_;
	}

	@Override
	public void dispose() {
		for (Sound music : sounds.values()) {
			music.dispose();
		}
	}

}
