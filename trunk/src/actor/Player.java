package actor;


import game.GameEngine;
import game.Level;
import game.Speeds;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Player implements IDrawable,IMoveable {
	
	private Level level;
	private ImageHelper imagehelper;
	
	public Player(GameEngine engine, GameContainer container, String graphicsLocation,Level level) throws SlickException {
		imagehelper = new ImageHelper(graphicsLocation);
		imagehelper.setTopY((float) (level.getMaxYBounds()-imagehelper.getHeight()));
		imagehelper.setTopX((float) ((level.getMaxXBounds()-imagehelper.getWidth())/2));
		
		this.level = level;
	}

	@Override
	public void render(Graphics g) {
		imagehelper.render(g);
	}

	@Override
	public void moveLeft(int delta) {
		float shift = Speeds.playerspeed*delta/1000;
		imagehelper.setTopX(Math.max(level.getMinXBounds(), imagehelper.getTopX()-shift));
		
	}

	@Override
	public void moveRight(int delta) {
		float shift = Speeds.playerspeed*delta/1000;
		imagehelper.setTopX(Math.min(level.getMaxXBounds()-imagehelper.getWidth(), imagehelper.getTopX()+shift));

		
	}

	@Override
	public void moveDown(int delta) {
		float shift = Speeds.playerspeed*delta/1000;
		imagehelper.setTopY(Math.min(level.getMaxYBounds()-imagehelper.getHeight(), imagehelper.getTopY()+shift));
	}

	@Override
	public void moveUp(int delta) {
		float shift = Speeds.playerspeed*delta/1000;
		imagehelper.setTopY(Math.max(level.getMinYBounds(),imagehelper.getTopY()-shift));
	}

	public Vector2f getPosition() {
		return new Vector2f(getBoundingBox().getCenter());
	}

	public Rectangle getBoundingBox() {
		return imagehelper.getBoundingBox();
	}

}
