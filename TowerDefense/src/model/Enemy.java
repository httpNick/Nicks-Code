package model;

import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JProgressBar;

public class Enemy {

	private static final int DEFAULT_HEALTH = 50;

	private static final int DEFAULT_SPEED = 1;

	private int my_health;

	private int my_speed;

	private Point my_location;

	private Point next_location;

	private boolean passed_node;

	private int kill_value;

	private int income_value;

	private BufferedImage image;

	JProgressBar hp;

	private int cost;

	public Enemy(final Point the_start, int the_health, int the_kill_value,
			int the_income_value, int the_cost, BufferedImage the_image) {
		if (the_start != null) {
			my_health = the_health;
			my_speed = DEFAULT_SPEED;
			passed_node = false;
			my_location = the_start;
			kill_value = the_kill_value;
			income_value = the_income_value;
			cost = the_cost;
			JProgressBar hp = new JProgressBar(0, my_health);
			image = the_image;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public Enemy(final Point start, final int the_health, final int the_speed,
			BufferedImage the_image) {
		if (the_health < DEFAULT_HEALTH || the_speed < DEFAULT_SPEED
				|| start == null) {
			throw new IllegalArgumentException();
		} else {
			my_speed = the_speed;
			my_health = the_health;
			my_location = start;
			image = the_image;
			JProgressBar hp = new JProgressBar(0, my_health);
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

	public JProgressBar getHPBar() {
		return hp;
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

	public int getHP() {
		return my_health;
	}

	public int getSpeed() {
		return my_speed;
	}

	public boolean passedNode() {
		return passed_node;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setPassedNode() {
		passed_node = true;
	}

	public void moveTo(final int the_x, final int the_y) {
		my_location.x = the_x;
		my_location.y = the_y;
	}

	public int getKillValue() {
		return kill_value;
	}

	public int getCost() {
		return cost;
	}

	public int getIncomeValue() {
		return income_value;
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
