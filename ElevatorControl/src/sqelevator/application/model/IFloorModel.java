/*
	Adelakun Adetunji - S1210455001 
	Asnake Endalkachew - S1210455002 
	Vladimir Marinkovic - s1210455011 
*/
package sqelevator.application.model;

public interface IFloorModel {
	public int getFloorNumber();
	public int getDirection();
	public int getFeetsPosition();
	public boolean floorCommands(FloorCommands cmd, Object... params);
}
