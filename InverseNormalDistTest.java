

import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class InverseNormalDistTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class InverseNormalDistTest
{
    /**
     * Default constructor for test class InverseNormalDistTest
     */
    public InverseNormalDistTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void checkBetween()
    {
        InverseNormalDist inverseN2 = new InverseNormalDist();
        double[] compare = {-0.6741891400433162,0.6741891400433162};
        assertTrue(Arrays.equals(compare, inverseN2.between(.5, 0, 1)));
    }

    @Test
    public void checkOutside()
    {
        InverseNormalDist inverseN1 = new InverseNormalDist();
        double[] compare = {-0.6741891400433162,0.6741891400433162};
        assertTrue(Arrays.equals(compare, inverseN1.outside(.5, 0, 1)));
    }
}


