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

	public PlayerGhost(GameEngine engine, GameContainer container,
			int bottomMargin, String graphicsLocation, Level level) throws SlickException {
		super(engine, container, bottomMargin, graphicsLocation, level);
		this.imagehelper.setAlpha(0.3f);
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
				engine.registerBullet(new Bullet(container, Resources.bullet1, level, startPos,dir,PlayerBulletMoveHelper.getInstance(), 
						true, Damages.ghostDamage));
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			shotLast = 0;
		}
	}

}
