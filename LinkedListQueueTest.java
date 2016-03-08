

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LinkedListQueueTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LinkedListQueueTest
{
    /**
     * Default constructor for test class LinkedListQueueTest
     */
    public LinkedListQueueTest()
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
    public void testPeek()
    {
        LinkedListQueue linkedLi1 = new LinkedListQueue();
        Player player1 = new Player();
        Player player2 = new Player();
        linkedLi1.add(player1);
        linkedLi1.add(player2);
        assertEquals(player1, linkedLi1.peek());
    }

    @Test
    public void testRemove()
    {
        LinkedListQueue linkedLi1 = new LinkedListQueue();
        Player player1 = new Player();
        Player player2 = new Player();
        linkedLi1.add(player1);
        linkedLi1.add(player2);
        assertEquals(player1, linkedLi1.remove());
        assertEquals(player2, linkedLi1.remove());
    }
}


