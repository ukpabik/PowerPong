package view;

import gui.GameDisplay;

public interface DelegatingPongView {
	public void addViewListeners(APongPainter painter, GameDisplay game);
}
