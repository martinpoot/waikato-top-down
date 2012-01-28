package game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;


import actor.Bullet;
import actor.InputFeeder;
import actor.KeyboardInput;
import actor.Player;
import actor.ScrollingInputFeeder;
import actor.Turret;
import actor.movehelpers.ScrollingMovehelper;

public class EntityManager {
	
	private List<InputFeeder> inputFeeders;
	private List<Turret> turrets;
	private List<Bullet> bullets;
	private GameContainer container;
	private Level level;
	private GameEngine engine;
	
	public EntityManager(GameEngine engine, GameContainer container, Level level) {
		this.container = container;
		this.level = level;
		this.engine = engine;
		init();
	}
	
	private void init() {
		turrets = new ArrayList<Turret>();
		bullets = new ArrayList<Bullet>();
		inputFeeders = new ArrayList<InputFeeder>();
	}

	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}
	
	public void createTurret(float x, float y) throws SlickException {
		Turret newTurret = new Turret(engine, container, Resources.turretShootingDown, level, x, y, 
				ScrollingMovehelper.getInstance());
		turrets.add(newTurret);
		inputFeeders.add(new ScrollingInputFeeder(newTurret, container));
	}
	
	public void generateRandomTurrets(int numTurrets) throws SlickException {
		for (int i = 0; i < numTurrets; i++) {
			float x = (float)(Math.random() * level.getMaxXBounds());
			float y = -(float)(Math.random() * (level.getLevelHeight() - level.getMaxYBounds()));
			
			createTurret(x, y);
		}
	}

	
	/**
	 * Destroy things that shouldn't exist anymore.
	 * Includes:
	 * Bullets anywhere outside of view frame
	 * Turrets below view frame
	 */
	public void destroyOffscreen() {
		
		List<Turret> turretsToRemove = new ArrayList<Turret>();
		for(Turret turret : turrets) {
			if (turret.getY() > level.getMaxYBounds()) {
				turretsToRemove.add(turret);
				System.out.println("Removing turret (below view)");
			}
		}
		turrets.removeAll(turretsToRemove);
		List<Bullet> bulletsToRemove = new ArrayList<Bullet>();
		for(Bullet bullet : bullets) {
			if (bullet.getX() < 0 || // left
					bullet.getX() > level.getMaxXBounds() ||	// right
					bullet.getY() > level.getMaxYBounds() ||	// below screen
					bullet.getY() < 0	// above screen
					) {
				bulletsToRemove.add(bullet);
				System.out.println("Removing bullet");
			}
		}
		bullets.remove(bulletsToRemove);
		bullets = bullets.subList(0, Math.min(bullets.size(), 100));
	}
	
	
	
	// Getters

	public List<Turret> getTurrets() {
		return turrets;
	}

	public List<InputFeeder> getInputFeeders() {
		return inputFeeders;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}
}