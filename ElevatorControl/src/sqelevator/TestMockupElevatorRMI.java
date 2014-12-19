package sqelevator;
import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Remote Class for the "Hello, world!" example.
 */
public class TestMockupElevatorRMI implements IElevator {

	private String message;
	private int target=0;
	private int commitedDirection = 0;
	private int floorNum = 10;
	private int  curentElevatorFloor = 0;
	
	private HashMap<Integer,Boolean> floors = new HashMap<Integer,Boolean>(floorNum);
    private HashMap<Integer, Boolean> floorButtonDown = new HashMap<Integer, Boolean>(floorNum);
	private HashMap<Integer, Boolean> floorButtonUp = new HashMap<Integer, Boolean>(floorNum);
	
	
	public TestMockupElevatorRMI(){
		for(int i = 0; i < floorNum; i++){
			floorButtonUp.put(i, false);
			floorButtonDown.put(i, false);
		}
		
	//	floorButtonDown.put(5, true);
		
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
		
		return curentElevatorFloor;
	}
	@Override
	public int getElevatorNum() throws RemoteException {
		
		return 1;
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
		
		return floorButtonDown.get(floor);
	}
	@Override
	public boolean getFloorButtonUp(int floor) throws RemoteException {
		
		return floorButtonUp.get(floor);
	}
	@Override
	public int getFloorHeight() throws RemoteException {
		
		return 8;
	}
	@Override
	public int getFloorNum() throws RemoteException {
		
		return target;
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
		//startService();
		
	}
	@Override
	public long getClockTick() throws RemoteException {
		
		return 1000;
	}
	
	/*public void startService(){
		Runnable t = new Runnable() {
			
			@Override
			public void run() {
				while(true){
					if(curentElevatorFloor != target){
						if(target > curentElevatorFloor){
							curentElevatorFloor += 1;
						}else{
							curentElevatorFloor -= 1;
						}
					}else{
						floorButtonDown.put(target, false);
						floorButtonUp.put(target, false);
						break;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		};
		t.run();*/
//		Timer t = new Timer();
//		t.schedule(new TimerTask() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				if(curentElevatorFloor != target){
//					if(target > curentElevatorFloor){
//						curentElevatorFloor += 1;
//					}else{
//						curentElevatorFloor -= 1;
//					}
//				}
//			}
//		}, 10);
		
	//}
}