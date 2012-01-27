package actor;

import game.Level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Bullet implements IDrawable, IMoveable{

	Level level;
	private ImageHelper imagehelper;
	private Vector2f direction;
	
	
	public Bullet(GameContainer container, String graphicsLocation,Level level, Vector2f direction) throws SlickException{
		float topY, topX;
		this.direction = direction;

		imagehelper = new ImageHelper(graphicsLocation);
		topY = level.getMaxYBounds()-imagehelper.getHeight();
		topX = (level.getMaxXBounds()-imagehelper.getWidth())/2;
		imagehelper.setTopX(topX);
		imagehelper.setTopY(topY);
		this.level = level;
		
	}
	
	@Override
	public void moveLeft(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveRight(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveDown(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveUp(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		imagehelper.render(g);
	}

}
