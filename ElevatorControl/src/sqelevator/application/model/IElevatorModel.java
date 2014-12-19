package sqelevator.application.model;

import java.util.ArrayList;
import java.util.HashMap;

public interface IElevatorModel {
	public int getElevatorNumber();
	public int getSpeed();
	public int getWeight();
	public int getCapacity();
	public int getNextTargetFloor();
	public HashMap<Integer, Integer> getFollowingRequests();
	public int getDoorStatus();
	public int getCommittedDirection();
	public ArrayList<IFloorModel> getFloors();
	public int getTotalFloorsNumber();
	public boolean elevatorCommands(ElevatorCommands cmd, Object... params);
	public int getElevatorFloorNumber();	
}
