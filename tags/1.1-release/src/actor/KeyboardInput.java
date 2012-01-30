package actor;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;

public class KeyboardInput implements InputFeeder {
	
	public final static int LEFT = 0;
	public final static int RIGHT = 1;
	public final static int UP = 2;
	public final static int DOWN = 3;
	public final static int FIRE = 4;
	
	private Player player;
	
	private GameContainer container;
	
	private ArrayList<boolean[]> inputList = new ArrayList<boolean[]>(); 

	public KeyboardInput(Player player,GameContainer container) {
		this.player = player;
		this.container = container;
	}
	
	@Override
	public void poll(int delta) {
		boolean[] inputs = {false,false,false,false,false};
		boolean nop = true;
		if(container.getInput().isKeyDown(Keyboard.KEY_LEFT)) {
			player.moveLeft(delta);
			inputs[LEFT] = true;
			nop = false;

		}
		if(container.getInput().isKeyDown(Keyboard.KEY_RIGHT)) {
			player.moveRight(delta);
			inputs[RIGHT] = true;
			nop = false;
		}
		if(container.getInput().isKeyDown(Keyboard.KEY_UP)) {
			player.moveUp(delta);
			inputs[UP] = true;
			nop = false;
		}
		if(container.getInput().isKeyDown(Keyboard.KEY_DOWN)) {
			player.moveDown(delta);
			inputs[DOWN] = true;
			nop = false;
		}
		
		player.addDelta(delta);
		
		if(container.getInput().isKeyDown(Keyboard.KEY_SPACE) || container.getInput().isKeyPressed(Keyboard.KEY_SPACE)){
			player.shoot(delta);
			inputs[FIRE] = true;
			nop = false;
		}
		
		inputList.add(inputs);
	}
	
	public List<boolean[]> getInputsTriggered() {
		return inputList;
		
	}

}
