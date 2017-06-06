package mlp.battleship.enums;

public enum PositionStatus {
	WATER("~"),
	MISS("*"),
	HIT("X"),
	SHIP("V");
	
	private String gridCode;
	
	private PositionStatus(String gridCode) {
		this.gridCode = gridCode;
	}

	public String getGridCode() {
		return gridCode;
	}

	public void setGridCode(String gridCode) {
		this.gridCode = gridCode;
	}
}
