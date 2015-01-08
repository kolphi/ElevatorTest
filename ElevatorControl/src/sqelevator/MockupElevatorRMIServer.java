
package sqelevator;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class MockupElevatorRMIServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Naming.rebind ("sqelevator.MockupElevatorRMI", new MockupElevatorRMI ());
			System.out.println ("Hello Server is ready.");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
