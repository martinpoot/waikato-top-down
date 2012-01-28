package game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import actor.IDrawable;
import actor.movehelpers.IMoveHelper;

public class Level implements IDrawable {
	
	List<IDrawable> entities = new ArrayList<IDrawable>();
	private GameEngine engine;
	private GameContainer container;
	
	private Image background;
	private int height;
	private int width;
	
	private float start;
	private float end;
	private IMoveHelper myMoveHelper;
	
	public long randomSeed;
	
	public Level(GameEngine engine, GameContainer container, int bottomMargin, String backgroundPath,IMoveHelper myMoveHelper) throws SlickException {
		this.engine = engine;
		this.container = container;
		width = container.getWidth();
		height = container.getHeight() - bottomMargin;
		background = new Image(backgroundPath);
		start = background.getHeight()-height;
		end = background.getHeight();
		this.myMoveHelper = myMoveHelper;
		randomSeed = System.currentTimeMillis();
	}
	
	public void init(String background) {
		
	}
	
	@Override
	public void render(Graphics g) {
		background.draw(0, 0, width, height, 0, start, width, end);
		
	}
	
	public void updatePos(int delta) {
		float shift = myMoveHelper.getShift(delta);
		start = Math.max(0, start-shift);
		end = Math.max(height, end-shift);
		if(start == 0) {
			engine.levelStopped();
		}
	}

	public int getMinXBounds() {
		return 0;
	}
	public int getMaxXBounds() {
		return width;
	}
	public int getMinYBounds() {
		return 0;
	}
	public int getMaxYBounds() {
		return height;
	}
	
	public int getLevelHeight() {
		return background.getHeight();
	}

}
