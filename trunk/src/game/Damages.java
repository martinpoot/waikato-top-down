package game;

public class Damages {

	public static final float playerDamage = 5.0f;
	public static final float ghostDamage = playerDamage / 2;
	public static final float turretDamage = 5.0f;
	public static final int turretHealth = 10;
	public static final int playerHealth = 300;
	
	// Controls how quickly randomly generated turrets are added (per round)
	public static int turretIncreaseRate	= 10;
	public static int initialTurretCount = 30;
	
}
