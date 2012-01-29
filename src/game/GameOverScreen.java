package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import actor.IDrawable;
import actor.ImageHelper;

public class GameOverScreen implements IDrawable {

	private ImageHelper bgHelper;
	private List<String> scoreDigits;
	private List<String> levelDigits;

	public GameOverScreen(GameEngine gameEngine, GameContainer container,
			String gameoverimage) throws SlickException {
		bgHelper = new ImageHelper(gameoverimage);
	}

	@Override
	public void render(Graphics g) {
		bgHelper.render(g);
		try {
			renderDigits(g, levelDigits, 347, 306);
			renderDigits(g, scoreDigits, 347, 376);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void renderDigits(Graphics g, List<String> digits, int topX, int topY) throws SlickException {
		int runningX = topX;
		for (String location : digits) {
			Image sprite = Resources.getSprite(location);
			g.drawImage(sprite, runningX, topY);
			runningX += sprite.getWidth() + 1;
		}		
	}

	public void setFinalScore(int score) {
		scoreDigits = getDigitLocations(score);
	}

	public void setFinalLevel(int level) {
		levelDigits = getDigitLocations(level);
	}

	private List<String> getDigitLocations(int value) {
		List<String> digitLocations = new ArrayList<String>(); 
		int workingValue = value;
		while (true) {
			int remainder = workingValue % 10;
			digitLocations.add(Resources.getLocationForDigit(remainder));
			workingValue -= remainder;
			workingValue = workingValue / 10;
			if (workingValue <= 1) {
				break;
			}
		}
		Collections.reverse(digitLocations);
		return digitLocations;
	}

}
