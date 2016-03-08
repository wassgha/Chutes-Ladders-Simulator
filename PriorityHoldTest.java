

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PriorityHoldTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PriorityHoldTest
{
    /**
     * Default constructor for test class PriorityHoldTest
     */
    public PriorityHoldTest()
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
        PriorityHold priority1 = new PriorityHold(-3);
        assertEquals(-3, priority1.getMultiplier());
    }

    @Test
    public void testLeaving()
    {
        Player player1 = new Player();
        Player player2 = new Player();
        PriorityHold priority1 = new PriorityHold(2);
        priority1.enterCell(player1, 5);
        priority1.enterCell(player2, 2);
        assertEquals(0, priority1.leaveCell(player2, 2));
        assertEquals(0, priority1.leaveCell(player2, 3));
        assertEquals(0, priority1.leaveCell(player1, 3));
        assertEquals(10, priority1.leaveCell(player1, 5));
    }
}


