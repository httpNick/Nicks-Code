import java.util.Comparator;

public class AStarTileComparator implements Comparator<AStarTile> {

	public int compare(AStarTile first, AStarTile second) {
		if (first.getF() < second.getF()) {
			return -1;
		} else if (first.getF() > second.getF()) {
			return 1;

		} else {
			return 0;
		}
	}

}