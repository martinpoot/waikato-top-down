package actor;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;

public class KeyboardInput implements InputFeeder {
	
	private IMoveable myMovable;
	private GameContainer container;

	public KeyboardInput(IMoveable myMovable,GameContainer container) {
		this.myMovable = myMovable;
		this.container = container;
	}
	
	@Override
	public void poll(int delta) {
		if(container.getInput().isKeyDown(Keyboard.KEY_LEFT)) {
			myMovable.moveLeft(delta);
		}
		if(container.getInput().isKeyDown(Keyboard.KEY_RIGHT)) {
			myMovable.moveRight(delta);
		}
		if(container.getInput().isKeyDown(Keyboard.KEY_UP)) {
			myMovable.moveUp(delta);
		}
		if(container.getInput().isKeyDown(Keyboard.KEY_DOWN)) {
			myMovable.moveDown(delta);
		}
	}
	
	

}
