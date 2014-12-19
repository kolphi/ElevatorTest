/*
	Adelakun Adetunji - S1210455001 
	Asnake Endalkachew - S1210455002 
	Vladimir Marinkovic - s1210455011 
*/
package sqelevator.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ElevatorAdapterTest.class,
		ElevatorTest.class,
		IntegrationTest.class,
		AcceptanceTest.class,
		ElevatorModelTest.class,
		FloorModelTest.class,
		AutomodeTest.class})
public class AllTests {

}
