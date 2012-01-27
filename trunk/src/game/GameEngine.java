package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import actor.KeyboardInput;
import actor.Player;

public class GameEngine extends BasicGame{

	Level level;
	Player player;
	KeyboardInput input;
	
	public GameEngine(String title) {
		super(title);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		level.render(g);
		player.render(g);
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		level = new Level(this,container,"res/background.jpg");
		player = new Player(this, container, "res/player.png", level);
		input = new KeyboardInput(player, container);
		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		level.updatePos(delta);
		input.poll(delta);
		
	}


	public static void main(String[] args) throws SlickException {
		         AppGameContainer app = 
					new AppGameContainer(new GameEngine("Top down ghost zombie shooter"),800,600,false);
		         app.setVSync(true);
		         app.start();
	}

	public Player getPlayer() {
		return player;
	}
}
