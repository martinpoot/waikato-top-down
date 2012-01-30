package actor;

import game.Damages;
import game.GameEngine;
import game.Level;
import game.Resources;
import game.Scores;
import game.Speeds;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import actor.movehelpers.BulletMoveHelper;
import actor.movehelpers.IMoveHelper;

public class Turret implements IDrawable, IShooter, IDamageable, IMoveable {
	
	private GameEngine engine;
	private GameContainer container;
	private Level level;
	
	int deltaSinceLast;
	private IMoveHelper myMoveHelper;
	private int health;
	private ImageHelper imagehelper;	
	
	public Turret(GameEngine engine, GameContainer container, String graphicsLocation, Level level, float x, float y, IMoveHelper moveHelper) throws SlickException {
		imagehelper = new ImageHelper(graphicsLocation);
		imagehelper.setTopX(x);
		imagehelper.setTopY(y);
		
		this.level = level;
		this.engine = engine;
		this.container = container;
		this.myMoveHelper = moveHelper;
		
		health = Damages.turretHealth;
	}

	@Override
	public void render(Graphics g) {
		imagehelper.render(g);
	}

	@Override
	public void shoot(int delta) throws SlickException {
		rotateTurret();
		deltaSinceLast += delta;
		if (deltaSinceLast >= 1000 / Speeds.turretFireSpeed && getY() >= 0 && getY() <= level.getMaxYBounds()-imagehelper.getWidth()) {
			
			deltaSinceLast = 0;
			Vector2f startPos = new Vector2f(imagehelper.getBoundingBox().getCenter());
			Vector2f targetPos = engine.getPlayer().getPosition();
			
			Vector2f dir = targetPos.sub(startPos).normalise();
			
			engine.registerBullet(new Bullet(container, Resources.turretBullet, level, startPos,dir,BulletMoveHelper.getInstance(), false, Damages.turretDamage));
		}
		
	}

	private void rotateTurret() throws SlickException {
		Vector2f playerBB = engine.getPlayer().getPosition();
		
		Vector2f centrePos = new Vector2f(getX(),getY());
		
		//float xdiff = playerBB.getX() - (getX() + imagehelper.getWidth());
		//float ydiff = playerBB.getY() - (getY() + imagehelper.getHeight());
		
		float angle = (float) playerBB.sub(centrePos).getTheta();
		
		imagehelper.setGraphicsLocation(Resources.turret);
		imagehelper.rotate(angle);
		
		/*if (Math.abs(xdiff) > Math.abs(ydiff)) { // closest on x 
			if (xdiff < 0) {
				imagehelper.setGraphicsLocation(Resources.turretShootingLeft);
			}
			else {
				imagehelper.setGraphicsLocation(Resources.turretShootingRight);
			}
		}
		else {	// closest on x 
			if (ydiff < 0) {
				imagehelper.setGraphicsLocation(Resources.turretShootingUp);
			}
			else {
				imagehelper.setGraphicsLocation(Resources.turretShootingDown);
			}
		}*/
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
		imagehelper.setTopY(imagehelper.getTopY() + myMoveHelper.getShift(delta));
	}

	@Override
	public void moveUp(int delta) {
		// TODO Auto-generated method stub
		
	}

	public float getX() {
		return imagehelper.getTopX();
	}
	
	public float getY() {
		return imagehelper.getTopY();
	}

	@Override
	public void takeDamage(float damageRating) {
		health -= damageRating;
	}

	@Override
	public int getHealth() {
		return health;
	}

	public Rectangle getBoundingBox() {
		return imagehelper.getBoundingBox();
	}

	public void setX(float x) {
		imagehelper.setTopX(x);
	}

	public int getScoreValue() {
		return Scores.turret;
	}

}
