package io.github.versatilevelociraptors.ld32.audio;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Music;

public class MusicManager extends AudioManager{

	public MusicManager(){
		musics = new HashMap<String , Music>();
	}

	public void addAllMusicInAssets(){
		File file = new File(ASSETS_MUSIC_PATH);
		File[] mp3s = file.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String mp3) {
				return mp3.endsWith("mp3");
			}
		});

		for(File f : mp3s){
			addMusic(f.getName().substring(0, f.getName().lastIndexOf('.')) , f.getPath());
		}

	}

	public void addMusic(String name, Music music){
		musics.put(name, music);
	}

	public void addMusic(String name, String path){
		musics.put(name , (Music) Gdx.audio.newMusic(Gdx.files.internal(path)));
	}

	public void play(String name){
		musics.get(name).play();
		musics.get(name).setVolume(.3f);
	}

	public void loop(String name){
		musics.get(name).play();
		musics.get(name).setVolume(.5f);
		musics.get(name).setLooping(true);
	}

	public void stop(String name){
		if(musics.get(name).isPlaying()){
			musics.get(name).stop();
		}	
	}

	public void stop(){
		for (Music music : musics.values()) {
			music.stop();
		}
	}

	public ArrayList<String> getmusicsNames(){
		ArrayList<String> names = new ArrayList<String>();
		for (String name : musics.keySet()) {
			names.add(name);
		}
		return names;
	}

	public ArrayList<Music> getMusics(){
		ArrayList<Music> musics_ = new ArrayList<Music>();
		for (Music music : musics.values()) {
			musics_.add(music);
		}
		return musics_;
	}
	
	@Override
	public void dispose() {
		for (Music music : musics.values()) {
			music.dispose();
		}
	}

}

