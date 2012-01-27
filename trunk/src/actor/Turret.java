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

public class Turret implements IDrawable, IShooter, IDamageable, IMoveable {
	
	Image sprite;
	float topX;
	float topY;
	private GameEngine engine;
	private GameContainer container;
	private Level level;
	
	int deltaSinceLast;	
	
	public Turret(GameEngine engine, GameContainer container, String graphicsLocation, Level level, float x, float y) throws SlickException {
		sprite = new Image(graphicsLocation);
		topY = y;
		topX = x;
		this.level = level;
		this.engine = engine;
		this.container = container;
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
		topY += Speeds.background*delta/1000;
	}

	@Override
	public void moveUp(int delta) {
		// TODO Auto-generated method stub
		
	}

}
