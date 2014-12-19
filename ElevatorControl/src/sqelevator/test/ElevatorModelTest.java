/*
	Adelakun Adetunji - S1210455001 
	Asnake Endalkachew - S1210455002 
	Vladimir Marinkovic - s1210455011 
*/
package sqelevator.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import sqelevator.MockupElevatorRMIServer;
import sqelevator.TestMockupElevatorRMI;
import sqelevator.application.model.ElevatorCommands;
import sqelevator.application.model.ElevatorModel;
import sqelevator.application.service.ElevatorAdapter;
import sqelevator.application.ui.ElevatorUI;
import sqelevator.application.ui.FloorsUI;

public class ElevatorModelTest {
	
	private ElevatorModel elevatorModel = null;

	@Before
	public void setUp() throws Exception {
		elevatorModel = new ElevatorModel();
	}
	
	@Test
	public void testFollowingRequests(){
		HashMap<Integer, Integer> expected = new HashMap<Integer, Integer>();
		expected.put(1, 1);
		expected.put(2, 2);
		expected.put(3, 3);
		elevatorModel.setFollowingRequests(expected);
		HashMap<Integer, Integer> actual = elevatorModel.getFollowingRequests();
		assertEquals(actual.get(1),new Integer(1));
		assertEquals(actual.get(2),new Integer(2));
		assertEquals(actual.get(3),new Integer(3));
	}
	
	@Test
	public void testFeetsPosition(){
		int expected = 123;
		elevatorModel.setFeetsPosition(expected);
		assertEquals(elevatorModel.getFeetsPosition(),expected);	
	}
	
	@Test
	public void testNearestFloor(){
		int expected = 1;
		elevatorModel.setNearestFloor(expected);
		assertEquals(elevatorModel.getNearestFloor(),expected);
	}
	
	@Test
	public void testTotalFloorsNum(){
		int expected = 12;
		elevatorModel.setTotalFloorsNumber(expected);
		assertEquals(elevatorModel.getTotalFloorsNumber(),expected);
	}

}
