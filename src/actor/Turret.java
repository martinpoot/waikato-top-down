package actor;

import game.GameEngine;
import game.Level;
import game.Speeds;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Turret implements IDrawable, IShooter, IDamageable {
	
	Image sprite;
	float topX;
	float topY;
	private GameEngine engine;
	private GameContainer container;
	private Level level;
	
	
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
	public void shoot(int delta) {
		//engine.registerBullet(new Bullet(container, "", level, engine.getPlayer().getPosition()));
	}

}
