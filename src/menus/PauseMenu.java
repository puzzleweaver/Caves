package menus;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;

import images.Sprite;
import main.Input;
import main.Main;
import objects.Simple;

public class PauseMenu implements Menu {
	
	public static int sY = 0, selected = -1, timer;
	
	public PauseMenu() {}
	
	public void update() {
		if(Input.mouseButtonPressed(0)){
			if(Simple.pointRect(Mouse.getX(), Mouse.getY(), 20, 20, 152, 48)){
				Main.menu = Main.gameMenu;
				PauseMenu.selected = -1;
				Main.menu = Main.gameMenu;
			}else{
				for(int i = 0; i < 98; i++){
					if(Simple.pointRect(Input.mouseX(), Input.mouseY()+PauseMenu.sY, 28 + (i%7)*52, 80 + (i/7)*52, 48, 48)){
						if(PauseMenu.selected == -1){
							PauseMenu.selected = i;
						}else{
							Main.inventory.swap(PauseMenu.selected, i);
							PauseMenu.selected = -1;
						}
						break;
					}
				}
				if(PauseMenu.selected != -1){
					if(Simple.pointRect(Input.mouseX(), Input.mouseY()+PauseMenu.sY, 176, 800, 48, 48)){
						Main.inventory.equip(Main.inventory.EQUIP_HEAD, Main.inventory.item[PauseMenu.selected]);
					}else if(Simple.pointRect(Input.mouseX(), Input.mouseY()+PauseMenu.sY, 176, 852, 48, 48)){
						Main.inventory.equip(Main.inventory.EQUIP_TORSO, Main.inventory.item[PauseMenu.selected]);
					}else if(Simple.pointRect(Input.mouseX(), Input.mouseY()+PauseMenu.sY, 124, 852, 48, 48)){
						Main.inventory.equip(Main.inventory.EQUIP_SHIELD, Main.inventory.item[PauseMenu.selected]);
					}else if(Simple.pointRect(Input.mouseX(), Input.mouseY()+PauseMenu.sY, 228, 852, 48, 48)){
						Main.inventory.equip(Main.inventory.EQUIP_MELEE, Main.inventory.item[PauseMenu.selected]);
					}else if(Simple.pointRect(Input.mouseX(), Input.mouseY()+PauseMenu.sY, 176, 904, 48, 48)){
						Main.inventory.equip(Main.inventory.EQUIP_FEET, Main.inventory.item[PauseMenu.selected]);
					}else if(Simple.pointRect(Input.mouseX(), Input.mouseY()+PauseMenu.sY, 176, 20, 48, 48)){
						Main.inventory.trash(PauseMenu.selected);
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(Sprite.pauseMenu, 0, -sY, null);
		for(int i = 0; i < 98; i++) {
			if(Main.inventory.item[i] != -1) {
				g.drawImage(Sprite.items[Main.inventory.item[i]], 28 + (i%7)*52, 80 + (i/7)*52 - sY, null);
				//might be a problem
				g.drawImage(Sprite.pauseNum[Main.inventory.quantity[i] / 10], 40 + (i%7)*52, 100 + (i/7)*52 - sY, null);
				g.drawImage(Sprite.pauseNum[Main.inventory.quantity[i] % 10], 56 + (i%7)*52, 100 + (i/7)*52 - sY, null);
			}
		}
		int gear[] = Main.inventory.gear;
		System.out.println(gear[2]);
		if(gear[0] != -1){
			g.drawImage(Sprite.items[gear[0]], 184, 808 - sY, null);
		}if(gear[1] != -1){
			g.drawImage(Sprite.items[gear[1]], 132, 860 - sY, null);
		}if(gear[2] != -1){
			g.drawImage(Sprite.items[gear[2]], 184, 860 - sY, null);
		}if(gear[4] != -1){
			g.drawImage(Sprite.items[gear[4]], 236, 860 - sY, null);
		}if(gear[3] != -1){
			g.drawImage(Sprite.items[gear[3]], 184, 912 - sY, null);
		}
		if(selected != -1){
			timer++;
			if(timer == 32){
				timer = 0;
			}
			g.drawImage(Sprite.marq[timer/8], 16 + (selected%7)*52, 68 + (selected/7)*52 - sY, null);
		}
	}

}
