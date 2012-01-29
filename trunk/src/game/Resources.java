package game;

import java.io.File;
import java.util.Hashtable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Resources {
	

	private static Hashtable<String,Image> sprites = new Hashtable<String,Image>();
	private static Hashtable<String,Sound> sounds = new Hashtable<String,Sound>();
	private static Hashtable<String, File> files = new Hashtable<String, File>();
	
	public final static String bullet1 = "res/bullet.png";

	public final static String playerBullet = "res/bullet-purple.png";
	public final static String ghostBullet = "res/bullet-yellow.png";
	public final static String turretBullet = "res/bullet-green.png";
	
	// Speech bubble effects
	public final static String effectBoomGreenSmall = "res/boom-green-small.png";
	public final static String effectBoomPurpleSmall = "res/boom-purple-small.png";
	public final static String effectKaboomYellowSmall = "res/kaboom-yellow-small.png";
	

	public final static String turret = "res/turret.png";
	public final static String background = "res/background.png";
	public final static String player = "res/player.png";
	public final static String ghost = "res/ghost.png";
	public static final String backgroundMusic = "res/music/music.ogg";
	public static final String statusBackground = "res/status-background.png";
	public static final String splashImage = "res/splash-image.png";
	public static final String gameOverImage = "res/game-over-image.png";
	
	
	public static final String soundTurretHit = "res/music/recorded/popp.ogg";//"res/music/turret-hit.ogg";
	public static final String soundTurretExplosion = "res/music/recorded/boom.ogg";//"res/music/explosion-sm.ogg";
	
	public static final String soundPlayerExplosion = "res/music/recorded/nooo.ogg";//"res/music/player-dies.ogg";
	public static final String soundPlayerShoot = "res/music/recorded/pew.ogg"; //"res/music/player-shoot.ogg";
	
	public static final String playerCollision = "res/music/recorded/ouch.ogg";
	
	// File resources
	public static final String levelDirectory = "res/levels/";
	
	
	
	
	public static File getFile(String fileResource) {
		if(!files .contains(fileResource)) {
			files.put(fileResource, new File(fileResource));
		}
		return files.get(fileResource);
	}
	
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
