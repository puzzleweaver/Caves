package objects.enemies;

import java.awt.Image;

import org.newdawn.slick.Graphics;

import images.Sprite;
import main.Main;
import objects.Enemy;
import objects.Simple;

public class Burr extends Enemy {

	private boolean right, vertical;
	private Image image[];
	
	public Burr(int x, int y) {
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

	public void render(Graphics g) {
		update();
		timer++;
		if(timer == 12){
			timer = 0;
		}
		g.drawImage(Sprite.burrImage[timer/6], getX()-Main.sX, getY()-Main.sY);
	}

	public double getLit() {
		return 100;
	}

	public void hurt() {
		
	}

}
