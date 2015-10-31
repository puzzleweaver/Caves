package objects.enemies;

import org.newdawn.slick.Graphics;

import images.Sprite;
import main.Main;
import objects.Enemy;

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
		g.drawImage(Sprite.enemies[timer/16], getX()-Main.sX, getY()-Main.sY, null);
	}

	public double getLit() {
		return 100;
	}

	public void hurt() {
		
	}

}
