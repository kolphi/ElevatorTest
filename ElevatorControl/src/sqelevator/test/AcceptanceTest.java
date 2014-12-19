/*
	Adelakun Adetunji - S1210455001 
	Asnake Endalkachew - S1210455002 
	Vladimir Marinkovic - s1210455011 
*/
package sqelevator.test;

import java.awt.Component;
import java.awt.color.CMMException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.junit.Test;

import junit.extensions.abbot.ComponentTestFixture;
import junit.framework.Assert;
import sqelevator.IElevator;
import sqelevator.TestMockupElevatorRMI;
import sqelevator.application.bl.AutomoteModeBL;
import sqelevator.application.model.ElevatorModel;
import sqelevator.application.model.FloorModel;
import sqelevator.application.model.IElevatorModel;
import sqelevator.application.service.ElevatorAdapter;
import sqelevator.application.service.IElevatorAdapter;
import sqelevator.application.ui.ElevatorUI;
import sqelevator.application.ui.FloorsUI;
import abbot.finder.Matcher;
import abbot.tester.JButtonTester;

public class AcceptanceTest extends ComponentTestFixture {
	
	private static IElevator controller = null;
	private IElevatorAdapter adapter;
	//private static final String RMI_URL = "rmi://localhost/sqelevator.MockupElevatorRMI";
	private static final String RMI_URL = "rmi://localhost/ElevatorSim";
	private ElevatorUI elevatorUI;
	long waitTime;
	
	//GUI components
	private JButton btnSetTarget = null;
	private JLabel lblFloorPosition[] = null; 
 	private JRadioButton rdbtnManual = null;
	private JRadioButton rdbtnAuto = null;
	private JTextField txtSpeed = null;
	private JTextField txtCapacity;
	private JTextField txtClock;
	private JTextField txtWeight;
	private JTextField txtAcceleration;
	private JComboBox<Integer> comboTargetFloor;
	
	private static final boolean RUN_TESTS_WITHOUT_SERVER = true;
	
 	int floorLablel = 0;

	public void setUp() throws Exception {
	
		elevatorUI = new ElevatorUI(!RUN_TESTS_WITHOUT_SERVER);
		elevatorUI.setSize(600, 600);
    	elevatorUI.setVisible(true);
    	
		FloorsUI floorsUI = new FloorsUI();
		ArrayList<Observer> observers = new ArrayList<Observer>();
		observers.add(elevatorUI);
		observers.add(floorsUI);
		
		if(RUN_TESTS_WITHOUT_SERVER){ 
			controller =  new TestMockupElevatorRMI();
			adapter = new ElevatorAdapter(controller, observers);
			adapter.updateModels();
		}else{
			adapter = new ElevatorAdapter(RMI_URL, observers);
			if(adapter.connect()){	
				Timer timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask(){
					@Override
					public void run() {
						adapter.updateModels();
						//elevatorUI.repaint();
					}
				}, new Date(), 100); //every 10th of a second (100ms)
			}
			controller = ElevatorAdapter.getElevatorRMIInstance();
		}
		
		adapter.updateModels();
	 
		Thread.sleep(1000);
		int eleveatorNum = 0;
		int floorHeight = controller.getFloorHeight();
		int ticksPerSecond = 6000/(60*10);
		
		int currentFloor = controller.getElevatorFloor(eleveatorNum);
		int timePerFloor = floorHeight; //Worst Case: elevSpeed ~ 1ft/s
		int ticksPerFloor = timePerFloor * ticksPerSecond;
		waitTime = ticksPerFloor*(currentFloor+1)*10;
		
		btnSetTarget = (JButton) getFinder().find(new Matcher(){ 
			public boolean matches(Component c) { 
				return c instanceof JButton && ((JButton) c).getText().equals("Set Target"); 
			} 
		});
		
		rdbtnManual = (JRadioButton) getFinder().find(new Matcher(){ 
			public boolean matches(Component c) { 
				return c instanceof JRadioButton && ((JRadioButton) c).getText().equals("Manual"); 
			} 
		});
		
		rdbtnAuto = (JRadioButton) getFinder().find(new Matcher(){ 
			public boolean matches(Component c) { 
				return c instanceof JRadioButton && ((JRadioButton) c).getText().equals("Auto"); 
			} 
		});
		
