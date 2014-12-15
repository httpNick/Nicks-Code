/**
 * 
 * @author (httpNick) Nick Duncan
 *
 */
public class CacheEntry {
	int tag;
	MesiState mesi;
	int address;
	int wv;

	public CacheEntry(int the_tag, int the_address,
			int the_write_value) {
		tag = the_tag;
		address = the_address;
		wv = the_write_value;
		mesi = MesiState.EXCLUSIVE;
	}

	public int getTag() {
		return tag;
	}

	public MesiState getState() {
		return mesi;
	}

	public String toString() {
		return "tag = " + Integer.toHexString(tag) + " MesiState = " + mesi;

	}
}
