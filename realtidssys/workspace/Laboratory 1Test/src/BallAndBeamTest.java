import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>BallAndBeamTest</code> contains tests for the class <code>{@link BallAndBeam}</code>.
 *
 * @generatedBy CodePro at 12/15/14 10:23 AM
 * @author rickard
 * @version $Revision: 1.0 $
 */
public class BallAndBeamTest {
	/**
	 * Run the BallAndBeam() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testBallAndBeam_1()
		throws Exception {

		BallAndBeam result = new BallAndBeam();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the BallAndBeam() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testBallAndBeam_2()
		throws Exception {

		BallAndBeam result = new BallAndBeam();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the double[] computeOutput(double[],double[]) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testComputeOutput_1()
		throws Exception {
		BallAndBeam fixture = new BallAndBeam();
		double[] state = new double[] {0.0, 0.0, 1.0};
		double[] input = new double[] {};

		double[] result = fixture.computeOutput(state, input);

		// add additional test code here
		assertNotNull(result);
		assertEquals(2, result.length);
		assertEquals(0.0, result[0], 1.0);
		assertEquals(1.0, result[1], 1.0);
	}

	/**
	 * Run the void draw(Graphics2D,JPanel,double[],double[],double[]) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testDraw_1()
		throws Exception {
		BallAndBeam fixture = new BallAndBeam();
		BufferedImage image = new BufferedImage(8, 8, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		JPanel jp = new JPanel();
		double[] state = new double[] {};
		double[] input = new double[] {};
		double[] output = new double[] {1.0};

		fixture.draw(g2, jp, state, input, output);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 1
		//       at BallAndBeam.draw(BallAndBeam.java:91)
	}

	/**
	 * Run the void draw(Graphics2D,JPanel,double[],double[],double[]) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testDraw_2()
		throws Exception {
		BallAndBeam fixture = new BallAndBeam();
		BufferedImage image = new BufferedImage(8, 8, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		JPanel jp = new JPanel();
		double[] state = new double[] {};
		double[] input = new double[] {};
		double[] output = new double[] {1.0};

		fixture.draw(g2, jp, state, input, output);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 1
		//       at BallAndBeam.draw(BallAndBeam.java:91)
	}

	/**
	 * Run the double[] updateState(double[],double[],double) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/15/14 10:23 AM
	 */
	@Test
	public void testUpdateState_1()
		throws Exception {
		BallAndBeam fixture = new BallAndBeam();
		double[] state = new double[] {0.0, 1.0, 1.0};
		double[] input = new double[] {};
		double h = 1.0;

		double[] result = fixture.updateState(state, input, h);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at BallAndBeam.updateState(BallAndBeam.java:60)
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
		new org.junit.runner.JUnitCore().run(BallAndBeamTest.class);
	}
}