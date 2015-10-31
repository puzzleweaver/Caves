package main;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class World {
	
	private final static int STONE = 0, SEED = -1, SPACE = -2, CHEST = -3, POT = -4, BARREL = -5;
	
	public static Image makeWorldImage(){
		BufferedImage image = new BufferedImage(1000, 2000, BufferedImage.TYPE_4BYTE_ABGR);
		int x = 0, y = 0;
		for(int j = 0; j < 400; j++){
			x = Main.r.nextInt(400); y = Main.r.nextInt(400);
			for(int i = 0; i < 400; i++){
				int oX = Main.r.nextInt(3)-1, oY = Main.r.nextInt(3)-1;
				if(oX == oY && oY == STONE){
					i--;
				}else{
					x+=oX;
					y+=oY;
					try{
						image.setRGB(x, y, SEED);
					}catch(Exception e){
						x-=oX; y-=oY;
						i--;
					}
				}
			}
		}
		for(int i = 0; i < 2000000; i++){
			if(image.getRGB(i%1000, i/1000) == SEED){
				try{
					image.setRGB(i%1000, i/1000, SPACE);
					image.setRGB((i%1000)+1, i/1000, SPACE);
					image.setRGB((i%1000)-1, i/1000, SPACE);
					image.setRGB(i%1000, (i/1000)+1, SPACE);
					image.setRGB(i%1000, (i/1000)-1, SPACE);
					image.setRGB((i%1000)+1, (i/1000)+1, SPACE);
					image.setRGB((i%1000)-1, (i/1000)-1, SPACE);
					image.setRGB((i%1000)-1, (i/1000)+1, SPACE);
					image.setRGB((i%1000)+1, (i/1000)-1, SPACE);
				}catch(Exception e){}
			}
		}
		for(int i = 0; i < 1999000; i++){
			try{
				if(image.getRGB(i%1000, i/1000+1) == STONE && image.getRGB(i%1000, i/1000) == SPACE &&
						image.getRGB(i%1000+1, i/1000-1) == SPACE && image.getRGB(i%1000-1, i/1000-1) == SPACE &&
						image.getRGB(i%1000+1, i/1000) == SPACE && image.getRGB(i%1000-1, i/1000) == SPACE){
					if(Main.r.nextInt(20) == STONE){
						image.setRGB(i%1000, i/1000, CHEST);
					}else if(Main.r.nextInt(10) == STONE){
						image.setRGB(i%1000, i/1000, -4);
					}
				}
			}catch(Exception e){
				
			}
		}
		for(int i = 0; i < 800; i++){
			try{
				int X = x-20+i%40, Y = y-10+i/40;
				image.setRGB(X, Y, SPACE);
			}catch(Exception e){
				return makeWorldImage();
			}
		}
		for(int i = 0; i < 40; i++){
			try{
				image.setRGB(x-20+i, y-10, STONE);
				image.setRGB(x-20+i, y+10, STONE);
				if(Main.r.nextInt(5) == 0){
					image.setRGB(x-20+i, y+9, POT);
				}
			}catch(Exception e){
				return makeWorldImage();
			}
		}
		Main.player.x = x*32; Main.player.y = y*32;
		return image;
	}
	
	public static Image loadWorld(Image image){
		BufferedImage img = (BufferedImage) image;
		for(int i = 0; i < 2000000; i++){
			int x = i%1000, y = i/1000;
			Main.state[x][y] = getIndex(img.getRGB(x, y));
		}
		return img;
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
