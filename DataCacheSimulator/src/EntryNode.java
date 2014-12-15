/**
 * 
 * @author (httpNick) Nick Duncan
 *
 */
public class EntryNode {

	int ia;
	int writeValue;
	int dataAddress;

	public EntryNode(int insturctionaddress, int writeValue, int dataAddress) {
		this.ia = insturctionaddress;
		this.writeValue = writeValue;
		this.dataAddress = dataAddress;
	}

	public EntryNode(int instructionaddress) {
		ia = instructionaddress;
		writeValue = -1;
		dataAddress = -1;
	}

}