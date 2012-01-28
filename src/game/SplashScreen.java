package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import actor.IDrawable;
import actor.ImageHelper;

public class SplashScreen implements IDrawable {

	private ImageHelper imagehelper;

	public SplashScreen(GameEngine gameEngine, GameContainer container,
			String splashimage) throws SlickException {
		imagehelper = new ImageHelper(splashimage);
	}

	@Override
	public void render(Graphics g) {
		imagehelper.render(g);
	}

}
