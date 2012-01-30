package actor;

import org.newdawn.slick.GameContainer;

public class ScrollingInputFeeder implements InputFeeder {
	

	private IMoveable myMovable;
	private GameContainer container;

	public ScrollingInputFeeder(IMoveable myMovable,GameContainer container) {
		this.myMovable = myMovable;
		this.container = container;
	}

	public void poll(int delta) {
		myMovable.moveDown(delta);
	}
	
	

}
