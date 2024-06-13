package controller;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import shapes.BoundedShape;

public interface PongController extends MouseListener, KeyListener{

	public void moveBall();

	public void movePlayer(BoundedShape player);
	
}
