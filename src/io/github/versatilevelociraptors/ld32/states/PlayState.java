package io.github.versatilevelociraptors.ld32.states;

import io.github.versatilevelociraptors.ld32.LudumDare32;
import io.github.versatilevelociraptors.ld32.audio.MusicManager;
import io.github.versatilevelociraptors.ld32.entities.Player;
import io.github.versatilevelociraptors.ld32.level.Level;
import io.github.versatilevelociraptors.ld32.level.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class PlayState extends State {

	private Level level;
	private Texture overlay;
	private Player player;
	private BitmapFont font;
	private MusicManager music;
	
	public PlayState(GameStateManager manager){
		super(manager);
		level = new Level("assets/levels/level02.txt");
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
        player = new Player(level);
        player.setCenter(LudumDare32.getWidth() / 2, LudumDare32.getHeight() / 2);
        level.setPlayer(player);
        
        overlay = new Texture("assets/images/overlay.png");
        font = new BitmapFont(true);
        
        music = new MusicManager();
        music.addAllMusicInAssets();
	}

	@Override
	protected void render() {
		cam.update();
        batch.setProjectionMatrix(cam.combined);
        
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClearColor(0.396f, 0.612f, 0.937f, 1.0f);
		batch.begin();
		level.render(batch);
		batch.draw(overlay, 0, 0);
		float weaponY = LudumDare32.getHeight() - player.getWeapon().getImage().getHeight();// y coordinate of weapon image
		batch.draw(player.getWeapon().getImage(), 0, weaponY);
		font.draw(batch," Weapon: " + player.getWeapon().getName(), 0, weaponY - 30);
		font.draw(batch, " Ammo: " + player.getWeapon().getAmmo(), 0, weaponY - 50);
		font.draw(batch, " Health: " + player.getHealth(), 0, weaponY + - 70);
		font.draw(batch, " Points: " + level.getKills(), 0, weaponY - 90);
		player.draw(batch);
		batch.end();
	}

	@Override
	protected void update(float dt){
		if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
			if(cam.zoom < 1)
            cam.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
        	if(cam.zoom > 0.2)
            cam.zoom -= 0.02;
        }
		level.update(dt);
		
		if(level.tileType((int)player.getX(), (int)player.getY()) == Tile.PLAY420_TILE){
			music.play("420");
		}
		
		if(level.tileType((int)player.getX(), (int)player.getY()) == Tile.STOP420_TILE){
			music.stop("420");
		}
		
		if(!level.getPlayer().isAlive()){
			music.stop("420");
			manager.set(new EndGameState(manager));
		}
	}

	@Override
	public void dispose() {
		player.dispose();
		level.dispose();
		font.dispose();
		overlay.dispose();
	}

}

