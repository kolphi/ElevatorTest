/*
	Adelakun Adetunji - S1210455001 
	Asnake Endalkachew - S1210455002 
	Vladimir Marinkovic - s1210455011 
*/
package sqelevator.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import sqelevator.IElevator;
import sqelevator.TestMockupElevatorRMI;
import sqelevator.application.service.ElevatorAdapter;
import sqelevator.application.ui.ElevatorUI;
import sqelevator.application.ui.FloorsUI;

public class IntegrationTest {
	private ElevatorAdapter adapter = null;
	private static final String MOCK_RMI_SERVER_URL = "rmi://localhost/ElevatorSim";
	
	private FloorsUI floorsUI = new FloorsUI();
	private IElevator controller = null;
	
	private static final boolean RUN_TESTS_WITHOUT_SERVER = false;
	private ElevatorUI elevatorUI = new ElevatorUI(!RUN_TESTS_WITHOUT_SERVER);
	
	@Before
	public void setUp() throws Exception {
		ArrayList<Observer> observers = new ArrayList<Observer>();
		observers.add(elevatorUI);
		observers.add(floorsUI);
		
		if(RUN_TESTS_WITHOUT_SERVER){
			controller =  new TestMockupElevatorRMI();
			adapter = new ElevatorAdapter(controller, observers);
		}else{
			adapter = new ElevatorAdapter(MOCK_RMI_SERVER_URL, observers);
			adapter.connect();
			controller = ElevatorAdapter.getElevatorRMIInstance();
		}
	}
	
	@Test
	public void testScenarioSetTargetFloor(){
		try {
			int elevatorNum = 0;
			int floorHeight = controller.getFloorHeight();
			int ticksPerSecond = 6000/(60*10);
			int currentSpeed = 0;
			
			/* SET MANUAL */
			elevatorUI.rdbtnManual.setSelected(true);
			
			/* SET TARGET FLOOR */
			int totalFloors = controller.getFloorNum();
			int currentFloor = controller.getElevatorFloor(elevatorNum);
			Random generator = new Random(); 
			int target = 0;
			while(target == currentFloor){
				target = generator.nextInt(totalFloors-1);
			}
			
			/* WAIT FOR ELEVATOR TO REACH THE TARGET */
			controller.setTarget(elevatorNum, target);
			int cur = 0;
			int i = 0;
			while(currentFloor != target){
				currentFloor = controller.getElevatorFloor(elevatorNum);
				if(cur == currentFloor){
					if( i == 5 ){
						break;
					}else{
						i++;
					}
				}
				cur = currentFloor;
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			assertEquals(target, controller.getTarget(elevatorNum));
			assertEquals(target, controller.getElevatorFloor(elevatorNum));
			assertEquals(0, controller.getElevatorSpeed(elevatorNum));
			assertTrue(currentFloor>=0 && currentFloor<totalFloors);
			assertTrue(floorHeight > 0);
			assertTrue(totalFloors > 0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("Remote connection error.");
		}
	}

}
