

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DiceComparatorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DiceComparatorTest
{
    /**
     * Default constructor for test class DiceComparatorTest
     */
    public DiceComparatorTest()
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
    public void CompareTwoPlayers()
    {
        Player player1 = new Player();
        player1.enteredWithDice(5);
        Player player2 = new Player();
        player2.enteredWithDice(3);
        DiceComparator diceComp1 = new DiceComparator();
        assertEquals(-1, diceComp1.compare(player1, player2));
    }
}

