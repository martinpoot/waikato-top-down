package game;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundEffectManager {
	
	Sound turretHit;
	Sound turretExplosion;
	Sound playerExplosion;
	Sound playerShoots;
	
	private static SoundEffectManager instance;
	
	public static SoundEffectManager getInstance() {
		if (instance == null)
			try {
				instance = new SoundEffectManager();
			} catch (SlickException e) {
				// Should actually handle this exception, maybe later!
				e.printStackTrace();
			}
		return instance;
	}
	
	private SoundEffectManager() throws SlickException {
		init();
	}
	
	private void init() throws SlickException {
		turretHit = new Sound("res/music/turret-hit.ogg");
		turretExplosion = new Sound("res/music/explosion-sm.ogg");
		playerExplosion = new Sound("res/music/player-dies.ogg");
		playerShoots = new Sound("res/music/player-shoot.ogg");
	}
	
	public void turretHit() {
		turretHit.play();
	}
	
	public void playerHit() {
	}
	
	public void turretExplosion() {
		turretExplosion.play();
	}
	
	public void playerExplosion() {
		playerExplosion.play();
	}
	
	public void playerShoots() {
		playerShoots.play();
	}

}
