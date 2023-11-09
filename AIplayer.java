public class AIplayer extends Players {
	// Constructor for AIPlayer
	public AIplayer(String name, String mark) {
		super(name, mark);
	}

	@Override
	// Check if the player is computer
	public boolean isPlayerComputer() {
		return true;
	}
}