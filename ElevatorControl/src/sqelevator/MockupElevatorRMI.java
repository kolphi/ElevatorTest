/*
	Adelakun Adetunji - S1210455001 
	Asnake Endalkachew - S1210455002 
	Vladimir Marinkovic - s1210455011 
*/

package sqelevator;
import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.Random;
/**
 * Remote Class for the "Hello, world!" example.
 */
public class MockupElevatorRMI extends UnicastRemoteObject implements IElevator {

	private String message;
	private int target=0;
	private int commitedDirection = 0;
	private int floorNum = 100;
	private HashMap<Integer,Boolean> floors = new HashMap<Integer,Boolean>(floorNum);
	
	protected MockupElevatorRMI() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getCommittedDirection(int elevatorNumber) throws RemoteException {
		return commitedDirection;
	}
	@Override
	public int getElevatorAccel(int elevatorNumber) throws RemoteException {
		
		return 5;
	}
	@Override
	public boolean getElevatorButton(int elevatorNumber, int floor)
			throws RemoteException {
		
		return false;
	}
	@Override
	public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
		
		return 1;
	}
	@Override
	public int getElevatorFloor(int elevatorNumber) throws RemoteException {
		
		return target;
	}
	@Override
	public int getElevatorNum() throws RemoteException {
		
		return 12;
	}
	@Override
	public int getElevatorPosition(int elevatorNumber) throws RemoteException {
		
		return 24;
	}
	@Override
	public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
		
		return 4;
	}
	@Override
	public int getElevatorWeight(int elevatorNumber) throws RemoteException {
		return 459;
	}
	@Override
	public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
		
		return 10;
	}
	@Override
	public boolean getFloorButtonDown(int floor) throws RemoteException {
		
		return true;
	}
	@Override
	public boolean getFloorButtonUp(int floor) throws RemoteException {
		
		return true;
	}
	@Override
	public int getFloorHeight() throws RemoteException {
		
		return 8;
	}
	@Override
	public int getFloorNum() throws RemoteException {
		
		return floorNum;
	}
	@Override
	public boolean getServicesFloors(int elevatorNumber, int floor)
			throws RemoteException {
		
		if( floors.get(floor) == null){
			floors.put(floor, true);
			return true;
		}else{
			return floors.get(floor);
		}
	}
	@Override
	public int getTarget(int elevatorNumber) throws RemoteException {
		
		return target;
	}
	@Override
	public void setCommittedDirection(int elevatorNumber, int direction)
			throws RemoteException {
		
		commitedDirection = direction;
	}
	@Override
	public void setServicesFloors(int elevatorNumber, int floor, boolean service)
			throws RemoteException {
		
		floors.put(floor, service);
	}
	@Override
	public void setTarget(int elevatorNumber, int target) throws RemoteException {
		
		this.target= target; 
	}
	@Override
	public long getClockTick() throws RemoteException {
		
		return 1000;
	}
}