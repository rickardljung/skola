import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>BallAndBeamRegulTest</code> contains tests for the class <code>{@link BallAndBeamRegul}</code>.
 *
 * @generatedBy CodePro at 12/15/14 10:24 AM
 * @author rickard
 * @version $Revision: 1.0 $
 */
public class BallAndBeamRegulTest {
	/**
	 * Run the BallAndBeamRegul(ReferenceGenerator,BallAndBeam,int) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:24 AM
	 */
	@Test
	public void testBallAndBeamRegul_1()
		throws Exception {
		ReferenceGenerator refgen = new ReferenceGenerator(1.0, 1.0);
		BallAndBeam bb = new BallAndBeam();
		int priority = 1;

		BallAndBeamRegul result = new BallAndBeamRegul(refgen, bb, priority);

		// add additional test code here
		assertNotNull(result);
		assertEquals("Thread[Thread-56,1,main]", result.toString());
		assertEquals(false, result.isInterrupted());
		assertEquals("Thread-56", result.getName());
		assertEquals(false, result.isAlive());
		assertEquals(1, result.getPriority());
		assertEquals(0, result.countStackFrames());
		assertEquals(true, result.isDaemon());
		assertEquals(113L, result.getId());
	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:24 AM
	 */
	@Test
	public void testRun_1()
		throws Exception {
		BallAndBeamRegul fixture = new BallAndBeamRegul(new ReferenceGenerator(1.0, 1.0), new BallAndBeam(), 1);

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:24 AM
	 */
	@Test
	public void testRun_2()
		throws Exception {
		BallAndBeamRegul fixture = new BallAndBeamRegul(new ReferenceGenerator(1.0, 1.0), new BallAndBeam(), 1);

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:24 AM
	 */
	@Test
	public void testRun_3()
		throws Exception {
		BallAndBeamRegul fixture = new BallAndBeamRegul(new ReferenceGenerator(1.0, 1.0), new BallAndBeam(), 1);

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:24 AM
	 */
	@Test
	public void testRun_4()
		throws Exception {
		BallAndBeamRegul fixture = new BallAndBeamRegul(new ReferenceGenerator(1.0, 1.0), new BallAndBeam(), 1);

		fixture.run();

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 12/15/14 10:24 AM
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
	 * @generatedBy CodePro at 12/15/14 10:24 AM
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
	 * @generatedBy CodePro at 12/15/14 10:24 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BallAndBeamRegulTest.class);
	}
}