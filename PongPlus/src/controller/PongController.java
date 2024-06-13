package controller;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import shapes.BoundedShape;
import shapes.Player;

public interface PongController extends MouseListener, KeyListener{

	public void moveBall();

	public void movePlayer(Player player);

	public void movePlayerAndBall(Player player, BoundedShape ball, boolean moveUp, boolean moveDown);
	
}
