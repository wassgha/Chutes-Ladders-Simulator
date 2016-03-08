

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TreasurePotBTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TreasurePotBTest
{
    /**
     * Default constructor for test class TreasurePotBTest
     */
    public TreasurePotBTest()
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
    public void checkTreasurePotCreation()
    {
        TreasurePotB treasure1 = new TreasurePotB(20);
        assertEquals(20, treasure1.getCoinsLeft());
    }

    @Test
    public void checkPlayerGetsCoins()
    {
        Player player1 = new Player();
        assertEquals(0, player1.getCoins());
        TreasurePotB treasure1 = new TreasurePotB(20);
        treasure1.enterCell(player1, 2);
        assertEquals(2, player1.getCoins());
        assertEquals(18, treasure1.getCoinsLeft());
    }

    @Test
    public void checkLeave()
    {
        TreasurePotB treasure1 = new TreasurePotB(20);
        Player player1 = new Player();
        treasure1.enterCell(player1, 5);
        assertEquals(3, treasure1.leaveCell(player1, 3));
    }
}


