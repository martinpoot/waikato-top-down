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
	private boolean firedByPlayer;
	private float damageRating;
	
	
	public Bullet(GameContainer container, String graphicsLocation,Level level, 
			Vector2f startPos, Vector2f direction,IMoveHelper moveHelper, boolean firedByPlayer, float damageRating) throws SlickException{
		this.direction = direction;
		imagehelper = new ImageHelper(graphicsLocation);
		
		imagehelper.setTopX(startPos.getX());
		imagehelper.setTopY(startPos.getY());
		this.level = level;
		this.moveHelper = moveHelper;
		
		this.firedByPlayer = firedByPlayer;
		this.damageRating = damageRating;
		
	}
	
	public void advance(int delta) {
		imagehelper.setTopX(imagehelper.getTopX()+moveHelper.getShift(delta)*direction.getX());
		imagehelper.setTopY(imagehelper.getTopY()+moveHelper.getShift(delta)*direction.getY());
		
	}

	@Override
	public void render(Graphics g) {
		
		imagehelper.render(g);
	}
	
	public float getX() {
		return imagehelper.getTopX() + (imagehelper.getWidth() / 2);
	}
	
	public float getY() {
		return imagehelper.getTopY() + (imagehelper.getHeight() / 2);
	}

}
