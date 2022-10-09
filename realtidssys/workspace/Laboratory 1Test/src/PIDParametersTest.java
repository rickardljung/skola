import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>PIDParametersTest</code> contains tests for the class <code>{@link PIDParameters}</code>.
 *
 * @generatedBy CodePro at 12/15/14 10:25 AM
 * @author rickard
 * @version $Revision: 1.0 $
 */
public class PIDParametersTest {
	/**
	 * Run the Object clone() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	@Test
	public void testClone_1()
		throws Exception {
		PIDParameters fixture = new PIDParameters();
		fixture.Tr = 1.0;
		fixture.integratorOn = true;
		fixture.Td = 1.0;
		fixture.K = 1.0;
		fixture.Ti = 1.0;
		fixture.N = 1.0;
		fixture.H = 1.0;
		fixture.Beta = 1.0;

		Object result = fixture.clone();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object clone() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	@Test
	public void testClone_2()
		throws Exception {
		PIDParameters fixture = new PIDParameters();
		fixture.Tr = 1.0;
		fixture.integratorOn = true;
		fixture.Td = 1.0;
		fixture.K = 1.0;
		fixture.Ti = 1.0;
		fixture.N = 1.0;
		fixture.H = 1.0;
		fixture.Beta = 1.0;

		Object result = fixture.clone();

		// add additional test code here
		assertNotNull(result);
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
		new org.junit.runner.JUnitCore().run(PIDParametersTest.class);
	}
}