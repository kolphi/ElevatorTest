/*
	Adelakun Adetunji - S1210455001 
	Asnake Endalkachew - S1210455002 
	Vladimir Marinkovic - s1210455011 
*/
package sqelevator.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sqelevator.application.model.FloorModel;


public class FloorModelTest {
	
	private FloorModel model = null;

	@Before
	public void setUp() throws Exception {
		model = new FloorModel();
	}
	
	@Test
	public void testDirection(){
		int expected = 1;
		model.setDirection(expected);
		int actual = model.getDirection();
		assertEquals(actual,expected);
	}

}
