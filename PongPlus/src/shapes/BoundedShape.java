package shapes;

public interface BoundedShape extends Locatable, ScaleAndMove{
	public int getHeight();
	public int getWidth();
	public void setHeight(int newHeight);
	public void setWidth(int newWidth);
}
