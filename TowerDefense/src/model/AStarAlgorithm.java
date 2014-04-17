package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class AStarAlgorithm {
	public List<Tile> search(Board board, Tile source, Tile target) {
		Map<String, AStarTile> openSet = new HashMap<String, AStarTile>();
		PriorityQueue<AStarTile> pQueue = new PriorityQueue(20,
				new AStarTileComparator());
		Map<String, AStarTile> closeSet = new HashMap<String, AStarTile>();
		AStarTile start = new AStarTile(source, 0, board.calcManhattanDistance(
				source, target));
		openSet.put(source.getId(), start);
		pQueue.add(start);

		AStarTile goal = null;
		while (openSet.size() > 0) {
			AStarTile x = pQueue.poll();
			openSet.remove(x.getId());
			if (x.getId().equals(target.getId())) {
				goal = x;
				break;
			} else {
				closeSet.put(x.getId(), x);
				Set<Tile> neighbors = board.getNeighbors(x.getId());
				for (Tile neighbor : neighbors) {
					AStarTile visited = closeSet.get(neighbor.getId());
					if (visited == null) {
						double g = 0;
						if (!neighbor.isDiagonalNeighbor(x.getTile())) {
							g = x.getG()
									+ board.calcManhattanDistance(x.getTile(),
											neighbor);
						} else {
							g = x.getG()
									+ board.calcManhattanDistance(x.getTile(),
											neighbor) - 0.6;
						}
						AStarTile t = openSet.get(neighbor.getId());

						if (t == null) {
							t = new AStarTile(neighbor, g,
									board.calcManhattanDistance(neighbor,
											target));
							t.setCameFrom(x);
							openSet.put(neighbor.getId(), t);
							pQueue.add(t);
						} else if (g < t.getG()) {
							t.setCameFrom(x);
							t.setG(g);
							t.setH(board
									.calcManhattanDistance(neighbor, target));
						}
					}
				}
			}
		}
		if (goal != null) {
			Stack<Tile> stack = new Stack<Tile>();
			List<Tile> list = new ArrayList<Tile>();
			stack.push(goal.getTile());
			AStarTile parent = goal.getCameFrom();
			while (parent != null) {
				stack.push(parent.getTile());
				parent = parent.getCameFrom();
			}
			while (stack.size() > 0) {
				list.add(stack.pop());
			}
			return list;
		}
		return null;
	}
}
