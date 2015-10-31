package objects.enemies;

import java.awt.Graphics;
import java.awt.Image;

import objects.Enemy;
import main.Main;

import com.henagongames.image.Sprite;

public class Mushroom extends Enemy{

	private Image image[];
	
	public Mushroom(int x, int y) {
		super(x, y);
		x /= 32; x *= 32;
		y /= 32; y *= 32;
	}

	public void update() {
	}
	
	public void draw(Graphics g) {
		update();
		timer++;
		if(timer == 32){
			timer = 0;
		}
		g.drawImage(image[timer/16], getX()-Main.sX, getY()-Main.sY, null);
	}

	public void loadImage() {
		image = Sprite.getSprites(getClass().getResource("/images/enemies.png"), 0, 24, 8, 8, 2, 2);
		image = Sprite.resize(image, 32, 32, Sprite.EDIT_COARSE);
	}

	
	public double getLit() {
		return 100;
	}

	public void hurt() {
		
	}
	
}
