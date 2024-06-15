package shapes;

public interface Circle extends BoundedShape{
	public void setRadius(int newRadius);
	public int getRadius();
	public boolean isVisible();
	public void setVisible(boolean visibility);
}
