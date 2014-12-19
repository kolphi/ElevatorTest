package sqelevator.application.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import sqelevator.IElevator;
import sqelevator.application.service.ElevatorAdapter;
import sqelevator.application.service.IElevatorAdapter;

public class Main {
	private static IElevator controller = null;
	//private static final String RMI_URL = "rmi://localhost/sqelevator.MockupElevatorRMI";
	private static final String RMI_URL = "rmi://localhost/ElevatorSim";
	static ElevatorUI elevatorUI;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    elevatorUI = new ElevatorUI(true);
		elevatorUI.setSize(600, 600);
    	elevatorUI.setVisible(true);
    	
//		FloorsUI floorsUI = new FloorsUI();
		ArrayList<Observer> observers = new ArrayList<Observer>();
		observers.add(elevatorUI);
//		observers.add(floorsUI);
		IElevatorAdapter adapter = new ElevatorAdapter(RMI_URL, observers);
		runElevator(adapter);
	}
	
	public static void runElevator(final IElevatorAdapter adapter){
		if(adapter.connect()){	
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask(){
				@Override
				public void run() {
					adapter.updateModels();
					elevatorUI.repaint();
				}
			}, new Date(), 100); //every 10th of a second (100ms)
		}
	}
	


}
