package game;

public class Damages {

	// Damage
	public static final float playerDamage = 5.0f;
	public static final float ghostDamage = playerDamage;
	public static final float turretDamage = 2.0f;
	// Collision damage = this * actor damage
	public static final float collisionModifier = 10.0f;
	
	// Health
	public static final int turretHealth = 10;
	public static final int playerHealth = 200;
	
	public static final boolean turretCollision = true;
	
}
