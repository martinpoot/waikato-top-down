package game;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundEffectManager {
	
	Sound turretHit;
	Sound turretExplosion;
	Sound playerExplosion;
	Sound playerShoots;
	private Sound playerCollision;
	
	
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
		turretHit = Resources.getSound(Resources.soundTurretHit);
		turretExplosion = Resources.getSound(Resources.soundTurretExplosion);
		playerExplosion = Resources.getSound(Resources.soundPlayerExplosion);
		playerShoots = Resources.getSound(Resources.soundPlayerShoot);
		playerCollision = Resources.getSound(Resources.playerCollision);
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

	public void playerCollision() {
		playerCollision.play();
		
	}

}
