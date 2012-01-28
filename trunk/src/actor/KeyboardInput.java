package actor;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;

public class KeyboardInput implements InputFeeder {
	
	private Player player;
	
	private GameContainer container;

	public KeyboardInput(Player player,GameContainer container) {
		this.player = player;
		this.container = container;
	}
	
	@Override
	public void poll(int delta) {
		if(container.getInput().isKeyDown(Keyboard.KEY_LEFT)) {
			player.moveLeft(delta);
		}
		if(container.getInput().isKeyDown(Keyboard.KEY_RIGHT)) {
			player.moveRight(delta);
		}
		if(container.getInput().isKeyDown(Keyboard.KEY_UP)) {
			player.moveUp(delta);
		}
		if(container.getInput().isKeyDown(Keyboard.KEY_DOWN)) {
			player.moveDown(delta);
		}
		
		player.addDelta(delta);
		
		if(container.getInput().isKeyDown(Keyboard.KEY_SPACE) || container.getInput().isKeyPressed(Keyboard.KEY_SPACE)){
			player.shoot(delta);
		}
	}
	
	

}
