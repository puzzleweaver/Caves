package objects;

import images.Sprite;
import main.Main;
import menus.PauseMenu;

public class Inventory {

	private boolean full;
	public static final int ITEM_BAG = 0, ITEM_GOLD = 1, ITEM_SILVER = 2, ITEM_BRONZE = 3, ITEM_CHICKEN = 4, ITEM_APPLE = 5, ITEM_PURPLE = 6,
			ITEM_GREEN = 7, ITEM_RED = 8, ITEM_BLUE = 9, ITEM_YELLOW = 10, ITEM_CLOTH_HELMET = 11, ITEM_CLOTH_SHIRT = 12, ITEM_CLOTH_PANTS = 13,
					ITEM_IRON_HELMET = 14, ITEM_IRON_SHIRT = 15, ITEM_IRON_PANTS = 16, ITEM_RED_HELMET = 17, ITEM_RED_SHIRT = 18,
					ITEM_RED_PANTS = 19, ITEM_BEST_HELMET = 20, ITEM_BEST_SHIRT = 21, ITEM_BEST_PANTS = 22, ITEM_STICK = 31, ITEM_SPEAR = 32, 
					ITEM_HOOK = 33, ITEM_MAP = 34;
	public static final int EQUIP_HEAD = 0, EQUIP_SHIELD = 1, EQUIP_TORSO = 2, EQUIP_MELEE = 4, EQUIP_FEET = 3;
	public int item[] = new int[100], quantity[] = new int[100],
			gear[] = {ITEM_CLOTH_HELMET, -1, ITEM_CLOTH_SHIRT, ITEM_CLOTH_PANTS, ITEM_SPEAR};
	
	public Inventory(){
		for(int i = 0; i < 100; i++){
			item[i] = -1;
			quantity[i] = 0;
		}
	}
	public void add(Item d){
		boolean not = true;
		for(int i = 0; i < 100; i++){
			if(item[i] == d.getID() && quantity[i] < getMaxStack(item[i])){
				quantity[i]++;
				not = false;
				break;
			}else if(item[i] == -1){
				item[i] = d.getID();
				quantity[i]++;
				not = false;
				break;
			}
		}
		if(not){
			full = true;
		}else{
			full = false;
		}
	}
	
	private int getMaxStack(int item){
		if(item >= 11 && item <= 31){
			return 1;
		}
		return 99;
	}
		
	public boolean full(){
		return full;
	}
	public void swap(int index1, int index2){
		int itm = item[index1], quant = quantity[index1];
		item[index1] = item[index2]; quantity[index1] = quantity[index2];
		item[index2] = itm; quantity[index2] = quant;
	}
	public void equip(int slot, int ID){
		if(equippable(slot, ID)){
			if(gear[slot] == -1 && ID != -1){
				quantity[ID] = 0;
			}else{
				quantity[PauseMenu.selected] = 1;
			}
			int itm = gear[slot];
			gear[slot] = ID;
			item[PauseMenu.selected] = itm;
		}
		PauseMenu.selected = -1;
	}
	public void trash(int slot){
		PauseMenu.selected = -1;
		item[slot] = -1;
		quantity[slot] = 0;
	}
	
	public boolean equippable(int slot, int ID){
		if(ID == -1){
			return true;
		}
		switch(slot){
		case EQUIP_HEAD:
			if(ID == 11 || ID == 14 || ID == 17 || ID == 20){
				return true;
			}
			break;
		case EQUIP_TORSO:
			if(ID == 12 || ID == 15 || ID == 18 || ID == 21){
				return true;
			}
			break;
		case EQUIP_FEET:
			if(ID == 13 || ID == 16 || ID == 19 || ID == 22){
				return true;
			}
			break;
		case EQUIP_SHIELD:
			if(ID >= 23 && ID <= 26){
				return true;
			}
			break;
		case EQUIP_MELEE:
			if(ID >= 27 && ID <= 34){
				return true;
			}
			break;
		}
		return false;
	}
	public int getGarment(int slot){
		switch(slot){
		case EQUIP_HEAD:
			switch(gear[EQUIP_HEAD]){
			case 11:
				return 1;
			case 14:
				return 2;
			case 17:
				return 3;
			case 20:
				return 4;
			}
			break;
		case EQUIP_TORSO:
			switch(gear[EQUIP_TORSO]){
			case 12:
				return 5;
			case 15:
				return 6;
			case 18:
				return 7;
			case 21:
				return 8;
			}
			break;
		case EQUIP_FEET:
			switch(gear[EQUIP_FEET]){
			case 13:
				return 9;
			case 16:
				return 10;
			case 19:
				return 11;
			case 22:
				return 12;
			}
			break;
		case EQUIP_SHIELD:
			switch(gear[EQUIP_SHIELD]){
			case 23:
				return 13;
			case 24:
				return 14;
			case 25:
				return 15;
			case 26:
				return 16;
			}
		}
		return 0;
	}
	
	public static int chestSpawn(){
		return Main.r.nextInt(Sprite.items.length);
	}
	public static int potSpawn(){
		return Main.r.nextInt(Sprite.items.length);
	}
	
}
