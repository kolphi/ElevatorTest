package sqelevator.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import sqelevator.application.bl.AutomoteModeBL;
import sqelevator.application.model.FloorModel;
import sqelevator.application.model.IFloorModel;

public class AutomodeTest {
	private int totalFloors = 10;
	private int currentFloor = 1;
	private ArrayList<IFloorModel> floors = null;

	@Before
	public void setUp() throws Exception {
		floors = new ArrayList<IFloorModel>();
		DummyFloorModel floor = new DummyFloorModel();
		floor.setFloorNumber(5);
		floors.add(floor);
	}

	@Test
	public void testFindNextTargetSingleFloorRequest() {
		int target = AutomoteModeBL.FindNextTarget(totalFloors, currentFloor, floors);
		assertEquals(target, 5);
	}
	
	@Test
	public void testFindNextTargetMultipleFloorRequest() {
		DummyFloorModel floor = new DummyFloorModel();
		floor.setFloorNumber(3);
		floors.add(floor);
		int target = AutomoteModeBL.FindNextTarget(totalFloors, currentFloor, floors);
		assertEquals(target, 3);
	}

}
