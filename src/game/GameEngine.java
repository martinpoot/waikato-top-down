package game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import actor.Bullet;
import actor.KeyboardInput;
import actor.Player;
import actor.Turret;

public class GameEngine extends BasicGame{

	Level level;
	Player player;
	KeyboardInput input;
	List<Turret> turrets;
	List<Bullet> bullets;
	
	public GameEngine(String title) {
		super(title);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		level.render(g);
		player.render(g);
		for (Turret turret : turrets) {
			turret.render(g);
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		level = new Level(this,container,"res/background.jpg");
		player = new Player(this, container, "res/player.png", level);
		input = new KeyboardInput(player, container);
		
		turrets = new ArrayList<Turret>();
		turrets.add(new Turret(this, container, "res/turret.png", level, 10, 10));
		
		bullets = new ArrayList<Bullet>();
		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		level.updatePos(delta);
		input.poll(delta);
		

		for (Turret turret : turrets) {
			turret.shoot(delta);
		}
		
		for (Bullet bullet : bullets) {
			//
		}
		
		
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

	public void registerBullet(Bullet bullet) {
		bullets.add(bullet);
	}
}
