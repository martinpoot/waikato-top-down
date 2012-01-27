package actor;

import game.GameEngine;
import game.Level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Turret implements IDrawable, IShooter, IDamageable {
	
	Image sprite;
	float topX;
	float topY;
	

	
	public Turret(GameEngine engine, GameContainer container, String graphicsLocation, float x, float y) throws SlickException {
		sprite = new Image(graphicsLocation);
		topY = y;
		topX = x;
	}

	@Override
	public void render(Graphics g) {
		sprite.draw(topX, topY);
	}

	@Override
	public void shoot(GameEngine engine) {
		Vector2f targetPos = engine.getPlayer().getPosition();
		
	}

}
