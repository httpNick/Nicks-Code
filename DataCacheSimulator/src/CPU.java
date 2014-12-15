/**
 * 
 * @author (httpNick) Nick Duncan
 *
 */
public class CPU {
	
	public Cache l1i;
	public Cache l1d;
	public Cache l2;
	
	public CPU (Cache the_l1i, Cache the_l1d, Cache the_l2) {
		l1i = the_l1i;
		l1d = the_l1d;
		l2 = the_l2;
	}

	public Cache getL1i() {
		return l1i;
	}

	public Cache getL1d() {
		return l1d;
	}

	public Cache getL2() {
		return l2;
	}
}
