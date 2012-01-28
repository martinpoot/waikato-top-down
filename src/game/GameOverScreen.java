package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import actor.IDrawable;
import actor.ImageHelper;

public class GameOverScreen implements IDrawable {

	private GameEngine engine;
	private ImageHelper imagehelper;

	public GameOverScreen(GameEngine gameEngine, GameContainer container,
			String gameoverimage) throws SlickException {
		this.engine = gameEngine;
		imagehelper = new ImageHelper(gameoverimage);
	}

	@Override
	public void render(Graphics g) {
		int score = engine.getScore();
		int level = engine.getRound() + 1;
		imagehelper.render(g);
	}

}
