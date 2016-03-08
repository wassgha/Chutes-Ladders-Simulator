

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BlankTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BlankTest
{
    /**
     * Default constructor for test class BlankTest
     */
    public BlankTest()
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
    public void checkLeave()
    {
        Blank blank1 = new Blank();
        Player player1 = new Player();
        blank1.enterCell(player1, 5);
        assertEquals(3, blank1.leaveCell(player1, 3));
    }
}

