package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import java.util.Set;

import javax.swing.Timer;

import view.GUI;

/* Summer project game of Tower Defense.
 */

/**
 * Board representation of Tower Defense.
 * 
 * @author Nick Duncan (httpNick), (Nick.b.duncan@gmail.com)
 * @version Jun 22, 2013
 */
public class Board extends Observable {
	/** Width of the board. */
	private static final int DEFAULT_WIDTH = 20;

	/** Height of the board. */
	private static final int DEFAULT_HEIGHT = 10;

	private static final int NUMBER_OF_ENEMIES = 20;

	/** Horizontal wall string. */
	private static final String HORIZONTAL = "-";

	/** Vertical wall string. */
	private static final String VERTICAL = "|";

	/** String for a ling. */
	private static final String ENEMY = "X";

	/** String for an empty space. */
	private static final String EMPTY = " ";

	/** String for central node. */
	private static final String CENTER_NODE = "N";

	/** String for player house. */
	private static final String PLAYER_HOUSE = "H";

	// added the next two constants
	/** String for Player cursor. */
	private static final String PLAYER_CURSOR = "C";

	/** String for towers. */
	private static final String TOWER = "T";

	/** Random object used to create random Y coordinates. */
	private Random random_Y_coord;

	/** Y coordinate for randomly generated opening for enemies. */
	private int random_opening;

	private final Point NODE_LOCATION = new Point((DEFAULT_WIDTH / 2) - 1,
			(DEFAULT_HEIGHT / 2) - 1);
	private final Point HOUSE_LOCATION = new Point(DEFAULT_WIDTH - 1,
			DEFAULT_HEIGHT - 1);

	// Added to keep track of where towers are being placed.
	/** Cursor location. Cursor indicates place where next tower will be placed */
	private final Point my_cursor_location = new Point(NODE_LOCATION.x,
			NODE_LOCATION.y);

	private final ArrayList<Tower> my_towers;

	/** Array List to hold all of the enemies for this level. */
	private final ArrayList<Enemy> my_enemy_list;

	/** Array List to hold all current enemies on the board. */
	private final ArrayList<Enemy> my_current_enemy_list;

	/** 2D array of the board's points. */
	private final Tile[][] board_points;

	private Timer my_timer;

	private boolean gameGoingOn;

	private Map<String, Set<Tile>> adjacency;

	private AStarAlgorithm path_finder;

	private int money;

	private int level;

	private int lives;

	private int enemies_left;

	public ArrayList<Point> wall_array;

	// private Map<Point, Path> level_one;

	public Board() {
		money = 50;
		level = 1;
		lives = 25;
		enemies_left = NUMBER_OF_ENEMIES;
		my_enemy_list = new ArrayList<Enemy>();
		my_current_enemy_list = new ArrayList<Enemy>();
		random_Y_coord = new Random();
		random_opening = random_Y_coord.nextInt(DEFAULT_HEIGHT);
		board_points = new Tile[DEFAULT_WIDTH][DEFAULT_HEIGHT];
		my_towers = new ArrayList<Tower>();
		adjacency = new HashMap<String, Set<Tile>>();
		path_finder = new AStarAlgorithm();
		wall_array = new ArrayList<Point>();
		initializeEnemies();
		fillBoardPoints("firstlevel.xml");
		setNeighbors();

	}

	private void initializeEnemies() {
		for (int i = 0; i < NUMBER_OF_ENEMIES; i++) {
			my_enemy_list.add(new Enemy(new Point(0, 0), 5));
		}
		gameGoingOn = true;
	}

	private void fillBoardPoints(final String file_name) {
		Point p = null;
		for (int i = 0; i < DEFAULT_WIDTH; i++) {
			for (int j = 0; j < DEFAULT_HEIGHT; j++) {
				p = new Point(i, j);

				if (j == 1 && i != DEFAULT_WIDTH - 1) {
					board_points[i][j] = new Tile(Terrain.GRASS, p);
					board_points[i][j].setBuildable(false);
					board_points[i][j].setPassable(false);
					wall_array.add(p);
				} else {
					board_points[i][j] = new Tile(Terrain.GRASS, p);
				}
			}
		}
	}

	public double calcManhattanDistance(Tile a, Tile b) {
		return Math.abs(a.getLocation().getX() - b.getLocation().getX())
				+ Math.abs(a.getLocation().getY() - b.getLocation().getY());
	}

