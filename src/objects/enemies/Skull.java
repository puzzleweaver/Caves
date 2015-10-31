package objects.enemies;

import org.newdawn.slick.Graphics;

import images.Sprite;
import main.Main;
import objects.Enemy;

public class Skull extends Enemy{

		public Skull(int x, int y) {
			super(x, y);
		}

		public void update() {
			double l = Math.pow((Main.player.x-x)*(Main.player.x-x)+(Main.player.y-y)*(Main.player.y-y), -2.0);
			x -= (Main.player.x-x)*l;
			y -= (Main.player.y-y)*l;
		}

		public double getLit() {
			return 1;
		}

		public void hurt() {
			
		}

		public void render(Graphics g) {
			update();
			timer++;
			if(timer == 32){
				timer = 0;
			}
			if(Main.player.getX() >= x){
				g.drawImage(Sprite.enemies[timer/8], getX()-Main.sX, getY()-Main.sY, null);
			}else{
				g.drawImage(Sprite.enemies[4+timer/8], getX()-Main.sX, getY()-Main.sY, null);
			}
		}
		
	}
	