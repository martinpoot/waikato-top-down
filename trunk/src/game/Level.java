package game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import actor.IDrawable;

public class Level implements IDrawable {
	
	List<IDrawable> entities = new ArrayList<IDrawable>();
	private GameEngine engine;
	private GameContainer container;
	
	private Image background;
	private int height;
	private int width;
	
	private float start;
	private float end;
	
	public Level(GameEngine engine, GameContainer container,String backgroundPath) throws SlickException {
		this.engine = engine;
		this.container = container;
		width = container.getWidth();
		height = container.getHeight();
		background = new Image(backgroundPath);
		start = background.getHeight()-height;
		end = background.getHeight();
	}
	
	public void init(String background) {
		
	}
	
	public void render(Graphics g) {
		background.draw(0, 0, width, height, 0, start, width, end);
		
	}
	
	public void updatePos(int delta) {
		start = Math.max(0, start-Speeds.background*delta/1000);
		end = Math.max(height, end-Speeds.background*delta/1000);
	}
	

}
