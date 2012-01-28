package game;

public class Damages {

	// Damage
	public static final float playerDamage = 5.0f;
	public static final float ghostDamage = playerDamage / 2;
	public static final float turretDamage = 5.0f;
	// Collision damage = this * actor damage
	public static final float collisionModifier = 10.0f;
	
	// Health
	public static final int turretHealth = 10;
	public static final int playerHealth = 300;
	
	// Controls how quickly randomly generated turrets are added (per round)
	public static int turretIncreaseRate = 10;
	public static int initialTurretCount = 30;
	
	public static boolean turretCollision = true;
	
}
