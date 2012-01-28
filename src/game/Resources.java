package game;

import java.net.URL;
import java.util.Hashtable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Resources {
	

	private static Hashtable<String,Image> sprites = new Hashtable<String,Image>();
	private static Hashtable<String,Sound> sounds = new Hashtable<String,Sound>();
	
	public final static String bullet1 = "res/bullet.png";

	public final static String playerBullet = "res/bullet-purple.png";
	public final static String ghostBullet = "res/bullet-yellow.png";
	public final static String turretBullet = "res/bullet-green.png";

	public final static String turretShootingDown = "res/turret-shooting-down.png";
	public final static String turretShootingUp = "res/turret-shooting-up.png";
	public final static String turretShootingRight = "res/turret-shooting-right.png";
	public final static String turretShootingLeft = "res/turret-shooting-left.png";
	public final static String background = "res/background.png";
	public final static String player = "res/player.png";
	public static final String backgroundMusic = "res/music/music.ogg";
	public static final String statusBackground = "res/status-background.png";
	public static final String statusSlider = "res/status-slider.png";
	public static final String splashImage = "res/splash-image.png";

	
	
	public static final String soundTurretHit = "res/music/recorded/popp.ogg";//"res/music/turret-hit.ogg";
	public static final String soundTurretExplosion = "res/music/recorded/bang.ogg";//"res/music/explosion-sm.ogg";
	
	public static final String soundPlayerExplosion = "res/music/recorded/nooo.ogg";//"res/music/player-dies.ogg";
	public static final String soundPlayerShoot = "res/music/recorded/pew.ogg"; //"res/music/player-shoot.ogg";
	
	public static final String playerCollision = "res/music/recorded/ouch.ogg";
	
	
	
	
	public static Image getSprite(String spriteResource) throws SlickException {
		if(!sprites.contains(spriteResource)) {
			sprites.put(spriteResource,new Image(spriteResource));
		}
		return sprites.get(spriteResource).copy();
	}
	
		
	public static Sound getSound(String soundResource) throws SlickException {
		if(!sounds.contains(soundResource)) {
			sounds.put(soundResource,new Sound(soundResource));
		}
		return sounds.get(soundResource);
	}
	
	
}
