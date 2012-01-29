package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import actor.IDrawable;
import actor.ImageHelper;

public class StatusBar implements IDrawable {

	private GameEngine engine;
	private ImageHelper bgHelper;
	private int y;
	private GameContainer container;
	private ImageHelper scoreHelper;
	private ImageHelper healthHelper;

	public StatusBar(GameEngine gameEngine, GameContainer container, String background) throws SlickException {
		engine = gameEngine;
		bgHelper = new ImageHelper(background);
		y = container.getHeight() - getHeight();
		bgHelper.setTopY(y);
		
		scoreHelper = new ImageHelper(Resources.score);
		scoreHelper.setTopY(y + 16);
		scoreHelper.setTopX(10);
		healthHelper = new ImageHelper(Resources.health);
		healthHelper.setTopY(y + 16);
		healthHelper.setTopX(container.getWidth() - 200);
		
		this.container = container;
	}

	@Override
	public void render(Graphics g) {		
		bgHelper.render(g);
		
		scoreHelper.render(g);
		healthHelper.render(g);
		
		int score = engine.getScore();
		System.out.println("rendering score " + score);
		List<String> scoreDigits = getDigitLocations(score);
		
		int health = engine.getPlayer().getHealth();
		List<String> healthDigits = getDigitLocations(health);

		try {
			renderDigits(g, scoreDigits, (int) Math.ceil(scoreHelper.getBoundingBox().getMaxX() + 3), y + 18);
			renderDigits(g, healthDigits, (int) Math.ceil(healthHelper.getBoundingBox().getMaxX() + 3), y + 18);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getHeight() {
		return bgHelper.getHeight();
	}

	private void renderDigits(Graphics g, List<String> digits, int topX, int topY) throws SlickException {
		int runningX = topX;
		for (String location : digits) {
			Image sprite = Resources.getSprite(location);
			g.drawImage(sprite, runningX, topY);
			runningX += sprite.getWidth() + 1;
		}		
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
