package actor.movehelpers;

import game.Speeds;

public class ScrollingMovehelper implements IMoveHelper{
	
	private static IMoveHelper instance;
	
	public static IMoveHelper getInstance() {
		if (instance == null) {
			instance = new ScrollingMovehelper();
		}
		return instance;
	}

	private ScrollingMovehelper() {
		
	}
	
	@Override
	public float getShift(int delta) {
		return Speeds.background*delta/1000;
	}
	
	

}
