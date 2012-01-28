package actor;

import game.Damages;
import game.GameEngine;
import game.Level;
import game.Resources;
import game.Speeds;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import actor.movehelpers.PlayerBulletMoveHelper;

public class PlayerGhost extends Player {
	
	private float strength;

	public PlayerGhost(GameEngine engine, GameContainer container,
			int bottomMargin, String graphicsLocation, Level level, float strength) throws SlickException {
		super(engine, container, bottomMargin, graphicsLocation, level, 0);
		this.strength = strength;
		this.imagehelper.setAlpha(strength);
	}

	@Override
	public void takeDamage(float damageRating) {
		// do nothing.

	}
	
	@Override
	public void shoot(int delta) {
		if (shotLast >= 1000 / Speeds.playerFireSpeed){
			Vector2f dir = new Vector2f(0, -1);
			Vector2f startPos = new Vector2f(imagehelper.getTopX()+imagehelper.getWidth()/2, imagehelper.getTopY() - 1);
			try {
				engine.registerBullet(new Bullet(container, Resources.ghostBullet, level, startPos,dir,PlayerBulletMoveHelper.getInstance(), 
						true, Damages.ghostDamage * strength));
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			shotLast = 0;
		}
	}

}
