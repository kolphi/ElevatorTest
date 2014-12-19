/*
	Adelakun Adetunji - S1210455001 
	Asnake Endalkachew - S1210455002 
	Vladimir Marinkovic - s1210455011 
*/
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
