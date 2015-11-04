package menus;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;

import main.Input;
import main.Main;
import objects.Inventory;
import objects.Simple;
import res.Sprite;

public class PauseMenu implements Menu {
	
	public int sY = 600, selected = -1, timer;
	
	public PauseMenu() {}
	
	public void update() {
		sY = (int) Math.max(0, Math.min(Main.scale*90, sY-Mouse.getDWheel()));
		if(Input.mouseButtonPressed(0)){
			if(Simple.pointRect(Input.mouseX(), Input.mouseY()+sY, Main.scale*5, Main.scale*5, Main.scale*38, Main.scale*12)){
				Main.menu = Main.gameMenu;
				selected = -1;
				Main.menu = Main.gameMenu;
			}else{
				for(int i = 0; i < 98; i++){
					if(Simple.pointRect(Input.mouseX(), Input.mouseY()+sY, Main.scale*(7 + (i%7)*13), Main.scale*(20 + (i/7)*13), Main.scale*12, Main.scale*12)){
						if(selected == -1){
							selected = i;
						}else{
							Main.gameMenu.inventory.swap(selected, i);
							selected = -1;
						}
						break;
					}
				}
				if(selected != -1){
					if(Simple.pointRect(Input.mouseX(), Input.mouseY()+sY, Main.scale*44, Main.scale*200, Main.scale*12, Main.scale*12)){
						Main.gameMenu.inventory.equip(Inventory.EQUIP_HEAD, Main.gameMenu.inventory.item[selected]);
					}else if(Simple.pointRect(Input.mouseX(), Input.mouseY()+sY, Main.scale*44, Main.scale*213, Main.scale*12, Main.scale*12)){
						Main.gameMenu.inventory.equip(Inventory.EQUIP_TORSO, Main.gameMenu.inventory.item[selected]);
					}else if(Simple.pointRect(Input.mouseX(), Input.mouseY()+sY, Main.scale*31, Main.scale*213, Main.scale*12, Main.scale*12)){
						Main.gameMenu.inventory.equip(Inventory.EQUIP_SHIELD, Main.gameMenu.inventory.item[selected]);
					}else if(Simple.pointRect(Input.mouseX(), Input.mouseY()+sY, Main.scale*57, Main.scale*213, Main.scale*12, Main.scale*12)){
						Main.gameMenu.inventory.equip(Inventory.EQUIP_MELEE, Main.gameMenu.inventory.item[selected]);
					}else if(Simple.pointRect(Input.mouseX(), Input.mouseY()+sY, Main.scale*44, Main.scale*226, Main.scale*12, Main.scale*12)){
						Main.gameMenu.inventory.equip(Inventory.EQUIP_FEET, Main.gameMenu.inventory.item[selected]);
					}else if(Simple.pointRect(Input.mouseX(), Input.mouseY()+sY, Main.scale*44, Main.scale*5, Main.scale*12, Main.scale*12)){
						Main.gameMenu.inventory.trash(selected);
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(Sprite.pauseMenu, 0, -sY, null);
		for(int i = 0; i < 98; i++) {
			if(Main.gameMenu.inventory.item[i] != -1) {
				g.drawImage(Sprite.items[Main.gameMenu.inventory.item[i]], (int) Main.scale*(7 + (i%7)*13), (int)Main.scale*(20 + (i/7)*13) - sY, null);
				g.drawImage(Sprite.pauseNum[Main.gameMenu.inventory.quantity[i] / 10], (int) Main.scale*(10 + (i%7)*13), (int) Main.scale*(25 + (i/7)*13) - sY, null);
				g.drawImage(Sprite.pauseNum[Main.gameMenu.inventory.quantity[i] % 10], (int) Main.scale*(14 + (i%7)*13), (int) Main.scale*(25 + (i/7)*13) - sY, null);
			}
		}
		int gear[] = Main.gameMenu.inventory.gear;
		if(gear[0] != -1){
			g.drawImage(Sprite.items[gear[0]], (int) Main.scale*46, (int) Main.scale*202 - sY, null);
		}if(gear[1] != -1){
			g.drawImage(Sprite.items[gear[1]], (int) Main.scale*33, (int) Main.scale*215 - sY, null);
		}if(gear[2] != -1){
			g.drawImage(Sprite.items[gear[2]], (int) Main.scale*46, (int) Main.scale*215 - sY, null);
		}if(gear[4] != -1){
			g.drawImage(Sprite.items[gear[4]], (int) Main.scale*59, (int) Main.scale*215 - sY, null);
		}if(gear[3] != -1){
			g.drawImage(Sprite.items[gear[3]], (int) Main.scale*46, (int) Main.scale*228 - sY, null);
		}
		if(selected != -1){
			timer++;
			if(timer == 32){
				timer = 0;
			}
			g.drawImage(Sprite.marq[timer/8], (int) Main.scale*(4 + (selected%7)*13), (int) Main.scale*(17 + (selected/7)*13) - sY, null);
		}
	}

}
