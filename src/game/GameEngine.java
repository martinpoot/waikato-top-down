package game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import actor.Bullet;
import actor.InputFeeder;
import actor.KeyboardInput;
import actor.PlaybackInput;
import actor.Player;
import actor.PlayerGhost;
import actor.Turret;
import actor.movehelpers.ScrollingMovehelper;

public class GameEngine extends BasicGame{

	Level level;
	Player player;
	KeyboardInput playerInput;
	
	EntityManager entityManager;
	private boolean godMode;
	private boolean gameOver;
	private boolean levelFinished;
	private boolean towersGone;
	
	private List<List<boolean[]>> playbackInputs = new ArrayList<List<boolean[]>>();
	private ArrayList<PlayerGhost> ghosts;
	private ArrayList<PlaybackInput> ghostInputs;
	
	public GameEngine(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		if (!gameOver) {
			level.render(g);
			
			for(PlayerGhost ghost : ghosts) {
				ghost.render(g);
			}
			
			for (Turret turret : entityManager.getTurrets()) {
				turret.render(g);
			}
			for(Bullet bullet : entityManager.getBullets()) {
				bullet.render(g);
			}
			player.render(g);
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		level = new Level(this,container, Resources.background,ScrollingMovehelper.getInstance());
		player = new Player(this, container, Resources.player, level);
		ghosts = new ArrayList<PlayerGhost>();
		ghostInputs = new ArrayList<PlaybackInput>();
		for(List<boolean[]> inputRecording : playbackInputs) {
			PlayerGhost ghost = new PlayerGhost(this,container,Resources.player,level);
			ghosts.add(ghost);
			ghostInputs.add(new PlaybackInput(ghost,inputRecording));
		}
		playerInput = new KeyboardInput(player, container);
		
		entityManager = new EntityManager(this, container, level);
		
		entityManager.generateRandomTurrets(50);
		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if (gameOver) {
			return;
		}
		level.updatePos(delta);
		playerInput.poll(delta);
		
		for(PlaybackInput pbInput: ghostInputs) {
			pbInput.poll(delta);
		}
		
		entityManager.destroyOffscreen();

		for (Turret turret : entityManager.getTurrets()) {
			turret.shoot(delta);
		}
		
		List<Bullet> bullets = new ArrayList<Bullet>();
		bullets.addAll(entityManager.getBullets());
		for (Bullet bullet : bullets) {
			bullet.advance(delta);
			detectCollisions(bullet);
		}
		
		for (InputFeeder feeder : entityManager.getInputFeeders()) {
			feeder.poll(delta);
		}
		
		
		if(towersGone && levelFinished) {
			restartLevel(container);
		}
	}


	private void restartLevel(GameContainer container) throws SlickException {
		playbackInputs.add(playerInput.getInputsTriggered());
		
		init(container);
		
	}

	private void detectCollisions(Bullet bullet) {
		Rectangle bulletBB = bullet.getBoundingBox();
		if (bullet.isPlayerFired()) {
			List<Turret> turrets = new ArrayList<Turret>();
			turrets.addAll(entityManager.getTurrets());
			for (Turret turret : turrets) {
				// TODO do this for ghosts and enemy ships etc too
				Rectangle turretBB = turret.getBoundingBox();
				if (turretBB.intersects(bulletBB)) {
					turret.takeDamage(bullet.getDamageRating());
					if (turret.getHealth() <= 0) {
						entityManager.destroyTurret(turret);
					}
					entityManager.destroyBullet(bullet);
				}
			}
			// Now check if this is hitting the player (e.g. ghost bullets)
			if (bullet.getBoundingBox().intersects(player.getBoundingBox())) {
				player.takeDamage(bullet.getDamageRating());
				entityManager.destroyBullet(bullet);
				if (player.getHealth() <= 0) {
					System.out.println("player dies");
					gameOver = true;
				}
			}
		} else {
			// we know it's a bullet that can potentially hurt the player
			Rectangle playerBB = player.getBoundingBox();
			if (playerBB.intersects(bulletBB)) {
				if (!isGodMode()) {
					player.takeDamage(bullet.getDamageRating());
					if (player.getHealth() <= 0) {
						System.out.println("player dies");
						gameOver = true;
					}
				}
				entityManager.destroyBullet(bullet);
			}
		}
	}

	private boolean isGodMode() {
		return godMode;
	}

	public void setGodMode(boolean godMode) {
		this.godMode = godMode;
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

	public void levelStopped() {
		levelFinished = true;
	}
	
	public void towersGone(boolean status) {
		towersGone = status;
	}
}
