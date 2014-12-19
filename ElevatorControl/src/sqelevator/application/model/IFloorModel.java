package sqelevator.application.model;

public interface IFloorModel {
	public int getFloorNumber();
	public int getButtonPressState();
	public boolean floorCommands(FloorCommands cmd, Object... params);
	public boolean isTarget();
}
