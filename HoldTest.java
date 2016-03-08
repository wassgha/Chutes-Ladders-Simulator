

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HoldTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class HoldTest
{
    /**
     * Default constructor for test class HoldTest
     */
    public HoldTest()
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
    public void testMultiplier()
    {
        Hold hold1 = new Hold(-5);
        assertEquals(-5, hold1.getMultiplier());
    }

    @Test
    public void testLeaving()
    {
        Player player1 = new Player();
        Hold hold1 = new Hold(5);
        assertEquals(0, hold1.leaveCell(player1, 3));
        hold1.enterCell(player1, 4);
        assertEquals(0, hold1.leaveCell(player1, 3));
        assertEquals(20, hold1.leaveCell(player1, 4));
    }
}


