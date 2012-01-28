package actor.movehelpers;

import game.Speeds;

public class PlayerBulletMoveHelper implements IMoveHelper{

private static PlayerBulletMoveHelper instance;
	
	public static IMoveHelper getInstance() {
		
		if(instance == null) {
			instance = new PlayerBulletMoveHelper();
		}
		
		return instance;
		
	}

	@Override
	public float getShift(int delta) {
		return Speeds.playerBulletSpeed*delta/1000;
	}
	
}
