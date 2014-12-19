package sqelevator.application.bl;

import java.util.ArrayList;

import sqelevator.application.model.FloorCommands;
import sqelevator.application.model.FloorModel;
import sqelevator.application.model.IFloorModel;

public class AutomoteModeBL {
	
	public static int FindNextTarget(int totalFloors, int currentFloor, ArrayList<IFloorModel> floors){
		int minDiff = totalFloors;
		int target = 0;
		
		for(IFloorModel floor : floors){
			int floorNum = floor.getFloorNumber();
			boolean resultDown = floor.floorCommands(FloorCommands.GET_FLOOR_BUTTON_DOWN, floorNum);
			boolean resultUp = floor.floorCommands(FloorCommands.GET_FLOOR_BUTTON_UP, floorNum);
			
			if(resultDown || resultUp){
				int temp = Math.abs(floor.getFloorNumber() - currentFloor);
				if(temp < minDiff){
					minDiff = temp;
					target = floorNum;
				}
			}
		}
		
		return target;
	}

}
