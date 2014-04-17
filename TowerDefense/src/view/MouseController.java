package view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Board;

public class MouseController extends MouseAdapter {
	
	private final Board my_board;
	
	private final int square_size;
	
	public MouseController(final Board the_board, final int the_square_size) {
		my_board = the_board;
		square_size = the_square_size;
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
		super.mouseClicked(e);
		Point p = e.getPoint();
		my_board.placeTower(p.x / square_size, p.y / square_size - 1);
	}
	
	@Override
	public void mouseMoved(final MouseEvent e) {
		Point p = e.getPoint();
		my_board.placeCursor(p.x / square_size, p.y / square_size - 1);
	}
	
	
	
}
