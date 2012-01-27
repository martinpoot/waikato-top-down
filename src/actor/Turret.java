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

import actor.movehelpers.IMoveHelper;
import actor.movehelpers.ScrollingMovehelper;

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
		deltaSinceLast += delta;
		if (deltaSinceLast >= 1000 / Speeds.turretFireSpeed) {
			System.out.println("pew pew!");
			deltaSinceLast = 0;
			engine.registerBullet(new Bullet(container, Resources.bullet1, level, engine.getPlayer().getPosition()));
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

}
