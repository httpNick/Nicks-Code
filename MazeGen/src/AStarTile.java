
public class AStarTile {

	private Tile tile;

	private AStarTile cameFrom;

	private double g;

	private double h;

	public AStarTile(Tile the_tile, double the_g, double the_h) {

		tile = the_tile;

		setG(the_g);

		setH(the_h);
	}

	public void setCameFrom(AStarTile the_cameFrom) {
		cameFrom = the_cameFrom;
	}

	public AStarTile getCameFrom() {
		return cameFrom;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}

	public double getF() {
		return g + h;
	}

	public String getId() {
		return tile.getId();
	}

	public Tile getTile() {
		return tile;
	}

}