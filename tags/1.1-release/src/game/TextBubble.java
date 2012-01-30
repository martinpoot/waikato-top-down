package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import actor.IDrawable;
import actor.ImageHelper;

public class TextBubble implements IDrawable {
	
	private ImageHelper imageHelper;
	
	float deathRate;

	public TextBubble(String graphicsLocation, float x, float y) throws SlickException {
		imageHelper = new ImageHelper(graphicsLocation);
		imageHelper.setTopX(x);
		imageHelper.setTopY(y);
		this.deathRate = Speeds.textBubbleDeathRate;
	}

	@Override
	public void render(Graphics g) {
		imageHelper.render(g);
	}

	public void reduceAlpha() {
		imageHelper.setAlpha(imageHelper.getAlpha() - deathRate);
	}
	
	public boolean canBeDiscarded() {
		return imageHelper.getAlpha() <= 0;
	}

}