	private void setNeighbors() {
		for (int x = 0; x < DEFAULT_WIDTH; x++) {
			for (int y = 0; y < DEFAULT_HEIGHT; y++) {
				Tile t = board_points[x][y];
				Set<Tile> temp_neighbors = new HashSet<Tile>();
				for (int ox = 0; ox < DEFAULT_WIDTH; ox++) {
					for (int oy = 0; oy < DEFAULT_HEIGHT; oy++) {
						Tile o = board_points[ox][oy];
						if (t.getId() != o.getId() && o.isPassable()) {
							if (t.getLocation().x + 1 == o.getLocation().x
									&& t.getLocation().y == o.getLocation().y) {
								temp_neighbors.add(o);
							} else if (t.getLocation().x - 1 == o.getLocation().x
									&& t.getLocation().y == o.getLocation().y) {
								temp_neighbors.add(o);
							} else if (t.getLocation().y + 1 == o.getLocation().y
									&& t.getLocation().x == o.getLocation().x) {
								temp_neighbors.add(o);
							} else if (t.getLocation().y - 1 == o.getLocation().y
									&& t.getLocation().x == o.getLocation().x) {
								temp_neighbors.add(o);
							} else if (t.getLocation().x - 1 == o.getLocation().x
									&& t.getLocation().y - 1 == o.getLocation().y
									&& board_points[t.getLocation().x - 1][t
											.getLocation().y].isPassable()
									&& board_points[t.getLocation().x][t
											.getLocation().y - 1].isPassable()) {
								temp_neighbors.add(o);
							} else if (t.getLocation().x + 1 == o.getLocation().x
									&& t.getLocation().y - 1 == o.getLocation().y
									&& board_points[t.getLocation().x + 1][t
											.getLocation().y].isPassable()
									&& board_points[t.getLocation().x][t
											.getLocation().y - 1].isPassable()) {
								temp_neighbors.add(o);
							} else if (t.getLocation().x + 1 == o.getLocation().x
									&& t.getLocation().y + 1 == o.getLocation().y
									&& board_points[t.getLocation().x + 1][t
											.getLocation().y].isPassable()
									&& board_points[t.getLocation().x][t
											.getLocation().y + 1].isPassable()) {
								temp_neighbors.add(o);
							} else if (t.getLocation().x - 1 == o.getLocation().x
									&& t.getLocation().y + 1 == o.getLocation().y
									&& board_points[t.getLocation().x - 1][t
											.getLocation().y].isPassable()
									&& board_points[t.getLocation().x][t
											.getLocation().y + 1].isPassable()) {
								temp_neighbors.add(o);
							}
						}
					}
					adjacency.put(t.getId(), temp_neighbors);
				}
			}
		}
	}

	private void chooseShortestMoveToNode(final Enemy e) {
		List<Tile> path_list = path_finder.search(this,
				board_points[e.getLocation().x][e.getLocation().y],
				board_points[NODE_LOCATION.x][NODE_LOCATION.y]);
		if (path_list == null) {
			removeTower();
			path_list = path_finder.search(this,
					board_points[e.getLocation().x][e.getLocation().y],
					board_points[NODE_LOCATION.x][NODE_LOCATION.y]);
		} else {
			e.moveTo(path_list.get(1).getLocation().x, path_list.get(1)
					.getLocation().y);
			// added this so enemies know where they are going next
			if (path_list.size() >= 3) {
				e.setNextLocation(path_list.get(2).getLocation());
			} else {
				List<Tile> future_path_list = path_finder.search(this,
						board_points[NODE_LOCATION.x][NODE_LOCATION.y],
						board_points[HOUSE_LOCATION.x][HOUSE_LOCATION.y]);
				e.setNextLocation(future_path_list.get(1).getLocation());
			}
		}
	}

	private void chooseShortestMoveToHome(final Enemy e) {
		List<Tile> path_list = path_finder.search(this,
				board_points[e.getLocation().x][e.getLocation().y],
				board_points[HOUSE_LOCATION.x][HOUSE_LOCATION.y]);
		if (path_list == null) {
			removeTower();
			path_list = path_finder.search(this,
					board_points[e.getLocation().x][e.getLocation().y],
					board_points[HOUSE_LOCATION.x][HOUSE_LOCATION.y]);
		} else {
			e.moveTo(path_list.get(1).getLocation().x, path_list.get(1)
					.getLocation().y);
			if (path_list.size() >= 3) {
				e.setNextLocation(path_list.get(2).getLocation());
			}
		}
	}

	public void startGame() {
		my_timer.start();
	}

	public boolean isGameGoingOn() {
		return gameGoingOn;
	}

	public Set<Tile> getNeighbors(String the_id) {
		return adjacency.get(the_id);
	}

	private void damageEnemies() {
		boolean has_fired = false;
		for (Tower t : my_towers) {
			for (Enemy e : my_current_enemy_list) {
				if (!has_fired) {
					if (t.inTowerRange(e.getLocation())) {
						has_fired = true;
						e.takeDamage(t.getTowerDamage());
					}
				}
			}
			has_fired = false;
		}
	}

	public void step() {
		if (!my_enemy_list.isEmpty()) {
			my_current_enemy_list
					.add(my_enemy_list.get(my_enemy_list.size() - 1));
			my_enemy_list.remove(my_enemy_list.size() - 1);
		}
		if (my_current_enemy_list.isEmpty()) {
			gameGoingOn = false;
		}
		for (int i = 0; i < my_current_enemy_list.size(); i++) {
			Enemy e = my_current_enemy_list.get(i);
			if (e.getLocation().equals(NODE_LOCATION)) {
				e.setPassedNode();
			}
			if (e.passedNode()) {
				if (e.getLocation().equals(HOUSE_LOCATION)) {
					my_current_enemy_list.remove(i);
					lives = lives - 1;
					enemies_left = enemies_left - 1;
					setChanged();
					notifyObservers(true);
				} else {
					chooseShortestMoveToHome(e);
				}
			} else {
				chooseShortestMoveToNode(e);
			}
			damageEnemies();
			deleteDeadEnemies();
		}
	}

