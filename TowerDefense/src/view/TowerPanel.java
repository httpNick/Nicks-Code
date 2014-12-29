package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import model.Board;

public class TowerPanel extends JPanel {

	private final Dimension my_panel_dimension;

	private final Board my_board;

	public TowerPanel(int the_width, int the_height, Board the_board) {
		my_panel_dimension = new Dimension(the_width / 5, the_height);
		my_board = the_board;
		setup();

	}

	private void setup() {
		setPreferredSize(my_panel_dimension);
		setLayout(new FlowLayout());
	}
}
