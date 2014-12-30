package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Board;

public class InfoPanel extends JPanel {

	private final int money;

	private final Dimension my_panel_dimension;

	private final int lives;

	private final int level;

	private final int enemies_left;

	private final Board my_board;

	private final JLabel my_label;

	public InfoPanel(final int the_width, final int the_height,
			final int the_money, final int lives, final int level,
			final int enemies_left, final Board the_board) {
		money = the_money;
		this.lives = lives;
		this.level = level;
		this.enemies_left = enemies_left;
		my_board = the_board;
		my_panel_dimension = new Dimension(the_width, the_height);
		my_label = setupLabel();
		setup();
	}

	/**
	 * @wbp.parser.constructor
	 */
	public InfoPanel(int the_width, int the_height, Board the_board) {
		money = 0;
		lives = 0;
		level = 0;
		enemies_left = 0;
		my_panel_dimension = new Dimension(the_width / 5, the_height);
		my_board = the_board;
		my_label = setupLabel();
		setup();
	}

	private JLabel setupLabel() {
		StringBuilder buff = new StringBuilder();
		buff.append("<html> money = " + money + " <br />");
		buff.append("lives left = " + lives + "<br />");
		buff.append("level " + level + "<br />");
		buff.append("enemies left = " + enemies_left + "<br />");

		JLabel label = new JLabel();
		label.setText(buff.toString());
		return label;
	}

	private void setup() {
		setPreferredSize(new Dimension(116, 10));
		setLayout(new BorderLayout());
		add(my_label, BorderLayout.CENTER);
	}

	protected void changeLabelText(int the_money, int the_level, int the_lives,
			int the_enemies_left) {
		StringBuilder buff = new StringBuilder();
		buff.append("<html> money = " + the_money + " <br />");
		buff.append("lives left = " + the_lives + "<br />");
		buff.append("level " + the_level + "<br />");
		buff.append("enemies left = " + the_enemies_left + "<br />");
		my_label.setText(buff.toString());

	}
}
