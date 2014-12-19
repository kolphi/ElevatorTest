/*
	Adelakun Adetunji - S1210455001 
	Asnake Endalkachew - S1210455002 
	Vladimir Marinkovic - s1210455011 
*/
package sqelevator.application.service;

import sqelevator.application.model.ElevatorModel;

// TODO: Auto-generated Javadoc
/**
 * The Interface IElevatorAdapter.
 */
public interface IElevatorAdapter{
	
	/**
	 * Connects to RMI server.
	 *
	 * @return true, if successful
	 */
	public boolean connect();
	
	/**
	 * Populates the model with polled data.
	 */
	public void updateModels();
	
	/**
	 * Checks if is connected.
	 *
	 * @return true, if is connected
	 */
	public boolean isConnected();

	/**
	 * Gets the elevator model.
	 *
	 * @return the elevator model
	 */
	public ElevatorModel getElevatorModel();
}
