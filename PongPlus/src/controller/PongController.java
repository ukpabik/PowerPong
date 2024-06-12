package controller;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public interface PongController extends MouseListener, KeyListener{

	public void moveBall();

	public void movePlayerTwo();

	public void movePlayerOne();
	
}
