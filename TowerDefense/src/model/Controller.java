package model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {

	private final Board my_board;

	public Controller(final Board the_board) {
		my_board = the_board;
	}

	@Override
	public void keyPressed(final KeyEvent the_key) {

		switch (the_key.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			my_board.moveRight();
			break;
		case KeyEvent.VK_LEFT:
			my_board.moveLeft();
			break;
		case KeyEvent.VK_DOWN:
			my_board.moveDown();
			break;
		case KeyEvent.VK_UP:
			my_board.moveUp();
			break;
		default:
			break;
		}
	}
}
