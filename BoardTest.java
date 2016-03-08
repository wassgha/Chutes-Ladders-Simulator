

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BoardTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BoardTest
{
    /**
     * Default constructor for test class BoardTest
     */
    public BoardTest()
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
    public void testXY10And1()
    {
        Board board2 = new Board("config.txt", 10, 6);
        assertEquals(1, board2.convertToCellNum(0, 0));
    }


    @Test
    public void testXY10And100()
    {
        Board board2 = new Board("config.txt", 10, 6);
        assertEquals(100, board2.convertToCellNum(0, 9));
    }


    @Test
    public void testXY10And50()
    {
        Board board2 = new Board("config.txt", 10, 6);
        assertEquals(50, board2.convertToCellNum(9, 4));
    }


    @Test
    public void testXY50And1()
    {
        Board board2 = new Board("config.txt", 50, 6);
        assertEquals(1, board2.convertToCellNum(0, 0));
    }


    @Test
    public void testXY50And100()
    {
        Board board2 = new Board("config.txt", 50, 6);
        assertEquals(100, board2.convertToCellNum(0, 1));
    }


    @Test
    public void testXY50And50()
    {
        Board board2 = new Board("config.txt", 50, 6);
        assertEquals(50, board2.convertToCellNum(49, 0));
    }

    @Test
    public void testFirstAndLastEmpty()
    {
        for(int i = 10; i<=50; i+=10) {
            Board board2 = new Board("config.txt", i, 6);
            assertTrue((board2.getComponentAtXY(board2.convertToXY(1)[0], board2.convertToXY(1)[1]) instanceof Blank) && (board2.getComponentAtXY(board2.convertToXY(i*i)[0], board2.convertToXY(i*i)[1]) instanceof Blank));
        }
    }


    @Test
    public void checkNoSuccessiveHold()
    {
        Board board2 = new Board("config.txt", 10, 6);
        for(int i = 2; i<=100; i++) {
            int[] curcell = board2.convertToXY(i);
            int[] prevcell = board2.convertToXY(i-1);
            assertFalse((board2.getComponentAtXY(curcell[0], curcell[1]) instanceof HoldCells) && (board2.getComponentAtXY(prevcell[0], prevcell[1]) instanceof HoldCells));
        }
    }

}

