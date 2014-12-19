/*
	Adelakun Adetunji - S1210455001 
	Asnake Endalkachew - S1210455002 
	Vladimir Marinkovic - s1210455011 
*/

package sqelevator.application.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import sqelevator.IElevator;
import sqelevator.application.service.ElevatorAdapter;


public class ElevatorModel implements IElevatorModel{
	private int elevatorNumber;
	private int speed;
	private int weight;
	private int capacity;
	private int acceleration;
	private long clockTick;
	private int nextTargetFloor;
	private HashMap<Integer, Integer> followingRequests = new HashMap<Integer, Integer>();
	private int doorStatus;
	private int feetsPosition;
	private int nearestFloor;
	private int committedDirection;
	private int elevatorFloorNumber;
	

	/**
	 * @return the elevatorFloorNumber
	 */
	public int getElevatorFloorNumber() {
		return elevatorFloorNumber;
	}

	/**
	 * @param elevatorFloorNumber the elevatorFloorNumber to set
	 */
	public void setElevatorFloorNumber(int elevatorFloorNumber) {
		this.elevatorFloorNumber = elevatorFloorNumber;
	}

	// floors info
	private ArrayList<IFloorModel> floors = new ArrayList<IFloorModel>();
	private int totalFloorsNumber;

	@Override
	public int getElevatorNumber() {
		return elevatorNumber;
	}

	public void setElevatorNumber(int elevatorNumber) {
		this.elevatorNumber = elevatorNumber;
	}
	
	@Override
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public int getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(int acceleration) {
		this.acceleration = acceleration;
	}

	@Override
	public long getClockTick() {
		return clockTick;
	}

	public void setClockTick(long clockTick) {
		this.clockTick = clockTick;
	}

	@Override
	public int getNextTargetFloor() {
		return nextTargetFloor;
	}

	public void setNextTargetFloor(int nextTargetFloor) {
		this.nextTargetFloor = nextTargetFloor;
	}

	@Override
	public HashMap<Integer, Integer> getFollowingRequests() {
		return followingRequests;
	}

	public void setFollowingRequests(HashMap<Integer, Integer> followingRequests) {
		this.followingRequests = followingRequests;
	}

	@Override
	public int getDoorStatus() {
		return doorStatus;
	}

	public void setDoorStatus(int doorStatus) {
		this.doorStatus = doorStatus;
	}

	@Override
	public int getFeetsPosition() {
		return feetsPosition;
	}

	public void setFeetsPosition(int feetsPosition) {
		this.feetsPosition = feetsPosition;
	}

	@Override
	public int getNearestFloor() {
		return nearestFloor;
	}

	public void setNearestFloor(int nearestFloor) {
		this.nearestFloor = nearestFloor;
	}

	@Override
	public int getCommittedDirection() {
		return committedDirection;
	}

	public void setCommittedDirection(int committedDirection) {
		this.committedDirection = committedDirection;
	}

	@Override
	public ArrayList<IFloorModel> getFloors() {
		return floors;
	}

	public void setFloors(ArrayList<IFloorModel> floors) {
		this.floors = floors;
	}
	
	@Override
	public int getTotalFloorsNumber() {
		return totalFloorsNumber;
	}

	public void setTotalFloorsNumber(int totalFloorsNumber) {
		this.totalFloorsNumber = totalFloorsNumber;
	}
	
	@Override
	public boolean elevatorCommands(ElevatorCommands cmd, Object... params){
		IElevator controller = ElevatorAdapter.getElevatorRMIInstance();
		switch(cmd){
			case SET_TARGET:{
				if(params[0] != null){
					int p[]=(int[])params[0];
					int elevatorNumber = (int) p[0];
					int target = (int) p[1];
			    try {
					  controller.setTarget(elevatorNumber, target);
					} catch (RemoteException e) {
						e.printStackTrace();
						return false;
					}
					return true;
				}
				return false;
			}
		
			case SET_SERVICES_FLOOR:{
				if(params[0] != null 
						&& params[1] != null && params[2] != null){
					int elevatorNumber = (int) params[0];
					int floor = (int) params[1];
					boolean service = (boolean) params[2]; 
					try {
						controller.setServicesFloors(elevatorNumber, floor, service);
					} catch (RemoteException e) {
						e.printStackTrace();
						return false;
					}
					return true;
				}
				return false;	
			}
			
			default:
				return false;
		}
	}
	

}
