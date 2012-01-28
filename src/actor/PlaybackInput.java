package actor;

import java.util.List;

public class PlaybackInput implements InputFeeder {

	private List<boolean[]> inputList;
	private Player player;
	int pos = 0;
	
	public PlaybackInput(Player player, List<boolean[]> inputList) {
		this.player = player;
		this.inputList = inputList;
		pos = 0;
	}
	
	@Override
	public void poll(int delta) {
		if(pos < inputList.size()) {
			boolean[] inputs = inputList.get(pos++);
			
			
				if(inputs[KeyboardInput.LEFT]) {
					player.moveLeft(delta);
				}
				if(inputs[KeyboardInput.RIGHT]) {
					player.moveRight(delta);
				}
				if(inputs[KeyboardInput.UP]) {
					player.moveUp(delta);
				}
				if(inputs[KeyboardInput.DOWN]) {
					player.moveDown(delta);
				}
				player.addDelta(delta);
				if(inputs[KeyboardInput.FIRE]) {
					player.shoot(delta);
				}
		}
		
	}

	
	public void reset() {
		pos = 0;
	}
}
