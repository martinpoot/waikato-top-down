package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import actor.IDrawable;
import actor.ImageHelper;

public class StatusBar implements IDrawable {

	private GameEngine engine;
	private ImageHelper bgHelper;
	private ImageHelper sliderHelper;
	private int y;
	private GameContainer container;

	public StatusBar(GameEngine gameEngine, GameContainer container, String background, String slider) throws SlickException {
		engine = gameEngine;
		//bgHelper = new ImageHelper(background);
		//sliderHelper = new ImageHelper(slider);
		y = container.getHeight() - getHeight();
		this.container = container;
	}

	@Override
	public void render(Graphics g) {		
		//bgHelper.render(g);
		//sliderHelper.render(g);
		String score = "" + engine.getScore();
		g.drawString(score, 10, y);
		
		String health = "" + engine.getPlayer().getHealth();
		g.drawString(health, container.getWidth() - 10 - g.getFont().getWidth(health), y);
	}

	public int getHeight() {
		//return bgHelper.getHeight();
		return 20;
	}

}
