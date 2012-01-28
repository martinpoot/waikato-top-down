package actor;

import game.GameEngine;
import game.Level;
import game.Resources;
import game.Speeds;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import actor.movehelpers.BulletMoveHelper;
import actor.movehelpers.IMoveHelper;

public class Turret implements IDrawable, IShooter, IDamageable, IMoveable {
	
	Image sprite;
	float topX;
	float topY;
	private GameEngine engine;
	private GameContainer container;
	private Level level;
	
	int deltaSinceLast;
	private IMoveHelper myMoveHelper;	
	
	public Turret(GameEngine engine, GameContainer container, String graphicsLocation, Level level, float x, float y, IMoveHelper moveHelper) throws SlickException {
		sprite = new Image(graphicsLocation);
		topY = y;
		topX = x;
		this.level = level;
		this.engine = engine;
		this.container = container;
		this.myMoveHelper = moveHelper;
	}

	@Override
	public void render(Graphics g) {
		sprite.draw(topX, topY);
	}

	@Override
	public void shoot(int delta) throws SlickException {
		changeDirection();
		deltaSinceLast += delta;
		if (deltaSinceLast >= 1000 / Speeds.turretFireSpeed && topY >= 0 && topY <= level.getMaxYBounds()-sprite.getWidth()) {
			
			deltaSinceLast = 0;
			Vector2f startPos = new Vector2f(topX+sprite.getWidth()/2,topY+sprite.getHeight()/2);
			Vector2f targetPos = engine.getPlayer().getPosition();
			
			Vector2f dir = targetPos.sub(startPos).normalise();
			
			engine.registerBullet(new Bullet(container, Resources.bullet1, level, startPos,dir,BulletMoveHelper.getInstance(), false, 5.0f));
		}
		
	}

	private void changeDirection() throws SlickException {
		float xdiff = (engine.getPlayer().topX + (engine.getPlayer().sprite.getWidth() / 2)) - (topX + sprite.getWidth());
		float ydiff = (engine.getPlayer().topY + (engine.getPlayer().sprite.getHeight() / 2)) - (topY + sprite.getHeight());
		if (Math.abs(xdiff) > Math.abs(ydiff)) { // closest on x 
			if (xdiff < 0) {
				sprite = new Image(Resources.turretShootingLeft);
			}
			else {

				sprite = new Image(Resources.turretShootingRight);
			}
		}
		else {	// closest on x 
			if (ydiff < 0) {
				sprite = new Image(Resources.turretShootingUp);
			}
			else {

				sprite = new Image(Resources.turretShootingDown);
			}
		}
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
		topY += myMoveHelper.getShift(delta);
	}

	@Override
	public void moveUp(int delta) {
		// TODO Auto-generated method stub
		
	}

	public float getX() {
		return topX;
	}
	
	public float getY() {
		return topY;
	}

}
