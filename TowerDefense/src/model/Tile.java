package model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tile {

	private Terrain my_terrain;

	private Point my_location;

	private boolean has_tower;

	private boolean buildable;

	private boolean passable;

	private ArrayList<Point> node_path;

	private ArrayList<Point> home_path;

	private String id;

	BufferedImage floor;

	public Tile(final Terrain the_terrain, final Point the_location,
			BufferedImage the_image) {
		my_terrain = the_terrain;

		my_location = the_location;

		has_tower = false;

		if (my_terrain == Terrain.GRASS) {
			buildable = true;
			passable = true;
		} else {
			buildable = false;
		}
		id = the_terrain + " " + my_location.toString();

		floor = the_image;
	}

	public BufferedImage getImage() {
		return floor;
	}

	public Terrain getTerrain() {
		return my_terrain;
	}

	public Point getLocation() {
		return (Point) my_location.clone();
	}

	public boolean hasTower() {
		return has_tower;
	}

	public void setTerrain(final Terrain new_terrain) {
		my_terrain = new_terrain;
	}

	public ArrayList<Point> getHome_path() {
		return home_path;
	}

	public void setHome_path(ArrayList<Point> home_path) {
		this.home_path = home_path;
	}

	public ArrayList<Point> getNode_path() {
		return node_path;
	}

	public void setNode_path(ArrayList<Point> node_path) {
		this.node_path = node_path;
	}

	public boolean isBuildable() {
		return buildable;
	}

	public void setBuildable(boolean buildable) {
		this.buildable = buildable;
	}

	public boolean isPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	public String getId() {
		return id;
	}

	public boolean isDiagonalNeighbor(Tile o) {
		boolean isDiagonal = false;
		if (getLocation().x - 1 == o.getLocation().x
				&& getLocation().y - 1 == o.getLocation().y) {
			isDiagonal = true;
		} else if (getLocation().x + 1 == o.getLocation().x
				&& getLocation().y - 1 == o.getLocation().y) {
			isDiagonal = true;
		} else if (getLocation().x + 1 == o.getLocation().x
				&& getLocation().y + 1 == o.getLocation().y) {
			isDiagonal = true;
		} else if (getLocation().x - 1 == o.getLocation().x
				&& getLocation().y + 1 == o.getLocation().y) {
			isDiagonal = true;
		}
		return isDiagonal;
	}

	@Override
	public String toString() {
		return "Tile [my_terrain=" + my_terrain + ", my_location="
				+ my_location + ", has_tower=" + has_tower + ", buildable="
				+ buildable + ", passable=" + passable + ", node_path="
				+ node_path + ", home_path=" + home_path + "]";
	}

}
