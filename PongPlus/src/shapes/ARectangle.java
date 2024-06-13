package shapes;



public class ARectangle extends ABoundedShape implements Rectangle{
	
	
	public static final int 
		RECT_DEFAULT_X = 100, 
		RECT_DEFAULT_Y = 100, 
		DEFAULT_HEIGHT = 50, 
		DEFAULT_WIDTH = 8
	;

	public ARectangle() {
		this(RECT_DEFAULT_X, RECT_DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public ARectangle(int theX, int theY, int theWidth, int theHeight) {
		this.width = theWidth;
		this.height = theHeight;
		this.x = theX;
		this.y = theY;
	}
	

	
}
