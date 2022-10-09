import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>PIParametersTest</code> contains tests for the class <code>{@link PIParameters}</code>.
 *
 * @generatedBy CodePro at 12/15/14 10:23 AM
 * @author rickard
 * @version $Revision: 1.0 $
 */
public class PIParametersTest {
	/**
	 * Run the Object clone() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testClone_1()
		throws Exception {
		PIParameters fixture = new PIParameters();
		fixture.integratorOn = true;
		fixture.Beta = 1.0;
		fixture.Ti = 1.0;
		fixture.Tr = 1.0;
		fixture.K = 1.0;
		fixture.H = 1.0;

		Object result = fixture.clone();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object clone() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testClone_2()
		throws Exception {
		PIParameters fixture = new PIParameters();
		fixture.integratorOn = true;
		fixture.Beta = 1.0;
		fixture.Ti = 1.0;
		fixture.Tr = 1.0;
		fixture.K = 1.0;
		fixture.H = 1.0;

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
		new org.junit.runner.JUnitCore().run(PIParametersTest.class);
	}
}