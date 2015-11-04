package objects;


import org.newdawn.slick.Graphics;

import res.Sprite;
import main.Main;

public class Item extends Element {
	
	int timer = 0, ID = 0;
	private boolean done;
	
	public Item(int x, int y, int ID) {
		super(x, y);
		this.ID = ID;
		vs = -5; hs = Main.r.nextInt(9)-4;
	}
	
	public void update(){
		double dist = Math.hypot(y-Main.gameMenu.player.y, x-Main.gameMenu.player.x);
		if(dist > 64) {
			timer++;
			if(hs > 0){
				hs -= .5;
			}else if(hs < 0){
				hs += .5;
			}
			if(vs < 10){
				vs+=.5;
			}
			checkCollisions();
		}else {
			hs *= 0.9;
			vs *= 0.9;
			hs -= (x-Main.gameMenu.player.x)/dist*0.6;
			vs -= (y-Main.gameMenu.player.y)/dist*0.6;
		}
		x += hs;
		y += vs;
		if(dist < 20) done = true;
	}
	
	public void checkCollisions(){
		for(int i = 0; i < 250; i++){
			if(Main.getStateAt((int)x/32, (int)y/32, 15, i) <= 15){
				int nX = Main.getStateX((int)x/32, (int)y/32, 15, i)*32,
						nY = Main.getStateY((int)x/32, (int)y/32, 15, i)*32;
				if(Simple.rectRect(x+1, y+1, 30, 30, nX, nY, 32, 32)
					) {
					y = nY-31;
					vs = 0.5;
				}
			}
		}
	}
	public int done(){
		if(done){
			return 1;
		}
		return 0;
	}
	public int getID(){
		return ID;
	}
	
	public void render(Graphics g){
		g.drawImage(Sprite.items[ID], (int)(x*0.25*Main.scale) - Main.gameMenu.sX, (int)(y*0.25*Main.scale) - Main.gameMenu.sY);
	}
	
}
