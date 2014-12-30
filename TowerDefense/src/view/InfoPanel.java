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

	private final int lives;

	private final int level;

	private final JLabel my_label;

	private final int income;

	public InfoPanel(final int the_width, final int the_height,
			final int the_money, final int lives, final int level,
			final int the_income, final Board the_board) {
		money = the_money;
		this.lives = lives;
		this.level = level;
		income = the_income;
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
		income = 0;
		my_label = setupLabel();
		setup();
	}

	private JLabel setupLabel() {
		StringBuilder buff = new StringBuilder();
		buff.append("<html> money = " + money + " <br />");
		buff.append("income = " + income + " <br />");
		buff.append("lives left = " + lives + "<br />");
		buff.append("level " + level + "<br />");

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
			int the_income) {
		StringBuilder buff = new StringBuilder();
		buff.append("<html> money = " + the_money + " <br />");
		buff.append("income = " + the_income + " <br />");
		buff.append("lives left = " + the_lives + "<br />");
		buff.append("level " + the_level + "<br />");
		my_label.setText(buff.toString());

	}
}
