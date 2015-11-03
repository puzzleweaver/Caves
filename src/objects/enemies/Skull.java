package objects.enemies;

import org.newdawn.slick.Graphics;

import main.Main;
import objects.Enemy;
import res.Sprite;

public class Skull extends Enemy{

		public Skull(int x, int y) {
			super(x, y);
		}

		public void update() {
			double l = Math.pow((Main.gameMenu.player.x-x)*(Main.gameMenu.player.x-x)+(Main.gameMenu.player.y-y)*(Main.gameMenu.player.y-y), -0.5);
			x += (Main.gameMenu.player.x-x)*l;
			y += (Main.gameMenu.player.y-y)*l;
		}

		public double getLit() {
			return 1;
		}

		public void hurt() {
			
		}

		public void render(Graphics g) {
			timer++;
			if(timer == 32){
				timer = 0;
			}
			if(Main.gameMenu.player.x >= x){
				g.drawImage(Sprite.enemies[timer/8], (int)x-Main.gameMenu.sX, (int)y-Main.gameMenu.sY, null);
			}else{
				g.drawImage(Sprite.enemies[4+timer/8], (int)x-Main.gameMenu.sX, (int)y-Main.gameMenu.sY, null);
			}
		}
		
	}
	