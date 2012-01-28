package actor;

import game.Resources;

import java.awt.image.RescaleOp;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

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
	private Rectangle boundingBox;

	public ImageHelper(String graphicsLocation) throws SlickException {
		// TODO Auto-generated constructor stub
		sprite = Resources.getSprite(graphicsLocation);
		boundingBox = new Rectangle(0, 0, sprite.getWidth(), sprite.getHeight());
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
		boundingBox.setX(topX);
	}

	public float getTopY() {
		return topY;
	}

	public void setTopY(float topY) {
		this.topY = topY;
		boundingBox.setY(topY);
	}

	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	public void setAlpha(float alpha) {

		sprite.setAlpha(alpha);
		
	}

	public Vector2f getPosition(Player player) {
		return new Vector2f(player.getBoundingBox().getCenter());
	}

	public void setGraphicsLocation(String location) throws SlickException {
		sprite = new Image(location);
	}

	public void rotate(float angle) {
		sprite.rotate(angle);
		
	}

	

}
