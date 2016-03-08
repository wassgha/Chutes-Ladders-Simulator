

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DiceTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DiceTest
{
    /**
     * Default constructor for test class DiceTest
     */
    public DiceTest()
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
    public void checkRollInRangeAuto()
    {
        Dice dice1 = new Dice();
        for(int i =0; i<100; i++) {
            int roll = dice1.roll();
            assertTrue(roll>0 && roll <=6);
        }
    }


    @Test
    public void checkRollInRangeWithMaximum()
    {
        Dice dice1 = new Dice(20);
        for(int i =0; i<100; i++) {
            int roll = dice1.roll();
            assertTrue(roll>0 && roll <=20);
        }
    }
}

