/*
	Adelakun Adetunji - S1210455001 
	Asnake Endalkachew - S1210455002 
	Vladimir Marinkovic - s1210455011 
*/
package sqelevator.application.model;

import java.rmi.RemoteException;

import sqelevator.IElevator;
import sqelevator.application.service.ElevatorAdapter;

public class FloorModel implements IFloorModel{
	private int floorNumber;
	//0 up, 1 down, 2 uncommitted, 3 up&down
	private int direction;
	private int feetsPosition;
	
	@Override
	public int getFloorNumber() {
		return floorNumber;
	}
	
	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	
	@Override
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int commitedDirection) {
		this.direction = commitedDirection;
	}
	
	@Override
	public int getFeetsPosition() {
		return feetsPosition;
	}
	
	public void setFeetsPosition(int feetsPosition) {
		this.feetsPosition = feetsPosition;
	}
	
	@Override
	public boolean floorCommands(FloorCommands cmd, Object... params){
		IElevator controller = ElevatorAdapter.getElevatorRMIInstance();
		boolean result = false;
		switch(cmd){
//			case GET_FLOOR_BUTTON:{
//				if(params.length == 2 && params[0] != null && params[1] != null){
//					int elevatorNumber = (int) params[0];
//					int floor = (int) params[1];
//					try {
//						result = controller.getElevatorButton(elevatorNumber, floor);
//					} catch (RemoteException e) {
//						e.printStackTrace();
//						return result;
//					}
//					return result;
//				}
//				return result;
//			}
		
			case GET_FLOOR_BUTTON_DOWN:{
				if(params.length == 1 && params[0] != null){
					int floor = (int) params[0];
					try {
						result = controller.getFloorButtonDown(floor);
					} catch (RemoteException e) {
						e.printStackTrace();
						return result;
					}
					return result;
				}
				return result;	
			}
			
			case GET_FLOOR_BUTTON_UP:{
				if(params.length == 1 && params[0] != null ){
					int floor = (int) params[0];
					try {
						result = controller.getFloorButtonUp(floor);
					} catch (RemoteException e) {
						e.printStackTrace();
						return result;
					}
					return result;
				}
				return result;	
			}
			
			default:
				return false;
		}
	}
}
