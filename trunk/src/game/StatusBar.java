package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import actor.IDrawable;
import actor.ImageHelper;

public class StatusBar implements IDrawable {

	private GameEngine engine;
	private ImageHelper bgHelper;
	private int y;
	private GameContainer container;

	public StatusBar(GameEngine gameEngine, GameContainer container, String background) throws SlickException {
		engine = gameEngine;
		bgHelper = new ImageHelper(background);
		y = container.getHeight() - getHeight();
		bgHelper.setTopY(y);
		this.container = container;
	}

	@Override
	public void render(Graphics g) {		
		bgHelper.render(g);
		Color originalColor = g.getColor();
		g.setColor(Color.black);
		
		String score = "Score: " + engine.getScore();
		g.drawString(score, 10, y);
		

		int level = engine.getRound() + 1;
		
		String health = engine.getPlayer().getHealth() + " Health";
		g.drawString(health, container.getWidth() - 10 - g.getFont().getWidth(health), y);
		
		g.setColor(originalColor);
	}

	public int getHeight() {
		return bgHelper.getHeight();
	}

}
