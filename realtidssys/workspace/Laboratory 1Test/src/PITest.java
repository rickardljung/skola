import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>PITest</code> contains tests for the class <code>{@link PI}</code>.
 *
 * @generatedBy CodePro at 12/15/14 10:25 AM
 * @author rickard
 * @version $Revision: 1.0 $
 */
public class PITest {
	/**
	 * Run the PI(String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	@Test
	public void testPI_1()
		throws Exception {
		String name = "";

		PI result = new PI(name);

		// add additional test code here
		assertNotNull(result);
		assertEquals(50L, result.getHMillis());
	}

	/**
	 * Run the double calculateOutput(double,double) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	@Test
	public void testCalculateOutput_1()
		throws Exception {
		PI fixture = new PI("");
		double y = 1.0;
		double yref = 1.0;

		double result = fixture.calculateOutput(y, yref);

		// add additional test code here
		assertEquals(0.0, result, 0.1);
	}

	/**
	 * Run the long getHMillis() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	@Test
	public void testGetHMillis_1()
		throws Exception {
		PI fixture = new PI("");

		long result = fixture.getHMillis();

		// add additional test code here
		assertEquals(50L, result);
	}

	/**
	 * Run the void setParameters(PIParameters) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	@Test
	public void testSetParameters_1()
		throws Exception {
		PI fixture = new PI("");
		PIParameters newParameters = new PIParameters();

		fixture.setParameters(newParameters);

		// add additional test code here
	}

	/**
	 * Run the void setParameters(PIParameters) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	@Test
	public void testSetParameters_2()
		throws Exception {
		PI fixture = new PI("");
		PIParameters newParameters = new PIParameters();

		fixture.setParameters(newParameters);

		// add additional test code here
	}

	/**
	 * Run the void updateState(double) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	@Test
	public void testUpdateState_1()
		throws Exception {
		PI fixture = new PI("");
		double u = 1.0;

		fixture.updateState(u);

		// add additional test code here
	}

	/**
	 * Run the void updateState(double) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	@Test
	public void testUpdateState_2()
		throws Exception {
		PI fixture = new PI("");
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
	 * @generatedBy CodePro at 12/15/14 10:25 AM
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
	 * @generatedBy CodePro at 12/15/14 10:25 AM
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
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PITest.class);
	}
}