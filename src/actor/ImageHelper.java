package actor;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageHelper {

	private Image sprite;
	public int getWidth() {
		return sprite.getWidth();
	}

	public int getHeight() {
		return sprite.getHeight();
	}

	private float topX;
	private float topY;

	public ImageHelper(String graphicsLocation) throws SlickException {
		// TODO Auto-generated constructor stub
		sprite = new Image(graphicsLocation);
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		sprite.draw(topX, topY);
	}

	public float getTopX() {
		return topX;
	}

	public void setTopX(float topX) {
		this.topX = topX;
	}

	public float getTopY() {
		return topY;
	}

	public void setTopY(float topY) {
		this.topY = topY;
	}
	
	

}
