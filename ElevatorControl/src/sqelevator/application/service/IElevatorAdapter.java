package sqelevator.application.service;

import sqelevator.application.model.ElevatorModel;

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
