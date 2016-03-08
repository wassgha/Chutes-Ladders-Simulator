import java.util.*;

/*
 * This is the LinkedList Queue implementation for the HoldQ tile
 * 
 * This class uses LinkedList as a container and adds methods to it
 * to mimmick a queue container. When a player is added, he is added
 * to the tail of the queue (the last value of the linkedlist) and
 * only the player at the head of the queue (the first value of the
 * linkedlist) can be retrieved through either remove or peek.
 * 
 */

public class LinkedListQueue
{
    private LinkedList<Player> data;

    /**
     * Constructor for objects of class LinkedListQueue
     */
    public LinkedListQueue()
    {
        data = new LinkedList<Player>();
    }
    
    public void add(Player e) {
        data.add(e);
    }

    public boolean contains(Player e) {
        return data.contains(e);
    }
    
    public Player remove() {
        Player temp = data.get(0);
        data.remove(0);
        return temp;
    }
    
    public Player peek() {
        return data.get(0);
    }
}
