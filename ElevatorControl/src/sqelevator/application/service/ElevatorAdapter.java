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
import sqelevator.application.model.Floor;
import sqelevator.application.model.IElevatorModel;
import sqelevator.application.model.IFloor;

/**
 * The Class ElevatorAdapter
 * 
 * @author Koller - S1310455008
 */
public class ElevatorAdapter extends Observable implements IElevatorAdapter {

	/** The elevatorModel. */
	private ElevatorModel elevatorModel = null;
	// private Floor floor = null;

	/** The controller. */
	private static IElevator controller = null;

	/** The rmi url. */
	private String rmiURL = null;

	/** The connected. */
	private boolean connected = false;

	/**
	 * Instantiates a new elevator adapter.
	 * 
	 * @param rmiURL
	 *            the rmi url
	 * @param observers
	 *            the observers
	 */
	public ElevatorAdapter(String rmiURL, ArrayList<Observer> observers) {
		elevatorModel = new ElevatorModel();
		this.rmiURL = rmiURL;
		for (Observer o : observers) {
			this.addObserver(o);
		}
	}

	public ElevatorAdapter(IElevator controller, ArrayList<Observer> observers) {
		elevatorModel = new ElevatorModel();
		this.controller = controller;
		connected = true;
		for (Observer o : observers) {
			this.addObserver(o);
		}
	}

	public void updateModels() {
		try {
			if (controller.getElevatorNum() > 0) {
				int elevatorNumber = 0;
				//set connected in model to true
				elevatorModel.setConnected(true);
				
				elevatorModel.setElevatorNumber(controller.getElevatorNum());
				elevatorModel.setCapacity(controller
						.getElevatorCapacity(elevatorNumber));
				elevatorModel.setCommittedDirection(controller
						.getCommittedDirection(elevatorNumber));
				elevatorModel.setDoorStatus(controller
						.getElevatorDoorStatus(elevatorNumber));
				elevatorModel.setNextTargetFloor(controller
						.getTarget(elevatorNumber));
				elevatorModel.setSpeed(controller
						.getElevatorSpeed(elevatorNumber));
				elevatorModel.setWeight(controller
						.getElevatorWeight(elevatorNumber));
				elevatorModel.setElevatorFloorNumber(controller
						.getElevatorFloor(elevatorNumber));

				elevatorModel.setTotalFloorsNumber(controller.getFloorNum());
				int numOfFloors = controller.getFloorNum();
				HashMap<Integer, Integer> requests = new HashMap<Integer, Integer>();
				ArrayList<IFloor> floors = new ArrayList<IFloor>();
				Floor floor;
				for (int i = 0; i < numOfFloors; i++) {
					floor = new Floor();
					boolean isDown = controller.getFloorButtonDown(i);
					boolean isUp = controller.getFloorButtonUp(i);
					if (isUp && isDown) {
						floor.setDirection(3);
						requests.put(i, 3);
					} else if (isDown) {
						floor.setDirection(1);
						requests.put(i, 1);
					} else if (isUp) {
						floor.setDirection(0);
						requests.put(i, 0);
					} else {
						// neither up or down
						floor.setDirection(2);
					}

					floor.setFloorNumber(i);

					floor.setTarget(controller.getElevatorButton(
							elevatorNumber, i));
					System.out.println("service floor number: " + i + " is checked " + controller.getElevatorButton(elevatorNumber, i));
					floors.add(floor);

				}
				elevatorModel.setFloors(floors);
				elevatorModel.setFollowingRequests(requests);
			}
		} catch (Exception e) {
			connected = false;
			//set connected in model to true
			elevatorModel.setConnected(false);
			e.printStackTrace();
		}
		setChanged();
		notifyObservers((IElevatorModel) elevatorModel);
	}

	@Override
	public boolean isConnected() {
		return connected;
	}

	public static IElevator getElevatorRmiInstance() {
		return controller;
	}

	public boolean connect() {
		try {
			if (!connected) {
				controller = (IElevator) Naming.lookup(rmiURL);
				connected = true;
			}
		} catch (Exception e) {
			connected = false;
			elevatorModel.setConnected(false);
			e.printStackTrace();
		}
		return connected;
	}

	public ElevatorModel getElevatorModel() {
		return elevatorModel;
	}

	public IElevator getIElevator() {
		return controller;
	}
}