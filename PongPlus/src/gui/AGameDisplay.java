package gui;

import java.beans.PropertyChangeListener;

import shapes.ACircle;
import shapes.ARectangle;
import shapes.Circle;
import shapes.Rectangle;

public class AGameDisplay implements GameDisplay{
	
	
	Rectangle playerOne, playerTwo;
	Circle ball;
	
	
	
	public AGameDisplay() {
		ball = new ACircle();
		playerOne = new ARectangle();
		playerTwo = new ARectangle();
		
		setUpGame();
	}

	@Override
	public Rectangle getPlayerOne() {
		return playerOne;
	}
	@Override
	public Rectangle getPlayerTwo() {
		return playerTwo;
	}
	@Override
	public Circle getBall() {
		return ball;
	}
	
	
	@Override
	public void setUpGame() {
		
	}
	
	
	
	
}
