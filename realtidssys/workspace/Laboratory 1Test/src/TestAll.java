

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The class <code>TestAll</code> builds a suite that can be used to run all
 * of the tests within its package as well as within any subpackages of its
 * package.
 *
 * @generatedBy CodePro at 12/15/14 10:25 AM
 * @author rickard
 * @version $Revision: 1.0 $
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	PITest.class,
	PIDGUITest.class,
	MainBBTest.class,
	PIDParametersTest.class,
	BallAndBeamRegulTest.class,
	PIParametersTest.class,
	PIGUITest.class,
	ReferenceGeneratorTest.class,
	PIDTest.class,
	BallAndBeamTest.class,
})
public class TestAll {

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 12/15/14 10:25 AM
	 */
	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}