package menus;

import images.Sprite;
import main.Main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class PauseMenu implements Menu {
	
	Image image = Sprite.getSprites("/images/pauseMenu.png", 100, 242, 1, 1, 400, 968)[0],
			num[] = Sprite.getSprites("/images/nums.png", 3, 5, 10, 10, 12, 20),
			marq[] = Sprite.getSprites("/images/marquee.png", 14, 14, 4, 4, 56, 56);
	public static int sY = 0, selected = -1, timer;
	
	public PauseMenu() {}
	
	public void draw(Graphics g) {
		g.drawImage(image, 0, -sY, null);
		for(int i = 0; i < 98; i++) {
			if(Main.inventory.item[i] != -1) {
				g.drawImage(Main.items[Main.inventory.item[i]], 28 + (i%7)*52, 80 + (i/7)*52 - sY, null);
				g.drawImage(num[Tools.getDigit(Main.inventory.quantity[i], 1)], 56 + (i%7)*52, 100 + (i/7)*52 - sY, null);
				g.drawImage(num[Tools.getDigit(Main.inventory.quantity[i], 2)], 40 + (i%7)*52, 100 + (i/7)*52 - sY, null);
			}
		}
		int gear[] = Main.inventory.gear;
		System.out.println(gear[2]);
		if(gear[0] != -1){
			g.drawImage(Main.items[gear[0]], 184, 808 - sY, null);
		}if(gear[1] != -1){
			g.drawImage(Main.items[gear[1]], 132, 860 - sY, null);
		}if(gear[2] != -1){
			g.drawImage(Main.items[gear[2]], 184, 860 - sY, null);
		}if(gear[4] != -1){
			g.drawImage(Main.items[gear[4]], 236, 860 - sY, null);
		}if(gear[3] != -1){
			g.drawImage(Main.items[gear[3]], 184, 912 - sY, null);
		}
		if(selected != -1){
			timer++;
			if(timer == 32){
				timer = 0;
			}
			g.drawImage(marq[timer/8], 16 + (selected%7)*52, 68 + (selected/7)*52 - sY, null);
		}
	}
	
}
