
/*
 * This is the class for the HoldQ tile
 * 
 * When a player enters the tile, he gets added to a normal queue along 
 * with all the players who entered and their dice rolls are saved.
 * When the player rolls the dice he leaves if he is the head of the list and
 * he rolled the same dice value that he entered the cell with and when he leaves,
 * he advances by a number of steps proportional to his dice roll and a multiplier
 * 
 */

public class HoldQ extends HoldCells
{
    private int multiplier;
    private LinkedListQueue heldPlayers;
    public HoldQ(int multiplier)
    {
        this.multiplier = multiplier;
        this.heldPlayers = new LinkedListQueue();
    }
    
    public int leaveCell(Player player, int diceRoll) {
        // if the player is the head of the list and his dice roll is the one that is saved
        if(heldPlayers.contains(player) && heldPlayers.peek() == player && player.enteredWithDice() == diceRoll){
            player.enteredWithDice(-1);
            heldPlayers.remove();
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