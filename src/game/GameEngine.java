package game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
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
	
	private int score = 0;
	private int round = 0;	// How many runs through is the player (0 == 0 ghosts)
	
	EntityManager entityManager;
	private boolean godMode;
	private boolean gameOver;
	private boolean levelFinished;
	private boolean towersGone;
	private Music music;
	private GameState state;
	

	private List<List<boolean[]>> playbackInputs = new ArrayList<List<boolean[]>>();
	private ArrayList<PlayerGhost> ghosts;
	private ArrayList<PlaybackInput> ghostInputs;
	private StatusBar statusBar;
	private int gameoverwaited;
	
	public GameEngine(String title) {
		super(title);
		state = GameState.TITLE;
		score = 0;
	}
	
	
	public void stateTransition(GameContainer container) throws SlickException {
		switch(state) {
		case GAME_OVER:
		case TITLE:
			restartGame(container);
			state = GameState.PLAYING;
			break;
		case PLAYING:
			state = GameState.GAME_OVER;
			break;
		}
	}

	private void restartGame(GameContainer container) throws SlickException {
		playbackInputs = new ArrayList<List<boolean[]>>();
		score = 0;
		round = 0;
		init(container);
	}


	@Override
	public boolean closeRequested() {
		if (music != null && music.playing()) {
			music.stop();
		}
		return true;
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		if(state == GameState.TITLE) {
			g.drawString("The Game",50,50);
			g.drawString("Press Space to Play",50,100);
			
		}
		else if (state == GameState.PLAYING) {
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
			
			statusBar.render(g);
		}
		else if(state == GameState.GAME_OVER) {
			g.drawString("Game over man, game over", 50, 50);
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		statusBar = new StatusBar(this, container, Resources.statusBackground, Resources.statusSlider);
		int statusHeight = statusBar.getHeight();
		
		int reservedBottomSpace = 0;
		if (playbackInputs != null && !playbackInputs.isEmpty()) {
			reservedBottomSpace = 91;
		}
		level = new Level(this,container, statusHeight, Resources.background,ScrollingMovehelper.getInstance());
		player = new Player(this, container, statusHeight, Resources.player, level, reservedBottomSpace);
		ghosts = new ArrayList<PlayerGhost>();
		ghostInputs = new ArrayList<PlaybackInput>();
		float strength = 0.9f;
		for(int i = playbackInputs.size() - 1; i >= 0; i--) {
			PlayerGhost ghost = new PlayerGhost(this,container, statusHeight, Resources.player,level, strength);
			ghosts.add(ghost);
			ghostInputs.add(new PlaybackInput(ghost,playbackInputs.get(i)));
			strength -= 0.1f;	// TODO maybe should set this in damages etc
		}
		playerInput = new KeyboardInput(player, container);
		
		entityManager = new EntityManager(this, container,level);
		
		entityManager.generateRandomTurrets(Damages.initialTurretCount + round * Damages.turretIncreaseRate);
		
		music = new Music("res/music/music.ogg");
		music.loop();
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		
		
		switch(state) {

		case GAME_OVER:
			gameoverwaited += delta;
			
			if(container.getInput().isKeyPressed(Input.KEY_SPACE) && gameoverwaited >= 2000) {
				gameoverwaited = 0;
				stateTransition(container);
			}
			break;
		case TITLE:
			if(container.getInput().isKeyPressed(Input.KEY_SPACE)) {
				stateTransition(container);
			}
			break;
		case PLAYING:
			updateGamePlan(container, delta);
			break;
			
		}
		
		
	}


	private void updateGamePlan(GameContainer container, int delta)
			throws SlickException {
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
		
		if(gameOver) {
			gameOver = false;
			gameoverwaited = 0;
			stateTransition(container);
		}
	}


	private void restartLevel(GameContainer container) throws SlickException {
		playbackInputs.add(playerInput.getInputsTriggered());
		while (playbackInputs.size() > 10) {
			playbackInputs.remove(0);
		}
		round++;
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
					SoundEffectManager.getInstance().turretHit();
					if (turret.getHealth() <= 0) {
						score +=turret.getScoreValue();
						entityManager.destroyTurret(turret);
						SoundEffectManager.getInstance().turretExplosion();
					}
					entityManager.destroyBullet(bullet);
				}
			}
			// Now check if this is hitting the player (e.g. ghost bullets)
			if (bullet.getBoundingBox().intersects(player.getBoundingBox())) {
				player.takeDamage(bullet.getDamageRating());
				entityManager.destroyBullet(bullet);
				SoundEffectManager.getInstance().playerHit();
				if (player.getHealth() <= 0) {
					System.out.println("player dies");
					SoundEffectManager.getInstance().playerExplosion();
					gameOver = true;
				}
			}
		} else {
			// we know it's a bullet that can potentially hurt the player
			Rectangle playerBB = player.getBoundingBox();
			if (playerBB.intersects(bulletBB)) {
				SoundEffectManager.getInstance().playerHit();
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
					new AppGameContainer(new GameEngine("Memories that haunt you"),800,600,false);
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

	public int getScore() {
		return score;
	}
}
