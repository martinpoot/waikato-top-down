package actor;

import game.GameEngine;
import game.Level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class PlayerGhost extends Player {

	public PlayerGhost(GameEngine engine, GameContainer container,
			String graphicsLocation, Level level) throws SlickException {
		super(engine, container, graphicsLocation, level);
		this.imagehelper.setAlpha(0.3f);
	}

	@Override
	public void takeDamage(float damageRating) {
		// do nothing.

	}
	
	

}
