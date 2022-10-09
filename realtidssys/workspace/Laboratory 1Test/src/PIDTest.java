import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>PIDTest</code> contains tests for the class <code>{@link PID}</code>.
 *
 * @generatedBy CodePro at 12/15/14 10:23 AM
 * @author rickard
 * @version $Revision: 1.0 $
 */
public class PIDTest {
	/**
	 * Run the PID(String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testPID_1()
		throws Exception {
		String name = "";

		PID result = new PID(name);

		// add additional test code here
		assertNotNull(result);
		assertEquals(50L, result.getHMillis());
	}

	/**
	 * Run the double calculateOutput(double,double) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testCalculateOutput_1()
		throws Exception {
		PID fixture = new PID("");
		fixture.calculateOutput(1.0, 1.0);
		double newY = 1.0;
		double yref = 1.0;

		double result = fixture.calculateOutput(newY, yref);

		// add additional test code here
		assertEquals(0.5555555555555556, result, 0.1);
	}

	/**
	 * Run the long getHMillis() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testGetHMillis_1()
		throws Exception {
		PID fixture = new PID("");
		fixture.calculateOutput(1.0, 1.0);

		long result = fixture.getHMillis();

		// add additional test code here
		assertEquals(50L, result);
	}

	/**
	 * Run the void setParameters(PIDParameters) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testSetParameters_1()
		throws Exception {
		PID fixture = new PID("");
		fixture.calculateOutput(1.0, 1.0);
		PIDParameters newParameters = new PIDParameters();

		fixture.setParameters(newParameters);

		// add additional test code here
	}

	/**
	 * Run the void setParameters(PIDParameters) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testSetParameters_2()
		throws Exception {
		PID fixture = new PID("");
		fixture.calculateOutput(1.0, 1.0);
		PIDParameters newParameters = new PIDParameters();

		fixture.setParameters(newParameters);

		// add additional test code here
	}

	/**
	 * Run the void updateState(double) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testUpdateState_1()
		throws Exception {
		PID fixture = new PID("");
		fixture.calculateOutput(1.0, 1.0);
		double u = 1.0;

		fixture.updateState(u);

		// add additional test code here
	}

	/**
	 * Run the void updateState(double) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testUpdateState_2()
		throws Exception {
		PID fixture = new PID("");
		fixture.calculateOutput(1.0, 1.0);
		double u = 1.0;

		fixture.updateState(u);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PIDTest.class);
	}
}