package model;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class Tower {

	private static final int DEFAULT_FIRE_RANGE = 1;

	private int my_fire_range;

	private final Point my_location;

	private final int my_damage;

	private final int money_value;

	private final BufferedImage image;

	public Tower(final Point tower_location, final int the_damage,
			int the_money_value, BufferedImage the_image) {
		my_damage = the_damage;
		my_fire_range = DEFAULT_FIRE_RANGE;
		my_location = tower_location;
		money_value = the_money_value;
		image = the_image;
	}

	public Tower(final Point tower_location, final int the_damage,
			final int the_range, int the_money_value, BufferedImage the_image) {
		my_location = tower_location;
		my_damage = the_damage;
		my_fire_range = the_range;
		money_value = the_money_value;
		image = the_image;
	}

	public Point getLocation() {
		return my_location;
	}

	public int getFireRange() {
		return my_fire_range;
	}

	public int getTowerDamage() {
		return my_damage;
	}

	public boolean inTowerRange(final Point enemy_point) {
		boolean inRange = false;
		boolean inX = false;
		for (int i = 0; i <= my_fire_range; i++) {
			if (enemy_point.getX() == my_location.getX() + i
					|| enemy_point.getX() == my_location.getX() - i) {
				inX = true;
			}
		}
		if (inX == true) {
			for (int i = 0; i <= my_fire_range; i++) {
				if (enemy_point.getY() == my_location.getY() + i
						|| enemy_point.getY() == my_location.getY() - i) {
					inRange = true;
				}
			}
		}
		return inRange;
	}

	public int getMoneyValue() {
		return money_value;
	}

	public BufferedImage getImage() {
		return image;
	}

}
