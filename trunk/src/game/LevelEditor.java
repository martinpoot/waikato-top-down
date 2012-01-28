package game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Color;

import actor.Turret;
import actor.movehelpers.ScrollingMovehelper;

public class LevelEditor extends BasicGame {
	
	List<Turret> turrets;
	Rectangle referenceBox;
	
	private String fileName = "level";
	private String extension = ".csv";

	public LevelEditor(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		for (Turret turret : turrets) {
			turret.render(g);
		}
		g.setColor(Color.green);
		g.draw(referenceBox);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		turrets = new ArrayList<Turret>();
		referenceBox = new Turret(null, container, Resources.turretShootingDown, null, 0, 
				0, ScrollingMovehelper.getInstance()).getBoundingBox();
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		referenceBox.setCenterX(container.getInput().getMouseX());
		referenceBox.setCenterY(container.getInput().getMouseY());
		if (container.getInput().isMouseButtonDown(0)) {
			Turret newTurret = new Turret(null, container, Resources.turretShootingDown, null, (float)container.getInput().getMouseX() - referenceBox.getWidth() / 2, 
					(float)container.getInput().getMouseY() - referenceBox.getHeight() / 2, ScrollingMovehelper.getInstance());
			boolean collided = false;
			for (Turret t : turrets) {			
				if (t.getBoundingBox().intersects(newTurret.getBoundingBox()) ) {
					collided = true;
					break;
				}
			}			
			if (!collided) turrets.add(newTurret);	
		}
		else if (container.getInput().isMouseButtonDown(1)) {	// rightclick
			Turret newTurret = new Turret(null, container, Resources.turretShootingDown, null, (float)container.getInput().getMouseX() - referenceBox.getWidth() / 2, 
					(float)container.getInput().getMouseY() - referenceBox.getHeight() / 2, ScrollingMovehelper.getInstance());
			Turret collidedTurret = null;
			for (Turret t : turrets) {			
				if (t.getBoundingBox().intersects(newTurret.getBoundingBox()) ) {
					collidedTurret = t;
					break;
				}
			}			
			if (collidedTurret != null) turrets.remove(collidedTurret);	
		}
		
		if (container.getInput().isKeyPressed(Keyboard.KEY_S)) {
			saveTurretPositions();
		}
	}
	
	private void saveTurretPositions() {
		String output = "";
		for (Turret t : turrets) {
			output += (int)t.getX() + "," + (int)t.getY() + "\n"; 
		}
		System.out.print(output);
		try {
			FileWriter fstream = new FileWriter(Resources.getFile(Resources.levelDirectory) + fileName + "-" + (int)(System.currentTimeMillis() / 1000) + extension);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(output);
			  //Close the output stream
			out.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void main(String[] args) throws SlickException {
        AppGameContainer app = 
			new AppGameContainer(new LevelEditor("Memories that haunt you - Level Editor"),800,600,false);
        app.setVSync(true);
        app.start();
}

}
