
import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PlayerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlayerTest
{
    /**
     * Default constructor for test class PlayerTest
     */
    public PlayerTest()
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
    public void CheckPositionInitial()
    {
        Player player1 = new Player();
        int[] compare = {0,0};
        assertTrue(Arrays.equals(compare, player1.getPosition()));
    }

    @Test
    public void CheckPositionXY()
    {
        Player player1 = new Player();
        int[] compare = {5,10};
        player1.setPosition(compare);
        assertTrue(Arrays.equals(compare, player1.getPosition()));
    }

    @Test
    public void CheckAddCoins()
    {
        Player player1 = new Player();
        player1.addCoins(5);
        player1.addCoins(15);
        assertEquals(20, player1.getCoins());
    }


    @Test
    public void CheckEnteredWithDiceInitial()
    {
        Player player1 = new Player();
        assertEquals(-1, player1.enteredWithDice());
    }



    @Test
    public void CheckEnteredWithDice()
    {
        Player player1 = new Player();
        player1.enteredWithDice(10);
        assertEquals(10, player1.enteredWithDice());
    }
}

