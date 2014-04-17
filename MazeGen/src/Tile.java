import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class Tile {

	private Point my_location;

	private String info;

	private String id;

	protected Set<Tile> adjacent;

	public Tile(String str, final Point the_location) {
		info = str;

		my_location = the_location;

		id = info + " " + my_location.toString();

		adjacent = new HashSet<Tile>();
	}

	public Point getLocation() {
		return (Point) my_location.clone();
	}

	public String getStr() {
		return info;
	}

	public void setStr(final String the_str) {
		info = the_str;
		id = info + " " + my_location.toString();
	}

	public boolean isPassable() {
		if (info.equals("X ")) {
			return false;
		}
		return true;
	}

	public String getId() {
		return id;
	}
	
	public String toString() {
		return id;
	}

}
