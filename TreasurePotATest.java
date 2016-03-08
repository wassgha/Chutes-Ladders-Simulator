

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TreasurePotATest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TreasurePotATest
{
    /**
     * Default constructor for test class TreasurePotATest
     */
    public TreasurePotATest()
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
        TreasurePotA treasure1 = new TreasurePotA(5, 20);
        assertEquals(5, treasure1.getBounty());
        assertEquals(20, treasure1.getmaxBountiesGiven());
    }

    @Test
    public void checkPlayerGetsCoins()
    {
        Player player1 = new Player();
        assertEquals(0, player1.getCoins());
        TreasurePotA treasure1 = new TreasurePotA(5, 20);
        treasure1.enterCell(player1, 2);
        assertEquals(5, player1.getCoins());
        assertEquals(19, treasure1.getmaxBountiesGiven());
    }

    @Test
    public void checkLeave()
    {
        TreasurePotA treasure1 = new TreasurePotA(5, 20);
        Player player1 = new Player();
        treasure1.enterCell(player1, 5);
        assertEquals(3, treasure1.leaveCell(player1, 3));
    }
}


