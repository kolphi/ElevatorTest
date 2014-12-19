/*
	Adelakun Adetunji - S1210455001 
	Asnake Endalkachew - S1210455002 
	Vladimir Marinkovic - s1210455011 
*/
package sqelevator.application.ui;

import java.util.Observable;
import java.util.Observer;
import sqelevator.application.model.IFloorModel;

public class FloorsUI implements Observer{
	private IFloorModel model = null;
	
	public FloorsUI(){

	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg != null && (arg instanceof IFloorModel)){
			model = (IFloorModel)arg;
			System.out.println("FloorsUI:"+model.getFloorNumber());
		}
	}
}
