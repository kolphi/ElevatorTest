/*
	Adelakun Adetunji - S1210455001 
	Asnake Endalkachew - S1210455002 
	Vladimir Marinkovic - s1210455011 
*/
package sqelevator.application.model;

import java.util.ArrayList;
import java.util.HashMap;

public interface IElevatorModel {
	public int getElevatorNumber();
	public int getSpeed();
	public int getWeight();
	public int getCapacity();
	public int getAcceleration();
	public long getClockTick();
	public int getNextTargetFloor();
	public HashMap<Integer, Integer> getFollowingRequests();
	public int getDoorStatus();
	public int getFeetsPosition();
	public int getNearestFloor();
	public int getCommittedDirection();
	public ArrayList<IFloorModel> getFloors();
	public int getTotalFloorsNumber();
	public boolean elevatorCommands(ElevatorCommands cmd, Object... params);
	public int getElevatorFloorNumber();
	
}
