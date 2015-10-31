package objects.enemies;

import java.awt.Graphics;
import java.awt.Image;

import objects.Enemy;
import main.Main;

import com.henagongames.geometry.Simple;
import com.henagongames.image.Sprite;

public class Golem extends Enemy{

	private boolean right;
	private Image image[];
	
	public Golem(int x, int y) {
		super(x, y);
	}

	public void update() {
		if(Main.player.getX() > x && hs < 2){
			hs+=.1;
		}else if(hs > -2){
			hs-=.1;
		}
		if(vs < 11){
			vs += .5;
		}
		x += hs;
		y += vs;
		checkCollisions();
		if(hs > 0){
			right = true;
		}else if(hs < 0){
			right = false;
		}
	}
	
	private void checkCollisions(){
		for(int i = 0; i < 225; i++){
			if(Main.getStateAt(getX()/32, getY()/32, 15, i) <= 15){
				int nX = Main.getStateX(getX()/32, getY()/32, 15, i)*32,
						nY = Main.getStateY(getX()/32, getY()/32, 15, i)*32;
				if(Simple.rectRect(x+2, y+vs, 28, 32, nX, nY, 32, 32)){
					vs = 0;
					if(y >= nY){
						y = nY+32;
					}else{
						y = nY-32;
					}
				}else if(Simple.rectRect(x+hs, y+1, 32, 30, nX, nY, 32, 32)){
					hs = 0;
					if(x > nX){
						x = nX+32;
					}else if(x < nX+32){
						x = nX-32;
					}
				}
			}
		}
	}

	public void draw(Graphics g) {
		update();
		timer++;
		if(timer == 32){
			timer = 0;
		}
		if(right){
			g.drawImage(image[timer/8], getX()-Main.sX, getY()-Main.sY, null);
		}else{
			g.drawImage(image[4+timer/8], getX()-Main.sX, getY()-Main.sY, null);
		}
	}

	public void loadImage() {
		image = Sprite.getSprites(getClass().getResource("/images/enemies.png"), 0, 8, 8, 8, 8, 8);
		image = Sprite.resize(image, 32, 32, Sprite.EDIT_COARSE);
	}

	
	public double getLit() {
		return 100;
	}

	public void hurt() {
		
	}
	
}
