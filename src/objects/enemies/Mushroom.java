package objects.enemies;

import org.newdawn.slick.Graphics;

import main.Main;
import objects.Enemy;
import res.Sprite;

public class Mushroom extends Enemy{

	public Mushroom(int x, int y) {
		super(x, y);
		x = (x/32)*32;
		y = (y/32)*32;
	}

	public void update() {
	}
	
	public void render(Graphics g) {
		update();
		timer++;
		if(timer == 32){
			timer = 0;
		}
		// 0 3 1 3
		g.drawImage(Sprite.enemies[24+timer/16], (int)x-Main.gameMenu.sX, (int)y-Main.gameMenu.sY, null);
	}

	public double getLit() {
		return 100;
	}

	public void hurt() {
		
	}

}
