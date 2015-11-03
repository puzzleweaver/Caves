package objects.enemies;

import org.newdawn.slick.Graphics;

import main.Main;
import objects.Enemy;
import objects.Simple;
import res.Sprite;

public class Golem extends Enemy{

	private boolean right;

	public Golem(int x, int y) {
		super(x, y);
	}

	public void update() {
		if(Main.gameMenu.player.x > x && hs < 2){
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
			if(Main.getStateAt((int)x/32, (int)y/32, 15, i) <= 15){
				int nX = Main.getStateX((int)x/32, (int)y/32, 15, i)*32,
						nY = Main.getStateY((int)x/32, (int)y/32, 15, i)*32;
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
		if(timer == 32){
			timer = 0;
		}
		if(right){
			g.drawImage(Sprite.enemies[timer/8], (int)x-Main.gameMenu.sX, (int)y-Main.gameMenu.sY, null);
		}else{
			g.drawImage(Sprite.enemies[4+timer/8], (int)x-Main.gameMenu.sX, (int)y-Main.gameMenu.sY, null);
		}
	}

	public double getLit() {
		return 100;
	}

	public void hurt() {
		
	}

}
