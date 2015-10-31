package main;

import org.lwjgl.input.Mouse;

public class Input {

	public static boolean wasButtonDown[] = new boolean[Mouse.getButtonCount()];
	
	public static boolean mouseButtonPressed(int butt) {
		boolean ret = !wasButtonDown[butt] && Mouse.isButtonDown(0);
		if(wasButtonDown[butt] && !Mouse.isButtonDown(butt)) wasButtonDown[butt] = false;
		if(ret)
			wasButtonDown[butt] = true;
		return ret;
	}
	
	public static int mouseX() {
		return Mouse.getX();
	}
	public static int mouseY() {
		return Main.h-Mouse.getY();
	}

}