	private void deleteDeadEnemies() {
		for (int i = 0; i < my_current_enemy_list.size(); i++) {
			if (my_current_enemy_list.get(i).takeDamage(0)) {
				money = money + my_current_enemy_list.get(i).getMoneyValue();
				enemies_left = enemies_left - 1;
				my_current_enemy_list.remove(i);
			}
		}
		setChanged();
		notifyObservers(true);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();

		for (int i = 0; i < DEFAULT_WIDTH; i++) {
			sb.append(HORIZONTAL);
		}
		for (int row = 0; row < DEFAULT_HEIGHT; row++) {
			sb.append('\n');
			if (row != random_opening) {
				sb.append(VERTICAL);
			} else {
				sb.append(EMPTY);
			}
			sb.append(getRowString(row));
			sb.append(VERTICAL);

		}
		sb.append('\n');
		for (int i = 0; i < DEFAULT_WIDTH; i++) {
			sb.append(HORIZONTAL);
		}
		return sb.toString();

	}

	private String getRowString(final int row) {
		final StringBuilder sb = new StringBuilder();
		Point current_point = new Point();
		for (int column = 0; column < DEFAULT_WIDTH; column++) {
			current_point.setLocation(column, row);
			if (current_point.equals(my_cursor_location)) {// added this
				// condition for
				// drawing cursor
				sb.append(PLAYER_CURSOR);
			} else if (towerLocation(current_point)) {// added this condition
				// for drawing towers
				sb.append(TOWER);
			} else if (current_point.equals(NODE_LOCATION)) {
				sb.append(CENTER_NODE);
			} else if (enemyLocation(current_point)) {
				sb.append(ENEMY);
			} else if (HOUSE_LOCATION.equals(current_point)) {
				sb.append(PLAYER_HOUSE);
			} else {
				sb.append(EMPTY);

			}
		}
		return sb.toString();
	}

	/*
	 * Added this method
	 */
	private boolean towerLocation(final Point current_point) {
		for (int i = 0; i < my_towers.size(); i++) {
			if (my_towers.get(i).getLocation().equals(current_point)) {
				return true;
			}
		}
		return false;
	}

	private boolean enemyLocation(final Point current_point) {
		for (Enemy e : my_current_enemy_list) {
			if (e.getLocation().equals(current_point)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Added the following four methods for changing the cursors location.
	 */
	public void moveUp() {
		my_cursor_location.y--;
	}

	public void moveDown() {
		my_cursor_location.y++;
	}

	public void moveLeft() {
		my_cursor_location.x--;
	}

	public void moveRight() {
		my_cursor_location.x++;
	}

	public void placeTower(final int the_x, final int the_y) {
		Tower t = new Tower(new Point(the_x, the_y), 5, 5);
		if (!(NODE_LOCATION.x == the_x && NODE_LOCATION.y == the_y)
				&& !(HOUSE_LOCATION.x == the_x && HOUSE_LOCATION.y == the_y)
				&& board_points[the_x][the_y].isBuildable()
				&& !(the_x == 0 && the_y == random_opening)
				&& money >= t.getMoneyValue()) {
			my_towers.add(t);
			Tile location = board_points[the_x][the_y];
			location.setBuildable(false);
			location.setPassable(false);
			money = money - t.getMoneyValue();
			setNeighbors();
			setChanged();
			notifyObservers(false);
		}
	}

	protected void removeTower() {
		Tower t = my_towers.get(my_towers.size() - 1);
		money = money + t.getMoneyValue();
		board_points[t.getLocation().x][t.getLocation().y].setPassable(true);
		board_points[t.getLocation().x][t.getLocation().y].setBuildable(true);
		setNeighbors();
		my_towers.remove(t);
	}

	public ArrayList<Tower> getTowers() {
		return (ArrayList<Tower>) my_towers.clone();
	}

	public ArrayList<Enemy> getEnemyList() {
		return (ArrayList<Enemy>) my_current_enemy_list.clone();
	}

	public Point getNodeLocation() {
		return (Point) NODE_LOCATION.clone();
	}

	public Point getCursorLocation() {
		return (Point) my_cursor_location.clone();
	}

	public Point getHouseLocation() {
		return (Point) HOUSE_LOCATION.clone();
	}

	public void placeCursor(final int the_x, final int the_y) {
		if (the_x < DEFAULT_WIDTH && the_y < DEFAULT_HEIGHT) {
			my_cursor_location.x = the_x;
			my_cursor_location.y = the_y;
			setChanged();
			notifyObservers(false);
		}
	}

	public int getMoney() {
		return money;
	}

	public int getEnemiesLeft() {
		return enemies_left;
	}

	public int getLevel() {
		return level;
	}

	public int getLives() {
		return lives;
	}

}
