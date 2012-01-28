package actor;

import game.Level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import actor.movehelpers.IMoveHelper;

public class Bullet implements IDrawable {

	Level level;
	private ImageHelper imagehelper;
	private Vector2f direction;
	private IMoveHelper moveHelper;
	float topY, topX;
	
	public Bullet(GameContainer container, String graphicsLocation,Level level, Vector2f direction,IMoveHelper moveHelper) throws SlickException{
		
		this.direction = direction;

		imagehelper = new ImageHelper(graphicsLocation);
		topY = level.getMaxYBounds()-imagehelper.getHeight();
		topX = (level.getMaxXBounds()-imagehelper.getWidth())/2;
		imagehelper.setTopX(topX);
		imagehelper.setTopY(topY);
		this.level = level;
		this.moveHelper = moveHelper;
		
	}
	
	public void advance(int delta) {
		topX += moveHelper.getShift(delta)*direction.getX();
		topY += moveHelper.getShift(delta)*direction.getY();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		imagehelper.render(g);
	}

}
