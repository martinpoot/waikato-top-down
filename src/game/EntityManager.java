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
	
	public void generateRandomTurrets() throws SlickException {
		for (int i = 0; i < 50; i++) {
			createTurret(
					(float)(Math.random() * level.getMaxXBounds()), 
					-(float)(Math.random() * (level.getLevelHeight() - level.getMaxYBounds())));
		}
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
