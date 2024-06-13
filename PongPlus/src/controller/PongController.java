package controller;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import shapes.BoundedShape;
import shapes.Player;

public interface PongController extends MouseListener, KeyListener, Runnable{
	
	public void movePlayer(Player player);

	public void movePlayerAndBall(Player player, BoundedShape ball, boolean moveUp, boolean moveDown);

	public void moveBall(BoundedShape ball);

	public void startGame();

	
}
