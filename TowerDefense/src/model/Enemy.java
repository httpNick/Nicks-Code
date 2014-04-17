package model;

import java.awt.Point;

public class Enemy {

	private static final int DEFAULT_HEALTH = 50;

	private static final int DEFAULT_SPEED = 1;

	private int my_health;

	private int my_speed;

	private Point my_location;

	private Point next_location;

	private boolean passed_node;
	
	private int money_value;

	public Enemy(final Point the_start, int the_money_value) {
		if (the_start != null) {
			my_health = DEFAULT_HEALTH;
			my_speed = DEFAULT_SPEED;
			passed_node = false;
			my_location = the_start;
			money_value = the_money_value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public Enemy(final Point start, final int the_health, final int the_speed) {
		if (the_health < DEFAULT_HEALTH || the_speed < DEFAULT_SPEED
				|| start == null) {
			throw new IllegalArgumentException();
		} else {
			my_speed = the_speed;
			my_health = the_health;
			my_location = start;
		}
	}

	/*
	 * True if unit died. False otherwise.
	 */
	public boolean takeDamage(final int the_damage) {
		if (the_damage < 0) {
			throw new IllegalArgumentException();
		}

		boolean dead;
		my_health -= the_damage;
		if (my_health <= 0) {
			dead = true;
		} else {
			dead = false;
		}

		return dead;
	}

	public Point getLocation() {
		return (Point) my_location.clone();
	}

	public int getX() {
		return (int) my_location.getX();
	}

	public int getY() {
		return (int) my_location.getY();
	}

	public int getSpeed() {
		return my_speed;
	}

	public boolean passedNode() {
		return passed_node;
	}

	public void setPassedNode() {
		passed_node = true;
	}

	public void moveTo(final int the_x, final int the_y) {
		my_location.x = the_x;
		my_location.y = the_y;
	}
	public int getMoneyValue() {
		return money_value;
	}

	/**
	 * Needs to be overwritten for different enemies.
	 */
	public boolean canPass(Terrain the_terrain) {
		if (the_terrain == Terrain.GRASS) {
			return true;
		} else {
			return false;
		}
	}

	public void setNextLocation(final Point the_next_location) {
		next_location = the_next_location;
	}

	public Point getNextLocation() {
			return (Point) next_location.clone();
	}
}
