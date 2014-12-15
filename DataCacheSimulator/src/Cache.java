/**
 * 
 * @author (httpNick) Nick Duncan
 *
 */
public class Cache
{

	int size;
	int latency;
	CacheEntry[] entries;
	int number_of_offset_bits;
	int number_of_sets;
	int number_of_index_bits;
	int index_mask;

	public Cache(int the_size, int the_latency)
	{
		size = the_size;
		latency = the_latency;
		entries = new CacheEntry[the_size];
		number_of_offset_bits = (int) (Math.log(gui.CACHE_LINE_SIZE) / Math
				.log(2));
		number_of_sets = the_size / gui.NUMBER_OF_WAYS;
		number_of_index_bits = (int) (Math.log(number_of_sets) / Math.log(2));
		index_mask = ((1 << number_of_index_bits) - 1);

	}

	public void addEntry(CacheEntry entry, int real_index)
	{
		entries[real_index] = entry;
	}

	public int getOffset(int address)
	{
		return address & ((1 << number_of_offset_bits) - 1);
	}

	public int getIndex(int address)
	{
		return (address >>> number_of_offset_bits) & (index_mask);
	}

	public int getTag(int address)
	{
		return (address >>> (number_of_offset_bits + number_of_index_bits));
	}
	
}
