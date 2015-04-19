package io.github.versatilevelociraptors.ld32.states;

import io.github.versatilevelociraptors.ld32.LudumDare32;
import io.github.versatilevelociraptors.ld32.entities.Player;
import io.github.versatilevelociraptors.ld32.level.Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class PlayState extends State {

	private Level level;
	private Texture overlay;
	private Player player;

	public PlayState(GameStateManager manager){
		super(manager);
		level = new Level("assets/levels/level02.txt");
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
        player = new Player(level);
        player.setCenter(LudumDare32.getWidth() / 2, LudumDare32.getHeight() / 2);
        level.setPlayer(player);
        
        overlay = new Texture("assets/images/overlay.png");
      
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
	}

	@Override
	public void dispose() {
		level.dispose();
	}

}

