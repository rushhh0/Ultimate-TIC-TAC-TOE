//abstract class pf Players
public abstract class Players {
	// Private members, name and park
	private String name;
	private String mark;

	// Constructor
	public Players(String nname, String mmark) {
//		this.setName(name);
//		this.setMark(mark);
		name = nname;
		mark = mmark;
	}

	// Set method
	public void setName(String nname) {
		this.name = nname;
	}

	// Get method
	public String getName() {
		return name;
	}

	// Set method
	public void setMark(String mmark) {
		this.mark = mmark;
	}

	// Get method
	public String getMark() {
		return mark;
	}

	// Get row value from cell number
	public int pickRowContent(int maxRow, int minRow, int cellNumberOfEachCellBoard) {
		if (cellNumberOfEachCellBoard <= 2) {
			return minRow;
		} else if (cellNumberOfEachCellBoard <= 5) {
			return minRow + 1;
		}
		return maxRow;
	}

	// Get col value from cell number
	public int pickColContent(int maxCol, int minCol, int cellNumberOfEachCellBoard) {
		if (cellNumberOfEachCellBoard == 0 || cellNumberOfEachCellBoard == 3 || cellNumberOfEachCellBoard == 6) {
			return minCol;
		} else if (cellNumberOfEachCellBoard == 1 || cellNumberOfEachCellBoard == 4 || cellNumberOfEachCellBoard == 7) {
			return minCol + 1;
		}
		return maxCol;
	}

	// Prototype
	public abstract boolean isPlayerComputer();
}
