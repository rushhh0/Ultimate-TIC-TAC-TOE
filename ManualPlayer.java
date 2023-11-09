public class ManualPlayer extends Players {
	// Constructor for ManualPlayer
	public ManualPlayer(String name, String mark) {
		super(name, mark);
	}

	@Override
	// Check if the player is human
	public boolean isPlayerComputer() {
		return false;
	}
}
