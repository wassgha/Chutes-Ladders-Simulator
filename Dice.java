/*
 * 
 * This is the dice class.
 * 
 * If created without arguments it defaults to a dice of values between 1 and 6
 * Otherwise, it is possible to specify a maximum for the dice through the second constrcutor.
 * 
 */
import java.util.*;
public class Dice
{
    private Random spin;
    private int maximum;
    
    public Dice() {
        // Sleep for 1 millisecond to get a new random seed
        try {
            Thread.sleep(1);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        } 
        // Initialize the generator
        spin = new Random(System.currentTimeMillis());
        this.maximum = 6;
    }
    
    public Dice(int maximum)
    {
        // Sleep for 1 millisecond to get a new random seed
        try {
            Thread.sleep(1);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        } 
        // Initialize the generator
        spin = new Random(System.currentTimeMillis());
        this.maximum = maximum;
    }
    
    public int roll()
    {
        return spin.nextInt(maximum)+1;
    }
}