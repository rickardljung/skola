import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>PIDGUITest</code> contains tests for the class <code>{@link PIDGUI}</code>.
 *
 * @generatedBy CodePro at 12/15/14 10:23 AM
 * @author rickard
 * @version $Revision: 1.0 $
 */
public class PIDGUITest {
	/**
	 * Run the PIDGUI(PID,PIDParameters,String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testPIDGUI_1()
		throws Exception {
		PID pCon = new PID("");
		PIDParameters p = new PIDParameters();
		p.N = 1.0;
		p.Ti = 1.0;
		p.Beta = 1.0;
		p.Td = 1.0;
		p.K = 1.0;
		p.Tr = 1.0;
		p.H = 1.0;
		String n = "";

		PIDGUI result = new PIDGUI(pCon, p, n);

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
		new org.junit.runner.JUnitCore().run(PIDGUITest.class);
	}
}