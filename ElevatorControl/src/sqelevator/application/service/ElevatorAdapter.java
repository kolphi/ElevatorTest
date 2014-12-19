/*
	Adelakun Adetunji - S1210455001 
	Asnake Endalkachew - S1210455002 
	Vladimir Marinkovic - s1210455011 
*/
package sqelevator.application.service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import sqelevator.IElevator;
import sqelevator.application.model.ElevatorModel;
import sqelevator.application.model.FloorModel;
import sqelevator.application.model.IElevatorModel;
import sqelevator.application.model.IFloorModel;

// TODO: Auto-generated Javadoc
/**
 * The Class ElevatorAdapter.
 */
public class ElevatorAdapter extends Observable implements IElevatorAdapter{
	
	/** The elevatorModel. */
	private ElevatorModel elevatorModel = null;
	private FloorModel floorModel = null;
	
	/** The controller. */
	private static IElevator controller = null;
	
	/** The rmi url. */
	private String rmiURL = null;
	
	/** The connected. */
	private boolean connected = false;
	
	/**
	 * Instantiates a new elevator adapter.
	 *
	 * @param rmiURL the rmi url
	 * @param observers the observers
	 */
	public ElevatorAdapter(String rmiURL, ArrayList<Observer> observers){
		elevatorModel = new ElevatorModel();
		this.rmiURL = rmiURL;
		for(Observer o : observers){
			this.addObserver(o);
		}
	}
	
	public ElevatorAdapter(IElevator controller, ArrayList<Observer> observers){
		elevatorModel = new ElevatorModel();
		this.controller = controller;
		connected = true;
		for(Observer o : observers){
			this.addObserver(o);
		}
	}

	/* (non-Javadoc)
	 * @see sqelevator.application.service.IElevatorAdapter#refresh()
	 */
	public void updateModels(){
		try {
			if(controller.getElevatorNum()>0){
				int elevatorNumber = 0;
				elevatorModel.setElevatorNumber(controller.getElevatorNum());
				elevatorModel.setAcceleration(elevatorNumber);
				elevatorModel.setCapacity(controller.getElevatorCapacity(elevatorNumber));
				elevatorModel.setClockTick(controller.getClockTick());
				elevatorModel.setCommittedDirection(controller.getCommittedDirection(elevatorNumber));
				elevatorModel.setDoorStatus(controller.getElevatorDoorStatus(elevatorNumber));
				elevatorModel.setFeetsPosition(controller.getElevatorFloor(elevatorNumber));
				elevatorModel.setNearestFloor(controller.getElevatorFloor(elevatorNumber));
				elevatorModel.setNextTargetFloor(controller.getTarget(elevatorNumber));
				elevatorModel.setSpeed(controller.getElevatorSpeed(elevatorNumber));
				elevatorModel.setWeight(controller.getElevatorWeight(elevatorNumber));
				elevatorModel.setElevatorFloorNumber(controller.getElevatorFloor(elevatorNumber));
	
				elevatorModel.setTotalFloorsNumber(controller.getFloorNum());
				int numOfFloors = controller.getFloorNum();
				HashMap<Integer,Integer> requests = new HashMap<Integer,Integer>();
				ArrayList<IFloorModel> floors = new ArrayList<IFloorModel>();
				for(int i=0; i<numOfFloors; i++){
					floorModel = new FloorModel();
					boolean isDown = controller.getFloorButtonDown(i);
					boolean isUp = controller.getFloorButtonUp(i);
					if(isUp&&isDown){
						floorModel.setDirection(3);
						requests.put(i, 3);
					}else if(isDown){
						floorModel.setDirection(1);
						requests.put(i, 1);
					}else if(isUp){
						floorModel.setDirection(0);
						requests.put(i, 0);
					}else{
						//neither up or down
						floorModel.setDirection(2);
					}
					floorModel.setFeetsPosition(controller.getFloorHeight() * i);
					floorModel.setFloorNumber(i);
					floors.add(floorModel);
					elevatorModel.setFloors(floors);
					elevatorModel.setFollowingRequests(requests);
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setChanged();
		notifyObservers((IElevatorModel)elevatorModel);
		notifyObservers((IFloorModel)floorModel);
	}

	/* (non-Javadoc)
	 * @see sqelevator.application.service.IElevatorAdapter#isConnected()
	 */
	@Override
	public boolean isConnected() {
		return connected;
	}
	
	public static IElevator getElevatorRMIInstance(){
		return controller;
	}
	
	/* (non-Javadoc)
	 * @see sqelevator.application.service.IElevatorAdapter#connect()
	 */
	public boolean connect(){
		try {
			controller = (IElevator)Naming.lookup(rmiURL);
			connected = true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			connected = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			connected = false;
		} catch (NotBoundException e) {
			e.printStackTrace();
			connected = false;
		}catch (Exception e) {
			e.printStackTrace();
			connected = false;
		}
		
		return connected;
	}

	public ElevatorModel getElevatorModel() {
		return elevatorModel;
	}
}