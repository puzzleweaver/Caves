package main;

public class World {
	
	private final static int STONE = 0, SEED = -1, SPACE = -2, CHEST = -3, POT = -4, BARREL = -5;
	
	public static int w = 500, h = 500;
	public static int[][] state = new int[w][h];
	
	public static void loadWorld(){
		// let there be light
		
		// carve out cave system
		int x = w/2, y = h/2;
		for(int i = 0; i < 10000; i++) {
			x += Main.r.nextInt(3)-1;
			y += Main.r.nextInt(3)-1;
			if(x < 0 || x > w-2 || y < 0 || y > h-2) {
				x = w/2;
				y = h/2;
			}
			state[x][y] = SPACE;
			state[x+1][y] = SPACE;
			state[x][y+1] = SPACE;
			state[x+1][y+1] = SPACE;
		}
		
		int chests = 100;
		for(int i = 0; i < chests; i++) {
			while(state[x][y+1] == SPACE)
				y++;
			state[x][y] = CHEST;
			do {
				x = Main.r.nextInt(w);
				y = Main.r.nextInt(h);
			} while(state[x][y] != SPACE);
		}
		
		//translate simple state values into image indeces
		for(int i = 0; i < w*h; i++){
			x = i%w;
			y = i/w;
			state[x][y] = getIndex(state[x][y]);
		}
	}
	
	private static int getIndex(int i){
		if(i == -1 || i == -2){
			return 16+Main.r.nextInt(16);
		}else if(i == -3){
			return 33;
		}else if(i == -4){
			return 34;
		}
		return Main.r.nextInt(16);
	}
	
}
