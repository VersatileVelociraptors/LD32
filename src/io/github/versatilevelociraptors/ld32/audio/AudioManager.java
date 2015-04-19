package io.github.versatilevelociraptors.ld32.audio;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Music;
import com.badlogic.gdx.utils.Disposable;

public class AudioManager implements Disposable {

	private ArrayList<Music> musics;
	private static final String ASSETS_MUSIC_PATH = "assets/music";

	public AudioManager(){
		musics = new ArrayList<Music>();
	}
	
	public void addAllMusicInAssets(){
		File file = new File(ASSETS_MUSIC_PATH);
		File[] mp3s = file.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String mp3) {
		       return mp3.endsWith("mp3");
		    }
		});
		
		for(File f : mp3s){
			addMusic(f.getPath());
		}
		
	}

	public void addMusic(Music music){
		musics.add(music);
	}

	public void addMusic(String path){
		musics.add((Music) Gdx.audio.newMusic(Gdx.files.internal(path)));
	}

	public void play(int i){
		if(i<musics.size())
			musics.get(i).play();
	}

	public void loop(int i){
		if(i<musics.size())
			musics.get(i).setLooping(true);
	}

	public void stop(int i){
		if(i<musics.size() && musics.get(i).isPlaying()){
			musics.get(i).stop();
		}	
	}

	public void stop(){
		for(Music music : musics){
			if(music.isPlaying())
				music.stop();
		}
	}

	public ArrayList<Music> getmusics(){
		return musics;
	}

	@Override
	public void dispose() {
		for(Music music : musics){
			music.dispose();
		}
	}

}
