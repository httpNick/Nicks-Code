package model;

import java.awt.Point;

public class House {
	private int my_lives;

	private Point my_location;

	public House(final Point the_location, final int the_lives) {
		my_lives = the_lives;

		my_location = the_location;

	}

	public int getLives() {
		return my_lives;
	}

	public void setlives(int my_lives) {
		this.my_lives = my_lives;
	}
	public void removeALife() {
		my_lives = my_lives - 1;
	}

	public Point get_location() {
		return my_location;
	}
}
