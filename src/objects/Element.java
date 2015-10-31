package objects;

public class Element {
	
	public double x, y, vs, hs;
	
	public Element(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return (int) x;
	}
	public int getY() {
		return (int) y;
	}
	
}
