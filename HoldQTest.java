

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HoldQTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class HoldQTest
{
    /**
     * Default constructor for test class HoldQTest
     */
    public HoldQTest()
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
    public void checkMultiplier()
    {
        HoldQ holdQ1 = new HoldQ(-3);
        assertEquals(-3, holdQ1.getMultiplier());
    }

    @Test
    public void checkLeave()
    {
        HoldQ holdQ1 = new HoldQ(2);
        Player player1 = new Player();
        Player player2 = new Player();
        holdQ1.enterCell(player1, 3);
        holdQ1.enterCell(player2, 4);
        assertEquals(0, holdQ1.leaveCell(player2, 3));
        assertEquals(0, holdQ1.leaveCell(player2, 4));
        assertEquals(0, holdQ1.leaveCell(player1, 5));
        assertEquals(6, holdQ1.leaveCell(player1, 3));
    }
}


