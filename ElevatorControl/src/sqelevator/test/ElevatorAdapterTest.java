/*
	Adelakun Adetunji - S1210455001 
	Asnake Endalkachew - S1210455002 
	Vladimir Marinkovic - s1210455011 
*/
package sqelevator.test;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import sqelevator.IElevator;
import sqelevator.TestMockupElevatorRMI;
import sqelevator.application.service.ElevatorAdapter;
import sqelevator.application.ui.ElevatorUI;
import sqelevator.application.ui.FloorsUI;

/*
 * 
 * NOTE:
 * 
 * In order to run tests with server you need to start mockup RMI server first:
 * 
 * 
 * Step 01:
 * D:\ava-ecc\trunk\ECC\bin>rmic sqelevator.MockupElevatorRMI
 * Step 02:
 * D:\ava-ecc\trunk\ECC\bin>rmiregistry &
 * 
 * Step 03: (another console)
 * D:\ava-ecc\trunk\ECC\bin>java sqelevator.MockupElevatorRMIServer &
 * 
 * */


public class ElevatorAdapterTest {
	private ElevatorAdapter adapter = null;
	//private static final String MOCK_RMI_SERVER_URL = "rmi://localhost/sqelevator.MockupElevatorRMI";
	private static final String MOCK_RMI_SERVER_URL = "rmi://localhost/ElevatorSim";
	private ElevatorUI elevatorUI = new ElevatorUI(!RUN_TESTS_WITHOUT_SERVER);
	private FloorsUI floorsUI = new FloorsUI();
	
	private static final boolean RUN_TESTS_WITHOUT_SERVER = true;
	private ArrayList<Observer> observers = null;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		observers = new ArrayList<Observer>();
		observers.add(elevatorUI);
		observers.add(floorsUI);		
		if(RUN_TESTS_WITHOUT_SERVER){
			adapter = new ElevatorAdapter(new TestMockupElevatorRMI(),observers);
		}else{
			adapter = new ElevatorAdapter(MOCK_RMI_SERVER_URL, observers);
			adapter.connect();
		}
	}

	@Test
	public void testConnecttion() {
		boolean actual = adapter.isConnected();
		assertEquals(actual, true);
	}
	
//	@Test 
//	public void testConnectionMalformedURLException(){
//		ElevatorAdapter adapterDummy = new ElevatorAdapter("", observers);
//		expectedException.expect(ClassCastException.class);
//		adapterDummy.connect();
//		
//	}
	
	@Test
	public void testGetElevatorRMIInstance() {
		IElevator controller = ElevatorAdapter.getElevatorRMIInstance();
		assertNotNull(controller);
	}

}
