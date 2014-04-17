import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Maze {
	mNode[][] maze;

	Tile[][] walls;

	boolean debug;

	int width;

	int depth;

	public final Point START;

	public final Point END;

	private AStarAlgorithm path;

	public Maze(int the_width, int the_depth, boolean the_debug) {

		width = the_width;

		depth = the_depth;

		debug = the_debug;

		maze = new mNode[width][depth];

		walls = new Tile[(2 * width) + 1][(2 * depth) + 1];

		START = new Point(0, 1);

		END = new Point(((width * 2) + 1) - 1, ((depth * 2) + 1) - 2);

		path = new AStarAlgorithm();

		fillGraph();
		setAdjacencies();
		depthFirstSearch();
		walls[START.x][START.y].setStr("  ");
		walls[END.x][END.y].setStr("  ");
		FindShortestPath();
	}

	private void FindShortestPath() {
		for (int i = 0; i < (2 * width) + 1; i++) {
			for (int j = 0; j < (2 * depth) + 1; j++) {
				if (i - 1 >= 0 && walls[i - 1][j].isPassable()) {
					walls[i][j].adjacent.add(walls[i - 1][j]);
				}
				if (j - 1 >= 0 && walls[i][j - 1].isPassable()) {
					walls[i][j].adjacent.add(walls[i][j - 1]);
				}
				if (i + 1 < ((width*2) + 1) && walls[i + 1][j].isPassable()) {
					walls[i][j].adjacent.add(walls[i + 1][j]);
				}
				if (j + 1 < ((depth*2) + 1) && walls[i][j + 1].isPassable()) {
					walls[i][j].adjacent.add(walls[i][j + 1]);
				}
			}
		}
		List<Tile> path_list = path.search(this, walls[0][1],
				walls[END.x][END.y]);
		System.out.println(path_list);

	}

	private void fillGraph() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < depth; j++) {
				mNode vertice = new mNode(i, j);
				if (i == START.y && j == START.x || i == END.y && j == END.x) {
					vertice.v = true;
				}
				maze[i][j] = vertice;
			}
		}
		for (int i = 0; i < (2 * width) + 1; i++) {
			for (int j = 0; j < (2 * depth) + 1; j++) {
				if (i % 2 == 0 || j % 2 == 0) {
					walls[i][j] = new Tile("X ", new Point(i, j));
				} else {
					walls[i][j] = new Tile("  ", new Point(i, j));
				}
			}
		}
	}

	private void setAdjacencies() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < depth; j++) {
				if (i - 1 >= 0) {
					maze[i][j].adjacent.add(maze[i - 1][j]);
				}
				if (j - 1 >= 0) {
					maze[i][j].adjacent.add(maze[i][j - 1]);
				}
				if (i + 1 < width) {
					maze[i][j].adjacent.add(maze[i + 1][j]);
				}
				if (j + 1 < depth) {
					maze[i][j].adjacent.add(maze[i][j + 1]);
				}
			}
		}
	}

	private void depthFirstSearch() {
		ArrayList<mNode> visited = new ArrayList<mNode>();
		Random r = new Random();
		Stack<mNode> the_stack = new Stack<mNode>();
		mNode current = maze[0][0];
		current.v = true;
		visited.add(current);
		while (thereAreOpenings()) {
			if (hasOpenNeighbor(current.adjacent)) {
				mNode open = current.adjacent.get(r.nextInt(current.adjacent
						.size()));
				while (visited.contains(open)) {
					open = current.adjacent.get(r.nextInt(current.adjacent
							.size()));
				}
				Point last_place = current.p;
				current = open;
				Point current_place = current.p;
				the_stack.push(current);
				visited.add(current);
				current.v = true;
				if (last_place.x != current_place.x) {
					walls[(((last_place.x * 2) + 1) + ((current_place.x * 2) + 1)) / 2][(current_place.y * 2) + 1]
							.setStr("  ");
				} else {
					walls[(current_place.x * 2) + 1][(((last_place.y * 2) + 1) + ((current_place.y * 2) + 1)) / 2]
							.setStr("  ");
				}
			} else if (!the_stack.isEmpty()) {
				current = the_stack.pop();
			} else {
				mNode open = maze[r.nextInt(width)][r.nextInt(depth)];
				while (visited.contains(open)) {
					open = maze[r.nextInt(width)][r.nextInt(depth)];
				}
				current = open;
				current.v = true;
				visited.add(open);

			}
		}
	}

	private boolean hasOpenNeighbor(ArrayList<mNode> the_neighbors) {
		for (mNode check : the_neighbors) {
			if (!check.v) {
				return true;
			}
		}
		return false;
	}

	private boolean thereAreOpenings() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < depth; j++) {
				if (!maze[i][j].v) {
					return true;
				}
			}
		}
		return false;
	}

	public double calcManhattanDistance(Tile a, Tile b) {
		return Math.abs(a.getLocation().getX() - b.getLocation().getX())
				+ Math.abs(a.getLocation().getY() - b.getLocation().getY());
	}

	public void display() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < (2 * width) + 1; i++) {
			for (int j = 0; j < (2 * depth) + 1; j++) {
				sb.append(walls[i][j].getStr());
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	public static void main(String[] args) {
		Maze test = new Maze(4, 4, false);
		test.display();
	}

	private class mNode {

		protected boolean v = false;

		protected Point p;

		protected ArrayList<mNode> adjacent;

		private mNode(int the_x, int the_y) {
			adjacent = new ArrayList<mNode>();

			p = new Point(the_x, the_y);
		}

	}
}
