package actor;


import game.GameEngine;
import game.Level;
import game.Speeds;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Player implements IDrawable,IMoveable {
	
	Image sprite;
	float topX;
	float topY;
	private Level level;
	
	public Player(GameEngine engine, GameContainer container, String graphicsLocation,Level level) throws SlickException {
		sprite = new Image(graphicsLocation);
		this.level = level;
		topY = level.getMaxYBounds()-sprite.getHeight();
		topX = (level.getMaxXBounds()-sprite.getWidth())/2;
	}

	@Override
	public void render(Graphics g) {
		sprite.draw(topX,topY);
	}

	@Override
	public void moveLeft(int delta) {
		float shift = Speeds.playerspeed*delta/1000;
		topX = Math.max(level.getMinXBounds(), topX-shift);
		
	}

	@Override
	public void moveRight(int delta) {
		float shift = Speeds.playerspeed*delta/1000;
		topX = Math.min(level.getMaxXBounds()-sprite.getWidth(), topX+shift);

		
	}

	@Override
	public void moveDown(int delta) {
		float shift = Speeds.playerspeed*delta/1000;
		topY = Math.min(level.getMaxYBounds()-sprite.getHeight(), topY+shift);
		
	}

	@Override
	public void moveUp(int delta) {
		float shift = Speeds.playerspeed*delta/1000;
		topY = Math.max(level.getMinYBounds(),topY-shift);
		
	}

	public Vector2f getPosition() {
		return new Vector2f(topX, topY);
	}

}
