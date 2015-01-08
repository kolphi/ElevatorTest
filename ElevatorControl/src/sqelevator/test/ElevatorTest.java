package sqelevator.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observer;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import sqelevator.IElevator;
import sqelevator.TestMockupElevatorRMI;
import sqelevator.application.service.ElevatorAdapter;
import sqelevator.application.ui.AECUI;

/**
 * Test class for Elevator manual mode
 */
public class ElevatorTest {
	private ElevatorAdapter adapter = null;
	private static final String MOCK_RMI_SERVER_URL = "rmi://localhost/sqelevator.MockupElevatorRMI";
	//private static final String MOCK_RMI_SERVER_URL = "rmi://localhost/ElevatorSim";
	private AECUI elevatorUI = new AECUI();
	private IElevator controller = null;
	
	private static final boolean RUN_TESTS_WITHOUT_SERVER = true;
	
	@Before
	public void setUp() throws Exception {
		ArrayList<Observer> observers = new ArrayList<Observer>();
		observers.add(elevatorUI);
		
		if(RUN_TESTS_WITHOUT_SERVER){
			controller =  new TestMockupElevatorRMI();
			adapter = new ElevatorAdapter(controller, observers);
		}else{
			adapter = new ElevatorAdapter(MOCK_RMI_SERVER_URL, observers);
			adapter.connect();
			controller = ElevatorAdapter.getElevatorRmiInstance();
		}
	}
	
	@Test
	public void testGetCommittedDirection(){
		try {
			int elevatorNumber = 0;
			int direction = controller.getCommittedDirection(elevatorNumber);
			assertTrue(direction ==0 || direction==1 || direction==2);
		} catch (RemoteException e) {
			e.printStackTrace();
			Assert.fail("Remote connection error.");
		}
	}

	@Test
	public void testElevatorDirection(){
		try {
			int elevatorNumber = 0;
			int currentFloor = controller.getElevatorFloor(elevatorNumber);
			int direction = controller.getCommittedDirection(elevatorNumber);
			int nextFloor = controller.getTarget(elevatorNumber);
			if(direction == 0 || direction == 1){
				if(nextFloor > currentFloor){
					assertEquals(direction, 0);
				}else if(nextFloor < currentFloor){
					assertEquals(direction, 1);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			Assert.fail("Remote connection error.");
		}
	}
	
	@Test
	public void testSetCommittedDirection(){
		int expectedDirection = 0;
		try {
			int elevatorNumber = 0;
			controller.setCommittedDirection(elevatorNumber, expectedDirection);
			int actualDirection = controller.getCommittedDirection(elevatorNumber);
			assertEquals(expectedDirection, actualDirection);
		} catch (RemoteException e) {
			e.printStackTrace();
			Assert.fail("Remote connection error.");
		}
	}
	
	@Test
	public void testSetServicesFloors(){
		int expectedFloor = 3;
		boolean expectedService = false;
		try {
			int elevatorNumber = 0;
			controller.setServicesFloors(elevatorNumber, expectedFloor, expectedService);
			boolean actualService = controller.getServicesFloors(elevatorNumber, expectedFloor);
			assertEquals(expectedService,actualService);
		} catch (RemoteException e) {
			e.printStackTrace();
			Assert.fail("Remote connection error.");
		}
	}
	
	@Test
	public void testGetElevatorDoorStatus(){
		try {
			int elevatorNumber = 0;
			int status = controller.getElevatorDoorStatus(elevatorNumber);
			if(status == 1){
				int floor = controller.getElevatorFloor(elevatorNumber);
				boolean service = controller.getServicesFloors(elevatorNumber, floor);
				assertTrue(service == true);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			Assert.fail("Remote connection error.");
		}
	}

	@Test
	public void testSetTarget(){
		int expectedTarget = 10;
		try {
			int elevatorNumber = 0;
			controller.setTarget(elevatorNumber, expectedTarget);
			int actualTarget = controller.getTarget(elevatorNumber);
			assertEquals(expectedTarget, actualTarget);
		} catch (RemoteException e) {
			e.printStackTrace();
			Assert.fail("Remote connection error.");
		}
	}
	
	@Test
	public void testGetElevatorButton(){
		int floor = 0;
		try{
			int elevatorNumber = 0;
			boolean btnPressed = controller.getElevatorButton(elevatorNumber, floor);	
			assertNotNull(btnPressed);
		}catch (RemoteException e) {
			e.printStackTrace();
			Assert.fail("Remote connection error.");
		}
	}
	
	@Test
	public void testGetFloorButtonDown(){
		int floor = 0;
		try{
			int elevatorNumber = 0;
			boolean btnPressed = controller.getFloorButtonDown(floor);	
			assertNotNull(btnPressed);
		}catch (RemoteException e) {
			e.printStackTrace();
			Assert.fail("Remote connection error.");
		}
	}
	
	@Test
	public void testGetFloorButtonUp(){
		int floor = 0;
		try{
			int elevatorNumber = 0;
			boolean btnPressed = controller.getFloorButtonUp(floor);
			assertNotNull(btnPressed);
		}catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("Remote connection error.");
		}
	}

//  Moved to integration test	
	@Test
	public void testElevatorTarget(){
		int target=2;
		try {
			int eleveatorNum = 0;
			controller.setTarget(eleveatorNum, target);
			
			// Refresh delay
			final long refreshRate = controller.getClockTick();
			new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						Thread.sleep(refreshRate);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			assertEquals(target,controller.getTarget(eleveatorNum));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("Remote connection error.");
		}
		
	}
	
	
	

}
