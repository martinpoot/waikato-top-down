package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import actor.Bullet;
import actor.InputFeeder;
import actor.KeyboardInput;
import actor.Player;
import actor.Turret;
import actor.movehelpers.ScrollingMovehelper;

public class GameEngine extends BasicGame{

	Level level;
	Player player;
	InputFeeder playerInput;
	
	EntityManager entityManager;
	
	public GameEngine(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		level.render(g);
		player.render(g);
		for (Turret turret : entityManager.getTurrets()) {
			turret.render(g);
		}
		for(Bullet bullet : entityManager.getBullets()) {
			bullet.render(g);
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		level = new Level(this,container, Resources.background,ScrollingMovehelper.getInstance());
		player = new Player(this, container, Resources.player, level);
		playerInput = new KeyboardInput(player, container);
		
		entityManager = new EntityManager(this, container, level);
		
		entityManager.generateRandomTurrets(50);
		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		level.updatePos(delta);
		playerInput.poll(delta);
		
		entityManager.destroyOffscreen();

		for (Turret turret : entityManager.getTurrets()) {
			turret.shoot(delta);
		}
		
		for (Bullet bullet : entityManager.getBullets()) {
			bullet.advance(delta);
			detectCollisions(bullet);
		}
		
		for (InputFeeder feeder : entityManager.getInputFeeders()) {
			feeder.poll(delta);
		}
		
	}


	private void detectCollisions(Bullet bullet) {
		if (bullet.isPlayerFired()) {
//			for (Turret turret : entityManager.getTurrets()) {
//				
//			}
		} else {
			// we know it's a bullet that can potentially hurt the player
			Rectangle playerBB = player.getBoundingBox();
			if (playerBB.intersects(bullet.getBoundingBox())) {
				player.takeDamage(bullet.getDamageRating());
				System.out.println("boom!");
			}
		}
	}

	public static void main(String[] args) throws SlickException {
		         AppGameContainer app = 
					new AppGameContainer(new GameEngine("Memories that haunt us"),800,600,false);
		         app.setVSync(true);
		         app.start();
	}

	public Player getPlayer() {
		return player;
	}

	public void registerBullet(Bullet bullet) {
		entityManager.addBullet(bullet);
	}
}
