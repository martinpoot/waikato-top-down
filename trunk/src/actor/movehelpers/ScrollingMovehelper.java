package actor.movehelpers;

import game.Speeds;

public class ScrollingMovehelper implements IMoveHelper{

	@Override
	public float getShift(int delta) {
		return Speeds.background*delta/1000;
	}
	
	

}
