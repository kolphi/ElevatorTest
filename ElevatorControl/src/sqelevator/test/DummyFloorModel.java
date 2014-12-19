package sqelevator.test;

import java.rmi.RemoteException;

import sqelevator.application.model.FloorCommands;
import sqelevator.application.model.IFloorModel;

public class DummyFloorModel implements IFloorModel {
	
	int floor_number;
	
	public void setFloorNumber( int num) {
		// TODO Auto-generated method stub
		floor_number = num;
	}

	@Override
	public int getFloorNumber() {
		// TODO Auto-generated method stub
		return floor_number;
	}

	@Override
	public int getDirection() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getFeetsPosition() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public boolean floorCommands(FloorCommands cmd, Object... params) {
		// TODO Auto-generated method stub
		boolean result = false;
		switch(cmd){
		case GET_FLOOR_BUTTON_DOWN:{
			return true;	
		}
		
		case GET_FLOOR_BUTTON_UP:{
			return true;
		}
		
		default:
			return false;
		}
	}

}
