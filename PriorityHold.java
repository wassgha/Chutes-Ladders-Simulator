import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * This is the class for the PriorityHold tile
 * 
 * When a player enters the tile, he gets added to a priority queue along 
 * with all the players who entered and their dice rolls are saved.
 * When the player rolls the dice he leaves if he is the head of the list and
 * he rolled the same dice value that he entered the cell with and when he leaves,
 * he advances by a number of steps proportional to his dice roll and a multiplier
 * 
 */

public class PriorityHold extends HoldCells
{
    private int multiplier;
    private PriorityQueue<Player> heldPlayers;
    private Comparator<Player> comparator;
    public PriorityHold(int multiplier)
    {
        this.multiplier = multiplier;
        this.comparator = new DiceComparator();
        // initialize the queue with the dice roll comparator
        this.heldPlayers = new PriorityQueue<Player>(comparator);
    }
    
    public int leaveCell(Player player, int diceRoll) {
        // if the player is the head of the list and his dice roll is the one that is saved
        if(heldPlayers.contains(player) && heldPlayers.peek() == player && player.enteredWithDice() == diceRoll){
            heldPlayers.poll();
            player.enteredWithDice(-1);
            return diceRoll*multiplier;
        }
        return 0; 
    }

    public int tryToLeaveCell(Player player, int diceRoll) {
        if(heldPlayers.contains(player) && heldPlayers.peek() == player && player.enteredWithDice() == diceRoll){
            return diceRoll*multiplier;
        }
        return 0; 
    }
    
    public void enterCell(Player player, int diceRoll) {
        if(!heldPlayers.contains(player)) {
            player.enteredWithDice(diceRoll);
            heldPlayers.add(player);
        }
    }
    
    public int getMultiplier() {
        return this.multiplier;
    }
}