		comboTargetFloor = (JComboBox<Integer>) getFinder().find(new Matcher(){ 
			public boolean matches(Component c) { 
				return c instanceof JComboBox && ((JComboBox<Integer>)c).getSelectedItem().toString()
						.equals(String.valueOf(0)); 
			} 
		});
		
		
		IElevatorModel model = adapter.getElevatorModel() ;
		lblFloorPosition = new JLabel[model.getTotalFloorsNumber()];
		
		for(floorLablel = 0; floorLablel< model.getTotalFloorsNumber(); floorLablel++){
			lblFloorPosition[floorLablel] = (JLabel) getFinder().find(new Matcher() {
				public boolean matches(Component c) {
					return c instanceof JLabel && ((JLabel) c).getText().equals(String.valueOf(floorLablel));
				}
			});
		}
		
		
	 
		
		txtSpeed = (JTextField) getFinder().find(new Matcher() {
			public boolean matches(Component c) {
				return c instanceof JTextField && ((JTextField) c).getName().equals("txtSpeed");
			}
		});
		txtCapacity = (JTextField) getFinder().find(new Matcher() {
			public boolean matches(Component c) {
				return c instanceof JTextField && ((JTextField) c).getName().equals("txtCapacity");
			}
		});
		txtClock = (JTextField) getFinder().find(new Matcher() {
			public boolean matches(Component c) {
				return c instanceof JTextField && ((JTextField) c).getName().equals("txtClock");
			}
		});
		txtWeight = (JTextField) getFinder().find(new Matcher() {
			public boolean matches(Component c) {
				return c instanceof JTextField && ((JTextField) c).getName().equals("txtWeight");
			}
		});
		txtAcceleration = (JTextField) getFinder().find(new Matcher() {
			public boolean matches(Component c) {
				return c instanceof JTextField && ((JTextField) c).getName().equals("txtAcceleration");
			}
		});
		
		
		
		
	}

	public void testSetTargetFloorManualy() {
		JButtonTester rdbt = new JButtonTester(); 
		rdbt.actionClick(rdbtnManual);
		JButtonTester bt = new JButtonTester(); 
		bt.actionClick(btnSetTarget);
		try {
			Thread.sleep(waitTime + 1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			Assert.fail("Thread sleep interrupt.");
		}
	
		assertTrue(elevatorUI.getLabelIcon(Integer.parseInt(comboTargetFloor.getSelectedItem().toString())) != null);
 		 
 	}
	
//	public void testAutomodServiceTest(){
//		JButtonTester rdbt = new JButtonTester(); 
//		rdbt.actionClick(rdbtnAuto);
//		 
//		 ElevatorModel model = adapter.getElevatorModel();
//		 
//		// int nextTarget = AutomoteModeBL.FindNextTarget(model.getTotalFloorsNumber(), model.getElevatorFloorNumber(), model.getFloors());
//		 
//		   try {
//				Thread.sleep(waitTime + 6000);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//				Assert.fail("Thread sleep interrupt.");
//			}
//		   Icon icon = elevatorUI.getLabelIcon(elevatorUI.target);
//			assertTrue( icon != null);
//	 		
//		 
//	}
	
	public void testSwitchToManual(){
		JButtonTester bt = new JButtonTester(); 
		bt.actionClick(rdbtnManual);
		//assertTrue(elevatorUI.rdbtnManual.isSelected());
		assertTrue(btnSetTarget.isEnabled());
	}
	
	public void testSwitchToAutomatic(){
		JButtonTester bt = new JButtonTester(); 
		bt.actionClick(rdbtnAuto);
		assertFalse(btnSetTarget.isEnabled());
	}
	
	@Test
	public void testUpdateUI(){
		
		ElevatorModel elevModel = adapter.getElevatorModel();
		adapter.updateModels();
		
		assertEquals(txtSpeed.getText(), elevModel.getSpeed()+" ft/sec");
		assertEquals(txtCapacity.getText(), elevModel.getCapacity()+" persons");
		assertEquals(txtWeight.getText(), elevModel.getWeight()+" lbs");
		assertEquals(txtAcceleration.getText(), elevModel.getAcceleration()+" ft/sec-2");
		assertEquals(txtClock.getText(), String.valueOf(elevModel.getClockTick()));
	}

}
