package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileFilter;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import actor.Bullet;
import actor.InputFeeder;
import actor.ScrollingInputFeeder;
import actor.Turret;
import actor.movehelpers.ScrollingMovehelper;

public class EntityManager {
	
	private List<InputFeeder> inputFeeders;
	private List<Turret> turrets;
	private List<Bullet> bullets;
	private List<TextBubble> textBubbles;
	
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
		textBubbles = new ArrayList<TextBubble>();
	}

	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}
	
	public void loadSavedTurrets() {
		File levelDir = Resources.getFile(Resources.levelDirectory);
		File[] files = levelDir.listFiles(new FilenameFilter () {
			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith(".csv")) return true;
				return false;
			}
		});
		
		int numLevelsRequired = level.getEnemySpawnHeight() / level.getMaxYBounds();
		while (numLevelsRequired-- > 0) {
			int nextLevel = (int)(Math.random() * 10) % files.length;
			File file = files[nextLevel];
			
			try {
				FileReader istream = new FileReader(file);
				BufferedReader in = new BufferedReader(istream);
				String line = in.readLine();
				while (line != null) {
					String[] bits = line.split(",");
					int x = Integer.parseInt(bits[0]);
					int y = Integer.parseInt(bits[1]) - (level.getMaxYBounds() * (numLevelsRequired + 1));
					Turret newTurret = new Turret(engine, container, Resources.turret, level, x, y, 
							ScrollingMovehelper.getInstance());
					inputFeeders.add(new ScrollingInputFeeder(newTurret, container));
					turrets.add(newTurret);
					line = in.readLine();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			}
		}
		turrets.removeAll(turretsToRemove);
		
		engine.towersGone(turrets.size() == 0);
		
		List<Bullet> bulletsToRemove = new ArrayList<Bullet>();
		
		for(Bullet bullet : bullets) {
			if (bullet.getX() < 0 || // left
					bullet.getX() > level.getMaxXBounds() ||	// right
					bullet.getY() > level.getMaxYBounds() ||	// below screen
					bullet.getY() < 0	// above screen
					) {
				bulletsToRemove.add(bullet);
			}
		}
		
		bullets.removeAll(bulletsToRemove);

	}
	
	public void addTextBubble(TextBubble bubble) {
		textBubbles.add(bubble);
	}

	public void destroyBubble(TextBubble bubble) {
		textBubbles.remove(bubble);
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

	public void destroyBullet(Bullet bullet) {
		bullets.remove(bullet);
	}

	public void destroyTurret(Turret turret) {
		turrets.remove(turret);
	}

	public List<TextBubble> getTextBubbles() {
		return textBubbles;
	}

}
