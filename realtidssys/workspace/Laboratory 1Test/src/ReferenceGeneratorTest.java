import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>ReferenceGeneratorTest</code> contains tests for the class <code>{@link ReferenceGenerator}</code>.
 *
 * @generatedBy CodePro at 12/15/14 10:25 AM
 * @author rickard
 * @version $Revision: 1.0 $
 */
public class ReferenceGeneratorTest {
	/**
	 * Run the ReferenceGenerator(double,double) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	@Test
	public void testReferenceGenerator_1()
		throws Exception {
		double h = 1.0;
		double a = 1.0;

		ReferenceGenerator result = new ReferenceGenerator(h, a);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0.0, result.getRef(), 1.0);
		assertEquals("Thread[Thread-83,1,main]", result.toString());
		assertEquals(false, result.isInterrupted());
		assertEquals("Thread-83", result.getName());
		assertEquals(false, result.isAlive());
		assertEquals(1, result.getPriority());
		assertEquals(0, result.countStackFrames());
		assertEquals(true, result.isDaemon());
		assertEquals(140L, result.getId());
	}

	/**
	 * Run the double getRef() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	@Test
	public void testGetRef_1()
		throws Exception {
		ReferenceGenerator fixture = new ReferenceGenerator(1.0, 1.0);

		double result = fixture.getRef();

		// add additional test code here
		assertEquals(0.0, result, 0.1);
	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	@Test
	public void testRun_1()
		throws Exception {
		ReferenceGenerator fixture = new ReferenceGenerator(1.0, 1.0);

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	@Test
	public void testRun_2()
		throws Exception {
		ReferenceGenerator fixture = new ReferenceGenerator(1.0, 1.0);

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	@Test(expected = java.lang.InterruptedException.class)
	public void testRun_3()
		throws Exception {
		ReferenceGenerator fixture = new ReferenceGenerator(1.0, 1.0);

		fixture.run();

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
		new org.junit.runner.JUnitCore().run(ReferenceGeneratorTest.class);
	}
}