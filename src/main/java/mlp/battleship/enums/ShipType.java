package mlp.battleship.enums;

public enum ShipType {
	BATTLESHIP,
	SUBMARINE,
	MINE;
	
	@Override
	public String toString() {
		return super.toString().substring(0, 1).toUpperCase() 
				+ super.toString().substring(1).toLowerCase();
	}
}
