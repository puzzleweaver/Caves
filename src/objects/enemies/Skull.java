package objects.enemies;

import java.awt.Graphics;
import java.awt.Image;

import main.Main;
import objects.Enemy;

import com.henagongames.image.Sprite;
import com.henagongames.tools.Tools;

public class Skull extends Enemy{

		private Image image[];
		
		public Skull(int x, int y) {
			super(x, y);
		}

		public void update() {
			double dir = (Tools.getAngle(Main.player.getX(), Main.player.getY(), x, y));
			x += Math.cos(dir/180*Math.PI)*2.0;
			y += Math.sin(dir/180*Math.PI)*2.0;
		}

		public void draw(Graphics g) {
			update();
			timer++;
			if(timer == 32){
				timer = 0;
			}
			if(Main.player.getX() >= x){
				g.drawImage(image[timer/8], getX()-Main.sX, getY()-Main.sY, null);
			}else{
				g.drawImage(image[4+timer/8], getX()-Main.sX, getY()-Main.sY, null);
			}
		}

		public void loadImage() {
			image = Sprite.getSprites(getClass().getResource("/images/enemies.png"), 0, 0, 8, 8, 8, 8);
			image = Sprite.resize(image, 32, 32, Sprite.EDIT_COARSE);
		}

		public double getLit() {
			return 1;
		}

		public void hurt() {
			
		}
		
	}
	